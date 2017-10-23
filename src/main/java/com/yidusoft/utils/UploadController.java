package com.yidusoft.utils;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UploadController
 * 上传
 * Created by zcansheng on 2017/3/7.
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    SecUserService secUserService;


    Logger logger = LoggerFactory.getLogger(UploadController.class);
    static List<String> pictures = null;//图片
    static List<String> docs = null;//文档
    static List<String> videos = null;//视频
    static List<String> tottents = null;//种子
    static List<String> audios = null;//音乐
    static List<String> others = null;//其他
    static {
        //初始化文件类型
        pictures = new ArrayList<String>(Arrays.asList("JPEG","JPG","PNG","GIF","TIFF","BMP","DWG","DWG","PSD"));
        List<String> docs = new ArrayList<String>(Arrays.asList("PPT","PPTX","RTF","XML","HTML","CSS","JS","EML","DBX","PST","XLS_DOC","XLSX_DOCX","VSD","MDB",
                "MDB","WPS","WPD","EPS","PDF","QDF","PWL","ZIP","RAR","JSP","JAVA","CLASS",
                "JAR","MF","EXE","CHM"));
        List<String> videos = new ArrayList<String>(Arrays.asList("AVI","RAM","RM","MPG","MOV","ASF","MP4","FLV","MID"));
        List<String> tottents = new ArrayList<String>(Arrays.asList("TORRENT"));
        List<String> audios = new ArrayList<String>(Arrays.asList("WAV","MP3"));
        List<String> others = new ArrayList<String>(Arrays.asList("JPEG","PNG","GIF","TIFF","BMP","DWG","DWG","PSD"));
    }
    /**
     * 上传
     * @param file
     * @param request
     * 几个可选参数：
        1. fileType ："文件类型限制"
            传值范围：图片 pictures 文档 docs 视频 videos 种子 tottents 音乐 audios 其他 others
            不传递该参数，则默认允许所有类型上传
        2. fileSize : "文件大小限制"
            传值范围：该参数以字节为单位
            不传递则不限制文件大小
        3. 如果你想直接保存到数据库，需要传递以下两个参数：
           updateSql  :  将文件路径保存到数据库的sql
            格式如： UPDATE basic_user_info i SET i.avatar= ? WHERE i.userinfo_id = ?
            更新数据库的sql，即把路径保存到指定的表中
           primaryKey : 更新的表格主键
     * @return
     */
    @PostMapping("/upFile")
    public Result fileUpload(@RequestParam("file") MultipartFile file,
                             HttpServletRequest request) {
        //String updateSql = (String)request.getParameter("updateSql");
      //  String primaryKey = (String)request.getParameter("primaryKey");
        String fileSize = file.getSize()+"";
        String fileNames=file.getOriginalFilename();
        String fileType =fileNames.substring(fileNames.indexOf("."));
        System.out.println("文件类型是："+fileType);

        Result actionResult = new Result();
        String path = null;// 文件路径
        String childPath = null;//文件子路径
        String trueFileName = null;//文件名
        try{
            if (file != null) {// 判断上传的文件是否为空
                String type = null;// 文件类型
                String fileName = file.getOriginalFilename();// 文件原名称
                Long size = file.getSize();
                actionResult.setMessage("上传的文件原名称:" + fileName);
                System.out.println("上传的文件原名称:" + fileName);
                //判断文件大小
              /*  if(fileSize!=null&& !"".equals(fileSize)){
                    Long sizeMax = Long.parseLong(fileSize);
                    if (size>sizeMax){
                        size = size/1024/1024;
                       // actionResult.setSuccess(false);
                        actionResult.setMessage("上传失败：文件太大。要求大小为：" + size+"M");
                        logger.info("上传失败：文件太大");
                        return actionResult;
                    }
                }*/
                // 判断文件类型
                type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
                System.out.println("上传的文件类型:" + type);
                if (type != null) {// 判断文件类型是否为空
                    //文件类型默认为true，假如有传递类型显示，则进行判断
                    boolean typeFlag = true;
                    if (fileType!=null && !"".equals(fileType)){
                        System.out.println("文件类型是："+fileType);
                        typeFlag = this.isRightType(fileType,type);
                    }
                    if (typeFlag) {
                        // 项目在容器中实际发布运行的根路径
                        String realPath = System.getProperty("user.dir");
                        // 自定义的文件名称
                        trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                        // 设置存放图片文件的路径
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        childPath = "/upFile/upload/"+format.format(new Date());
//                        childPath = saveUrl /*System.getProperty("file.separator")+*/;
//                        childPath = "themes\\cust\\images\\avatars\\" +/*System.getProperty("file.separator")+*/trueFileName;
//                        childPath ="/"+childPath.replace("\\", "/");//开头加"/",反斜杠转换成斜杠
                        path = realPath + childPath;
                        actionResult.setMessage("存放文件的路径:" + path + trueFileName);
//                        File f = new File(saveUrl);
                        File dir = new File(path);
                        dir.mkdirs();

                        // 转存文件到指定的路径
                        file.transferTo(new File(path+"/"+trueFileName));
                        actionResult.setMessage("文件成功上传到指定目录下");

                        //保存到数据数据库
//                        childPath ="/"+childPath.replace("\\", "/");//开头加"/",反斜杠转换成斜杠
                       /* if (updateSql != null && !"".equals(updateSql)){
                           Dao.executeUpdate(updateSql,childPath+"/"+trueFileName,primaryKey);
                        }*/
                    } else {
                       // actionResult.setSuccess(false);
                        actionResult.setMessage("上传失败：文件类型错误。要求类型为："+getTypes(fileType).toString());
                        logger.info("上传失败：文件类型不匹配");
                        return actionResult;
                    }
                } else {
                   // actionResult.setSuccess(false);
                    actionResult.setMessage("上传失败：文件类型为空");
                    logger.info("上传失败：文件类型为空");
                }
            } else {
               // actionResult.setSuccess(false);
                actionResult.setMessage("上传失败：文件不存在");
                logger.info("上传失败：文件不存在");
            }
        }catch (Exception e){
            //actionResult.setSuccess(false);
            actionResult.setMessage("上传失败：请重试或联系工作人员");
            logger.info("上传失败：IllegalStateException, IOException");
        }
        // 返回路子径给页面  目的：1. 让页面显示预览效果  2.让页面把路径保存到数据库（如果前面已经保存了，这不需要再次保存）
        //actionResult.setObject(childPath+"/"+trueFileName);
        String paths=path.replace("\\","/");
         paths=paths.replace("//","/");
        String fileJson="{\"filePath\":\""+paths+"/"+trueFileName+"\",\"fileName\":\""+fileNames+"\"}";
        actionResult.setData(fileJson);
        actionResult.setMessage("上传成功！");
        return actionResult;
    }

    /**
     * 判断文件类型是否正确
     * @param fileType 要求的类型
     * @param type 实际的类型
     * @return
     */
    public static boolean isRightType(String fileType,String type){
        if ("pictures".equals(fileType)){
            return pictures.contains(type.toUpperCase());
        } else if ("docs".equals(fileType)){
            return docs.contains(type.toUpperCase());
        } else if ("videos".equals(fileType)){
            return videos.contains(type.toUpperCase());
        } else if ("tottents".equals(fileType)){
            return tottents.contains(type.toUpperCase());
        } else if ("audios".equals(fileType)){
            return audios.contains(type.toUpperCase());
        } else {
            return true;
        }
    }

    // 上传图片 "JPG","PNG"
    @PostMapping("/uploadImg")
    public String uploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        Map<String,Object> map=new HashMap();
        try {
            String fileName = file.getOriginalFilename();// 文件原名称

            String type= fileName.substring(fileName.lastIndexOf(".")).toLowerCase();;
           if(type.equals(".jpg") || type.equals(".png")){
               String realPath = System.getProperty("user.dir");
               // 自定义的文件名称
               String  trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
               // 设置存放图片文件的路径
               SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
               String childPath="/upload/image/"+format.format(new Date());
               String path = realPath + childPath;

               File dir = new File(path);
               if(!dir.exists()){
                   dir.mkdirs();
               }

               file.transferTo(new File(path+"/"+trueFileName));
               String img= Base64ToImage.getImageStr(path+"/"+trueFileName);
               map.put("imgPath",path+"/"+trueFileName);
               map.put("img",img);

                 }else{
               return ResultGenerator.genFailResult("只能上传png与jpg的文件！").toString();
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(map).toString();
    }


    // layui上传用户头像 "JPG","PNG"
    @PostMapping("/uploadImglayUi")
    @ResponseBody
    public String uploadImglayUi(HttpServletRequest request, @RequestParam("file") MultipartFile file,String userId){
        FileResponseData fileResponseData = null;
        try {
            String fileName = file.getOriginalFilename();// 文件原名称

            String type= fileName.substring(fileName.lastIndexOf(".")).toLowerCase();;

            if(type.equals(".jpg") || type.equals(".png")){
                String realPath = System.getProperty("user.dir");
                String childPath="/upload/headImg";

                String path = realPath + childPath;
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                file.transferTo(new File(path+"/"+userId+type));

                fileResponseData = new FileResponseData();
                fileResponseData.setCode(0);
                fileResponseData.setMsg("上传成功");
                Data data = new Data();
                data.setSrc(childPath+"/"+userId+type);
                fileResponseData.setData(data);

            }else{
                fileResponseData.setCode(500);
                fileResponseData.setMsg("只能上传png与jpg的文件！");
                return JSON.toJSONString(fileResponseData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(fileResponseData);
    }



    //app端的图片上传

    @PostMapping("/appUploadImg")
    public Result appUploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        Map<String,Object> map=new HashMap();
        try {
            String fileName = file.getOriginalFilename();// 文件原名称

            String type= fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            if(type.equals(".jpg") || type.equals(".png")){
                String realPath = System.getProperty("user.dir");
                // 自定义的文件名称
                String  trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                // 设置存放图片文件的路径
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String childPath="/upload/image/"+format.format(new Date());
                String path = realPath + childPath;

                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                file.transferTo(new File(path+"/"+trueFileName));
                String img= Base64ToImage.getImageStr(path+"/"+trueFileName);
                map.put("imgPath",path+"/"+trueFileName);
                map.put("img",img);
                SecUser secUser= secUserService.findById(Security.getUserId());
                secUser.setHeadImg(path+"/"+trueFileName);
                secUserService.update(secUser);
            }else{
                return ResultGenerator.genFailResult("只能上传png与jpg的文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult(map);
    }




    //获取绝对路径
    @PostMapping("/getFilePath")
    public Result getFilePath(HttpServletRequest request, @RequestParam("file") MultipartFile files){
        Result result=new Result();
        // 项目在容器中实际发布运行的根路径
        String realPath = System.getProperty("user.dir");

        // 设置存放图片文件的路径
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String childPath = "/upFile/upload/"+format.format(new Date());
//                        childPath = saveUrl /*System.getProperty("file.separator")+*/;
//                        childPath = "themes\\cust\\images\\avatars\\" +/*System.getProperty("file.separator")+*/trueFileName;
//                        childPath ="/"+childPath.replace("\\", "/");//开头加"/",反斜杠转换成斜杠
       String path = realPath + childPath;
        File dir = new File(path);
        dir.mkdirs();
        result.setData(path);
        return result;
    }



    /**
     * 获取限制的文件类型
     * @param fileType
     * @return
     */
    public static List<String> getTypes(String fileType){
        if ("pictures".equals(fileType)){
            return pictures;
        } else if ("docs".equals(fileType)){
            return docs;
        } else if ("videos".equals(fileType)){
            return videos;
        } else if ("tottents".equals(fileType)){
            return tottents;
        } else if ("audios".equals(fileType)){
            return audios;
        } else {
            return null;
        }
    }
}

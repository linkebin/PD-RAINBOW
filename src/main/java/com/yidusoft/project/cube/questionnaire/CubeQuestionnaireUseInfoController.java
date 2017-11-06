package com.yidusoft.project.cube.questionnaire;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.DataAcquisition;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.DataAcquisitionService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by yhdj on 2017/10/25.
 */

@RestController
@RequestMapping("/cube/questionnaire/")
public class CubeQuestionnaireUseInfoController {
    @Resource
    private DataAcquisitionService dataAcquisitionService;

    @PostMapping("/findQuestionnaireUseInfoListByPage")
    @ResponseBody
    public Result questionnaireUseInfoListByPage(int page,int size){
        PageHelper.startPage(page, size);
        List<DataAcquisition> list = dataAcquisitionService.questionnaireUseInfoListByPage();
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);

    }

    /**
     * 查找我的问卷
     * @param params
     * @param page
     * @param size
     * @return
     */

    @PostMapping("/findMyQuestionnaireListByPage")
    @ResponseBody
    public Result findMyQuestionnaireListByPage(String params, int page, int size,String createTime){
        Map<String,Object> map = JSON.parseObject(params, Map.class);
//        Date date = null;
//        if (createTime != null && createTime != "") {
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//            try {
//                date = format.parse(createTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            dataAcquisition.setCreateTime(date);
//
//        }
        map.put("dataUser",Security.getUserId());
        //dataAcquisition.setDataUser(Security.getUserId());
        PageHelper.startPage(page, size);
        List<DataAcquisition> list = dataAcquisitionService.findMyQuestionnaireListByPage(map);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 逻辑删除我的问卷
     * @param id
     * @return
     */
    @PostMapping("/deleteMyQuestionnaire")
    @ResponseBody
    public Result deleteMyQuestionnaire(String id){
        if (id != null && id != ""){
            DataAcquisition dataAcquisition = dataAcquisitionService.findById(id);
            dataAcquisition.setDeleted(1);
            dataAcquisitionService.update(dataAcquisition);
        }
        return ResultGenerator.genSuccessResult();
    }

}

package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.tools.internal.ws.processor.model.Model;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.monitor.domain.LoginLog;
import com.yidusoft.project.monitor.service.LoginLogService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.redisMq.MsgGenerator;
import com.yidusoft.redisMq.MsgSend;
import com.yidusoft.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

import static com.yidusoft.utils.Security.getUser;

/**
 * Created by you on 2017/7/17.
 */
@Controller
public class IndexController {
    @Autowired
    SecUserService secUserService;
    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value ={"/index",""})
    public String main(){
        if (Security.getUserId()==null){
            return "login";
        }
        SecUser secUser = Security.getUser();
        if (0==secUser.getAccountType()){
            return  "index";
        }else {
            return  "project/cube/index";
}
    }


    @RequestMapping("/403")
    public String forbidden(){
        return "/error/403";
    }

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        if (Security.getUserId()==null){
            return "login";
        }
        SecUser secUser = Security.getUser();
        if (0==secUser.getAccountType()){
            return  "index";
        }else {
            return  "project/cube/index";
        }
    }

    /**
     * 用户注册
     * @param json
     * @return
     */
    @PostMapping("/sign")
    @ResponseBody
    public Result sign(String json,HttpServletRequest request) {

        SecUser secUser = JSON.parseObject(json,SecUser.class);

        String code = (String) request.getSession().getAttribute("signCode");
        if (Integer.parseInt(code) != secUser.getMsgCode()){
            return ResultGenerator.genFailResult("验证码错误");
        }

        Result result = secUserService.addUser(JSON.toJSONString(secUser));
        if (result.getCode() !=200){
            return result;
        }

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 发送注册手机验证码
     * @param request
     * @return
     */
    @PostMapping("/sign/code")
    @ResponseBody
    public Result signcode(HttpServletRequest request,String mobile) {

        try{
            String json = SendMessageCode.sendMessageCode(mobile);
            SMSCode smsCode = JSON.parseObject(json,SMSCode.class);
            if (smsCode.getCode() == 200){
                request.getSession().setAttribute("signCode",smsCode.getObj());
            }else{
                return ResultGenerator.genFailResult("验证码发生失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("验证码发生失败");
        }
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value="/indexInfo")
    public String indexInfo(){
        return "indexInfo";
    }

    @Resource
    private MsgSend msgSend;


    /**
     * 登录请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String submitLogin(HttpServletRequest request,String username, String password,String vrifyCode) {
        request.setAttribute("account", username);
        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        if (!captchaId.equals(vrifyCode)) {
            request.setAttribute("msg", "验证码错误！");
            return "login";
        }


        UsernamePasswordToken token = null;
        try {
            token = new UsernamePasswordToken(username, password);
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);

        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", lae.getMessage());
            return "login";
        } catch (UnknownAccountException e) {
            token.clear();
            request.setAttribute("msg", e.getMessage());
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            request.setAttribute("msg", "密码错误");
            return "login";
        }
        SecUser  user = secUserService.getSecUserInfo(username);

        try {
            //将图片转换成base64
            if(user.getHeadImg()!=null && !user.getHeadImg().equals("")){
                user.setHeadImg(Base64ToImage.getImageStr(user.getHeadImg()));
            }
        }catch (Exception e) {
            user.setHeadImg("");
        }

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSessionId", user.getId());
        session.setAttribute("userSession", user);
        msgSend.send(MsgGenerator.genLoginLogMessage(user));
        return  "redirect:/index";
    }

    @RequestMapping(value="/login1",method= RequestMethod.POST)
    public String login(HttpServletRequest request, String username, String password,String vrifyCode){
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//            request.setAttribute("msg", "请输入用户名和密码！");
//            return "login";
//        }
//
//        if (!"vrifyCode".equals(vrifyCode)){
//            String captchaId = (String) request.getSession().getAttribute("vrifyCode");
//            System.out.println(vrifyCode);
//            if (!captchaId.equals(vrifyCode)) {
//                request.setAttribute("msg", "错误的验证码！");
//                return "login";
//            }
//        }
//
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
//        try {
//
//            subject.login(token);
//            return "index";
//        } catch (LockedAccountException lae) {
//            token.clear();
//            request.setAttribute("msg", "账号已被锁定，请与管理员联系！");
//            return "login";
//        } catch (UnknownAccountException uae) {
//            token.clear();
//            request.setAttribute("msg", "账号不存在！");
//            return "login";
//        } catch (AuthenticationException ae) {
//            //用户名和密码不匹配时，切换为手机或邮箱登录
//            SecUser secUser = null;
//            try{
//                secUser = secUserService.getSecUserInfo(username);
//            }catch (Exception e){
//                token.clear();
//                request.setAttribute("msg", "账号异常，请联系管理员！");
//                return "login";
//            }
//            if (secUser!=null){
//                if(!"true".equals(request.getAttribute("flag"))){
//                    request.setAttribute("flag","true");
//                    login(request, secUser.getAccount(), password,"passCode");
//                }
//            }
//            token.clear();
//            Session session = SecurityUtils.getSubject().getSession();
//            session.removeAttribute("userSessionId");
//            session.removeAttribute("userSession");
//            request.setAttribute("msg", "密码不正确！");
            return "login";
//        }
    }

    @RequestMapping(value="/appLogin",method= RequestMethod.POST)
    @ResponseBody
    public Result appLogin(HttpServletRequest request, String username, String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult("请输入用户名和密码！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return ResultGenerator.genSuccessResult(getUser());
        } catch (LockedAccountException lae) {
            token.clear();
            return ResultGenerator.genFailResult("账号已被锁定，请与管理员联系！");
        } catch (UnknownAccountException uae) {
            token.clear();
            return ResultGenerator.genFailResult("账号不存在！");
        } catch (AuthenticationException ae) {
            //用户名和密码不匹配时，切换为手机或邮箱登录
            SecUser secUser = null;
            try{
                secUser = secUserService.getSecUserInfo(username);
            }catch (Exception e){
                return ResultGenerator.genFailResult("账号异常，请联系管理员！");
            }
            if (secUser!=null){
                if(!"true".equals(request.getAttribute("flag"))){
                    request.setAttribute("flag","true");
                    appLogin(request, secUser.getAccount(), password);
                }
            }
            token.clear();
            return ResultGenerator.genFailResult("密码不正确！");
        }
    }
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/sign/imgcode")
    public void defaultKaptcha(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}

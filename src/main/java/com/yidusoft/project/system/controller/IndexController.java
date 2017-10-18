package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
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
import org.springframework.ui.Model;
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


    @RequestMapping(value = "/sign/perfact/info")
    public String signPerfactInfo(String d ,Model model){

        model.addAttribute("d",d);

        return "project/cube/perfact-Info";
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

    @RequestMapping("/sign/forgetpassword")
    public String forgetpassword(){
        return "project/cube/forget-password";
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

        String captchaId = (String) request.getSession().getAttribute("vrifyCode");

        if (!captchaId.equals(secUser.getVrifyCode()) && !"".equals(captchaId)) {
            return ResultGenerator.genFailResult("图形验证码错误");
        }
        String code = (String) request.getSession().getAttribute("signCode");
        if (!code.equals(secUser.getMsgCode()) ){
            return ResultGenerator.genFailResult("手机验证码错误");
        }

        secUser.setUserName(CodeHelper.getCode("心云魔方"));
        String inviterCode = CodeHelper.randomCode(8);
        SecUser isUser = null;
        if (inviterCode!=null){
             isUser = secUserService.findSecUserByInviterCode(inviterCode);
        }
        if (isUser != null){
            sign(json,request);

        }else {
            secUser.setInviterCode(inviterCode);
            Result result = secUserService.addUser(JSON.toJSONString(secUser));
            if (result.getCode() !=200){
                return result;
            }

            SecUser sessionUser = (SecUser) result.getData();

            UsernamePasswordToken token = new UsernamePasswordToken(secUser.getAccount(), secUser.getUserPass());
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSessionId", sessionUser.getId());
            session.setAttribute("userSession", sessionUser);

            return ResultGenerator.genSuccessResult(result.getData());
        }
        return ResultGenerator.genFailResult("注册失败");
    }

    /**
     * 发送注册手机验证码
     * @param request
     * @return
     */
    @PostMapping("/sign/code")
    @ResponseBody
    public Result signcode(HttpServletRequest request,String mobile) {
        request.getSession().setAttribute("signCode","1234");
//        try{
//            String json = SendMessageCode.sendMessageCode(mobile);
//            SMSCode smsCode = JSON.parseObject(json,SMSCode.class);
//            if (smsCode.getCode() == 200){
//                request.getSession().setAttribute("signCode",smsCode.getObj());
//            }else{
//                return ResultGenerator.genFailResult("验证码发生失败");
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//            return ResultGenerator.genFailResult("验证码发生失败");
//        }
        return ResultGenerator.genSuccessResult().setMessage("验证码发生成功");
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

        if (!captchaId.equals(vrifyCode) && !"".equals(captchaId)) {
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

//        try {
//            //将图片转换成base64
//            if(user.getHeadImg()!=null && !user.getHeadImg().equals("")){
//                user.setHeadImg(Base64ToImage.getImageStr(user.getHeadImg()));
//            }
//        }catch (Exception e) {
//            user.setHeadImg("");
//        }

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSessionId", user.getId());
        session.setAttribute("userSession", user);
        msgSend.send(MsgGenerator.genLoginLogMessage(user));
        return  "redirect:/index";
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


    @PostMapping("/sign/check/account")
    @ResponseBody
    public Result signCheckAccount(String mobile,String vrifyCode1,HttpServletRequest request){
        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        if (!captchaId.equals(vrifyCode1) && !"".equals(captchaId)) {
            return ResultGenerator.genFailResult("验证码错误！");
        }
        SecUser  user = secUserService.getSecUserInfo(mobile);
        if (user == null){
            return ResultGenerator.genFailResult("账号不存在！");
        }
        return ResultGenerator.genSuccessResult(mobile);
    }

    @PostMapping("/sign/reset/password")
    @ResponseBody
    public Result signResetPassword(String mobile,String password){

        SecUser  user = secUserService.getSecUserInfo(mobile);
        if (user == null){
            return ResultGenerator.genFailResult("重置密码失败");
        }
        user.setUserPass(password);
        PasswordHelper.encryptPassword(user);
        secUserService.update(user);
        return ResultGenerator.genSuccessResult().setMessage("重置密码成功");
    }

    @PostMapping("/sign/check/mobileCode")
    @ResponseBody
    public Result signCheckMobileCode(String mobile,String vrifyCode,HttpServletRequest request){
        if (!"".equals(vrifyCode) && !"".equals(mobile)){
            String signCode = (String) request.getSession().getAttribute("signCode");
            if (!signCode.equals(vrifyCode) && !"".equals(signCode)) {
                return ResultGenerator.genFailResult("手机验证码错误！");
            }
            return ResultGenerator.genSuccessResult(mobile);
        }else {
            return ResultGenerator.genFailResult("手机验证码错误");
        }
    }

    @PostMapping("/sign/paefactInfo")
    @ResponseBody
    public Result signPaefactInfo(String json){
        SecUser userJson = JSON.parseObject(json,SecUser.class);

        SecUser inviterUser = secUserService.findSecUserByInviterCode(userJson.getInviterCode());
        if (inviterUser == null){
            return ResultGenerator.genFailResult("邀请码不存在！");
        }
        SecUser secUser = secUserService.findById(userJson.getId());

        secUser.setUserName(userJson.getUserName());
        secUser.setEmail(userJson.getEmail());
        secUser.setInviterUser(inviterUser.getId());
        secUser.setAddr(userJson.getAddr());
        secUser.setHeadImg(userJson.getHeadImg());
        secUser.setChannelId(inviterUser.getChannelId());
        secUserService.update(secUser);

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSessionId", secUser.getId());
        session.setAttribute("userSession", secUser);

        return ResultGenerator.genSuccessResult();
    }
}

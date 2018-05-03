package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.channel.domain.ChannelManage;
import com.yidusoft.project.channel.service.ChannelManageService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.AliyunSMSUtil;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.PasswordHelper;
import com.yidusoft.utils.Security;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static com.yidusoft.utils.Security.getUser;

/**
 * Created by you on 2017/7/17.
 */
@Controller
public class IndexController {
    @Autowired
    SecUserService secUserService;


    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping(value ={"/index",""})
    public String main(){
        SecUser secUser = Security.getUser();
        if (secUser.getId()==null){
            return "login";
        }
        return link(secUser);
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
        return link(secUser);
    }

    public String link(SecUser secUser){
        if (0==secUser.getAccountType()){
            return  "index";
        }else if(1==secUser.getAccountType()){
            return  "project/cube/index";
        }else if(2==secUser.getAccountType()){

            return  "project/channelentrance/index";
        }
        return "/error/403";
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

        if(request.getSession().getAttribute("signCode")==null || request.getSession().getAttribute("signCode")==""){
            return ResultGenerator.genFailResult("请获取手机验证码");
        }
        String code = (String) request.getSession().getAttribute("signCode");

        if (!code.equals(secUser.getMsgCode()) ){
            return ResultGenerator.genFailResult("手机验证码错误");
        }
        String mobile = (String) request.getSession().getAttribute("mobile");
        if (!mobile.equals(secUser.getAccount()) ){
            return ResultGenerator.genFailResult("两次号码不一致，请重新输入");
        }
        secUser.setUserName(CodeHelper.getCode("yxmf"));
        String inviterCode = CodeHelper.randomCode(8);
        SecUser isUser = null;
        if (inviterCode!=null){
             isUser = secUserService.findSecUserByInviterCode(inviterCode);
        }
        if (isUser != null){
            sign(json,request);

        }else {
            secUser.setInviterCode(inviterCode);
            secUser.setMobile(secUser.getAccount());
            secUser.setHeadImg("/upload/headImg/default-pic.png");
            secUser.setAccountType(1);
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

    @Autowired
    private ChannelManageService channelManageService;

    /**
     * 渠道商注册
     * @param json
     * @return
     */
    @PostMapping("/sign/channel")
    @ResponseBody
    public Result signChannel(String json,HttpServletRequest request) throws  Exception{

        SecUser secUserJ = JSON.parseObject(json,SecUser.class);

        String captchaId = (String) request.getSession().getAttribute("vrifyCode");

        if (!captchaId.equals(secUserJ.getVrifyCode()) && !"".equals(captchaId)) {
            return ResultGenerator.genFailResult("图形验证码错误");
        }

        if(request.getSession().getAttribute("signCode")==null || request.getSession().getAttribute("signCode")==""){
            return ResultGenerator.genFailResult("请获取手机验证码");
        }
        String code = (String) request.getSession().getAttribute("signCode");

        if (!code.equals(secUserJ.getMsgCode()) ){
            return ResultGenerator.genFailResult("手机验证码错误");
        }

        ChannelManage channelManage = JSON.parseObject(json,ChannelManage.class);

        channelManage.setId(UUID.randomUUID().toString());
        channelManage.setChannelCode(CodeHelper.getCode("QD"));
        channelManage.setDeleted(0);
        channelManage.setCreateTime(new Date());

        SecUser secUser = new SecUser();
        secUser.setAccountType(2);
        secUser.setAccount(channelManage.getLinkmanTell());
        secUser.setUserName(channelManage.getLinkman());
        secUser.setSex(0);
        secUser.setStatus(0);
        secUser.setHeadImg("/upload/headImg/default-pic.png");
        secUser.setUserPass(secUserJ.getUserPass());
        secUser.setMobile(channelManage.getLinkmanTell());
        secUser.setChannelId(channelManage.getId());

        String inviterCode = CodeHelper.randomCode(8);
        SecUser isUser = null;
        if (inviterCode!=null){
            isUser = secUserService.findSecUserByInviterCode(inviterCode);
        }
        if (isUser != null){
            signChannel(json,request);
        }else {
            secUser.setInviterCode(inviterCode);
            return channelManageService.signChannel(secUser,channelManage);
        }
        return ResultGenerator.genFailResult("登记失败");
    }
    /**
     * 发送注册手机验证码
     * @param request
     * @return
     *
     * 模版名称       -  模版CODE:
     * 用户注册       - SMS_117010038
     * 登录确认       - SMS_117010040
     * 修改密码       - SMS_117010037
     * 参与活动       - SMS_117290172
     */
    @PostMapping("/sign/code")
    @ResponseBody
    public Result signcode(HttpServletRequest request,String mobile,String vrifyCode,String templateType) {

        String templateCode  = "";
        if(StringUtils.equals("用户注册",templateType)){
            templateCode= "SMS_117010038";
        }else  if (StringUtils.equals("登录确认",templateType)){
            templateCode= "SMS_117010040";
        }else  if (StringUtils.equals("修改密码",templateType)){
            templateCode= "SMS_117010037";
        }else  if (StringUtils.equals("参与活动",templateType)){
            templateCode= "SMS_117290172";
        }else {
            return ResultGenerator.genFailResult("验证码发送失败");
        }

        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        if (!captchaId.equals(vrifyCode) && !"".equals(captchaId)) {
            return ResultGenerator.genFailResult("图形验证码错误");
        }

        try{
            String signCode = getFourRandom();
            SendSmsResponse sendSmsResponse = AliyunSMSUtil.sendSms(mobile, templateCode,signCode);
            if (StringUtils.equals("OK",sendSmsResponse.getCode())){
                request.getSession().setAttribute("signCode",signCode);
                request.getSession().setAttribute("mobile",mobile);
                return ResultGenerator.genSuccessResult().setMessage("验证码发送成功");
            }else{
                return ResultGenerator.genFailResult("验证码发送失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("验证码发送失败");
        }
    }
    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
     */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++)
                fourRandom = "0" + fourRandom  ;
        }
        return fourRandom;
    }
    @RequestMapping(value="/indexInfo")
    public String indexInfo(){
        return "indexInfo";
    }


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

    @PostMapping("/sign/check/phone")
    @ResponseBody
    public Result signCheckPhone(String mobile){

        SecUser  user = secUserService.getSecUserInfo(mobile);
        if (user == null){
            return ResultGenerator.genSuccessResult(mobile);
        }
        return ResultGenerator.genFailResult(mobile);
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

    @RequestMapping(value = "/sign/channelin")
    public String channelin() {
        return "project/channelentrance/channel-in";
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
            String phone = (String) request.getSession().getAttribute("mobile");
            if (!phone.equals(mobile) && !"".equals(phone)) {
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
        SecUser secUser = secUserService.findById(userJson.getId());

        if (!"".equals(userJson.getInviterCode())){
            SecUser inviterUser = secUserService.findSecUserByInviterCode(userJson.getInviterCode());
            if (inviterUser == null){
                return ResultGenerator.genFailResult("邀请码不存在！");
            }
            secUser.setInviterUser(inviterUser.getId());
            secUser.setChannelId(inviterUser.getChannelId());
        }

        secUser.setUserName(userJson.getUserName());
        secUser.setEmail(userJson.getEmail());
        secUser.setAddr(userJson.getAddr());
        secUser.setProvince(userJson.getProvince());
        secUser.setCity(userJson.getCity());
        secUser.setDistrict(userJson.getDistrict());

        secUserService.update(secUser);

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSessionId", secUser.getId());
        session.setAttribute("userSession", secUser);
        return ResultGenerator.genSuccessResult();
    }


}

package com.yidusoft.project.cube.user.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.PasswordHelper;
import com.yidusoft.utils.SMSCode;
import com.yidusoft.utils.Security;
import com.yidusoft.utils.SendMessageCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.yidusoft.utils.Security.getUser;

/**
 * Created by yhdj on 2017/11/7.
 */

@RestController
@RequestMapping("/user")
public class UserModifyPsdController {

    @Autowired
    private SecUserService secUserService;

    /**
     * 判断密码是否与原密码相同
     *
     * @return
     */
    @PostMapping("/checkPassword")
    public Result checkPassword(String oldPassword) {
        SecUser secUser = secUserService.findById(Security.getUserId());
        SecUser newUser = new SecUser();
        newUser.setUserPass(oldPassword);
        PasswordHelper.encryptPassword(newUser);
        if (oldPassword != null && !"".equals(oldPassword)) {
            if (!newUser.getUserPass().equals(secUser.getUserPass())) {
                return ResultGenerator.genFailResult("密码与原密码不符合!");
            }
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/checkVrifyCode")
    public Result checkVrifyCode(HttpServletRequest request, String vrifyCode) {
        String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        if (!captchaId.equals(vrifyCode) && !"".equals(captchaId)) {
            return ResultGenerator.genFailResult("图形验证码错误！");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/modifyPassword")
    @ResponseBody
    public Result modifyPassword(String newPassword){
        if (newPassword == null || "".equals(newPassword)){
            System.out.println("===1====");
            return ResultGenerator.genFailResult("原密码错误！");

        }
        System.out.println("=======success=====");
        SecUser secUser = secUserService.findById(Security.getUserId());
        secUser.setUserPass(newPassword);
        PasswordHelper.encryptPassword(secUser);
        try {
            secUserService.update(secUser);
        }catch (Exception e){
            return ResultGenerator.genFailResult("修改密码失败！");
        }
        return ResultGenerator.genSuccessResult("修改密码成功！");

    }


    /**
     * 发送注册手机验证码
     * @param request
     * @return
     */
    @PostMapping("/modifyPsd/code")
    @ResponseBody
    public Result signcode(HttpServletRequest request, String mobile) {

        try{
            String json = SendMessageCode.sendMessageCode(mobile);
            SMSCode smsCode = JSON.parseObject(json,SMSCode.class);
            if (smsCode.getCode() == 200){
               request.getSession().setAttribute("signCode",smsCode.getObj());
            }else{
                return ResultGenerator.genFailResult("手机验证码发送失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("手机验证码发送失败");
        }
        return ResultGenerator.genSuccessResult().setMessage("手机验证码发送成功");
    }

    /**
     * 检查手机号码是否属于当前用户
     * @param mobile
     * @return
     */
    @PostMapping("/check/phone")
    @ResponseBody
    public Result checkPhone(String mobile){
        SecUser secUser = secUserService.findById(Security.getUserId());
        if (secUser.getMobile().equals(mobile)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("该手机号码不属于当前用户！");
        }
    }

    @PostMapping("/check/msgCode")
    @ResponseBody
    public Result checkMsgCode(HttpServletRequest request,String msgCode){
        String signCode = (String) request.getSession().getAttribute("signCode");
        System.out.println("signcode = " + signCode);
        System.out.println("msgcode = " + msgCode );
        if (signCode != null && !"".equals(signCode) && signCode.equals(msgCode)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("手机验证码错误！");
        }
    }

}

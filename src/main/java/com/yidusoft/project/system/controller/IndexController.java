package com.yidusoft.project.system.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.Security;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

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
        return  "index";
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
        return "index";
    }

    @RequestMapping(value="/indexInfo")
    public String indexInfo(){
        return "indexInfo";
    }

    @RequestMapping(value="/login",method= RequestMethod.POST)
    public String login(HttpServletRequest request, String username, String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "请输入用户名和密码！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "index";
        } catch (LockedAccountException lae) {
            token.clear();
            request.setAttribute("msg", "账号已被锁定，请与管理员联系！");
            return "login";
        } catch (UnknownAccountException uae) {
            token.clear();
            request.setAttribute("msg", "账号不存在！");
            return "login";
        } catch (AuthenticationException ae) {
            //用户名和密码不匹配时，切换为手机或邮箱登录
            SecUser secUser = null;
            try{
                secUser = secUserService.getSecUserInfo(username);
            }catch (Exception e){
                token.clear();
                request.setAttribute("msg", "账号异常，请联系管理员！");
                return "login";
            }
            if (secUser!=null){
                if(!"true".equals(request.getAttribute("flag"))){
                    request.setAttribute("flag","true");
                    login(request, secUser.getAccount(), password);
                }
            }
            token.clear();
            request.setAttribute("msg", "密码不正确！");
            return "login";
        }
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
            return ResultGenerator.genSuccessResult(Security.getUser());
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
}

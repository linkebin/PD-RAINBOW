package com.yidusoft.project.cube.user.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.JavaBeanUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

/**
 * Created by smy on 2017/10/19.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SecUserService secUserService;

    @PostMapping("/userUpdate")
    public Result userUpdate(String json){
        SecUser secUser = JSON.parseObject(json,SecUser.class);
        SecUser old = secUserService.findById(secUser.getId());
        JavaBeanUtil.mergeObject(secUser,old);
        try {
            secUserService.update(old);
        }catch (Exception e){
            return ResultGenerator.genFailResult("保存失败");
        }
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSessionId", old.getId());
        session.setAttribute("userSession", old);
        return ResultGenerator.genSuccessResult().setMessage("保存成功");
    }


}

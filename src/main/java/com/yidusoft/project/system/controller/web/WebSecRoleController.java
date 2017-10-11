package com.yidusoft.project.system.controller.web;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.service.SecRoleService;
import com.yidusoft.utils.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zcs on 2017/8/11.
 */

@Controller
@RequestMapping("/web/sec/role")
public class WebSecRoleController {

    @RequestMapping(value ="/linkList")
    public String linkList(){
        return  "project/system/secrole/SecRoleList";
    }

    /**
     * 打开角色权限页面
     * @return
     */
    @RequestMapping(value ="/linkPermission")
    public String permission(String roleId, Model model){
        model.addAttribute("roleId",roleId);
        return  "project/system/secrole/role-permission";
    }



    /**
     * 打开角色成员页面
     * @return
     */
    @RequestMapping(value ="/linkrolemember")
    public String linkrolemember(String roleId, Model model){
        model.addAttribute("roleId",roleId);
        return  "project/system/secrole/role-users";
    }
    @Autowired
    SecRoleService secRoleService;
    /**
     * 打开角色成员页面
     * @return
     */
    @RequestMapping(value ="/linkrolememberadd")
    public String linkrolememberadd( Model model){
        List<TreeNode> treeNodes=secRoleService.userTreeList();
        Map<String,Object> map=new HashMap<>();
        map.put("userList",treeNodes);
        Result result = ResultGenerator.genSuccessResult(map);
        model.addAttribute(result);
        return  "project/system/secrole/MemberDig";
    }
}

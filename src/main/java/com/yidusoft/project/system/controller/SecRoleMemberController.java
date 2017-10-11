package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecRoleMember;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecRoleMemberService;
import com.yidusoft.project.system.service.SecUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2017/07/18.
*/
@Controller
@RequestMapping("/sec/role/member")
public class SecRoleMemberController {
    @Resource
    private SecRoleMemberService secRoleMemberService;

    /**
     * 跳转到成员添加页面
     * @param id
     * @param roleName
     * @param model
     * @return
     */
    @RequestMapping(value ="/linkAdd")
    public String linkAdd(String id,String roleName,Model model){
        model.addAttribute("id",id);
        model.addAttribute("roleName",roleName);
        return  "secrolemember/secrolemember-add";
    }

    /**
     * 跳转到成员列表页面
     * @param id
     * @param roleName
     * @param model
     * @return
     */
    @RequestMapping(value ="/linkList")
    public String linkList(String id,String roleName,Model model){
        model.addAttribute("id",id);
        model.addAttribute("roleName",roleName);
        return  "secrolemember/secrolemember-list";
    }

    /**
     * 跳转到成员修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value ="/linkUpdate")
    public String linkUpdate(Model model, String id){
        SecRoleMember secRoleMember = secRoleMemberService.findById(id);
        model.addAttribute("secRoleMember",secRoleMember);
        return  "secrolemember/secrolemember-update";
    }

    @Resource
    private SecUserService secUserService;

    /**
     * 查询出角色的成员列表
     * @param roleId
     * @return
     */
    @PostMapping("/memberList")
    @ResponseBody
    public Result memberListlist(String roleId, int page, int pagesize) {

       // int total = secUserService.userListByRoleId(roleId).size();

        PageHelper.startPage(page,pagesize);
        List<SecUser> secUsers = secUserService.userListByRoleId(roleId);
        PageInfo pageInfo = new PageInfo(secUsers);
       // pageInfo.setTotal(total);

        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 查询用户列表
     * @param id
     * @return
     */
    @PostMapping("/userList")
    @ResponseBody
    public Map<String,Object> userListlist(String id,String orgId) {
        List<SecUser> secUsers = new ArrayList<SecUser>();
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> parmMap = new HashMap<String,Object>();
        if(orgId!=""){
            String arr[] = orgId.split(",");
            for (int i = 0; i < arr.length; i++) {
                parmMap.put("id",id);
                parmMap.put("orgId",arr[i]);
                List<SecUser> list = secUserService.roleChooseUser(parmMap);
                secUsers.addAll(list);
            }
        } else {
            parmMap.put("id",id);
            List<SecUser> list = secUserService.roleChooseUser(parmMap);
            secUsers.addAll(list);
        }
        map.put("Rows",secUsers);
        map.put("Total",secUsers.size());
        return map;
    }

    /**
     * 修改成员
     * @param secRoleMember
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(SecRoleMember secRoleMember) {
        secRoleMemberService.update(secRoleMember);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 批量添加成员
     * @param secRoleMemberJson
     * @return
     */
    @PostMapping("/addRoleMember")
    @ResponseBody
    public Result add(String secRoleMemberJson) {
        SecRoleMember secRoleMember = JSON.parseObject(secRoleMemberJson,SecRoleMember.class);
        String arr [] = (secRoleMember.getUserId()).split(",");
        for(String str : arr) {
            synchronized(this){
                SecRoleMember secRoleMember1 = new SecRoleMember();
                secRoleMember1.setUserId(str);
                secRoleMember1.setRoleId(secRoleMember.getRoleId());
                int count = secRoleMemberService.findRoleMemberIsExits(secRoleMember1);
                if (count == 0){
                    secRoleMember.setUserId(str);
                    secRoleMember.setId(UUID.randomUUID().toString());
                    secRoleMemberService.save(secRoleMember);
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 用户添加角色
     * @param secRoleMemberJson
     * @return
     */
    @PostMapping("/userAddRoles")
    @ResponseBody
    public Result userAddRoles(String secRoleMemberJson) {
        SecRoleMember secRoleMember = JSON.parseObject(secRoleMemberJson,SecRoleMember.class);
        String arr [] = (secRoleMember.getRoleId()).split(",");
        for(String str : arr) {
            secRoleMember.setRoleId(str);
            secRoleMember.setId(UUID.randomUUID().toString());
            secRoleMemberService.save(secRoleMember);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除用户角色
     * @param secRoleMemberJson
     * @return
     */
    @PostMapping("/userDeleteRoles")
    @ResponseBody
    public Result userDeleteRoles(String secRoleMemberJson) {
        SecRoleMember secRoleMember = JSON.parseObject(secRoleMemberJson,SecRoleMember.class);
        String arr [] = (secRoleMember.getRoleId()).split(",");
        for(String str : arr) {
            secRoleMember.setRoleId(str);
            secRoleMember.setId(UUID.randomUUID().toString());
            secRoleMemberService.deleteUserIdAndRoleId(secRoleMember);
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 添加部门
     * @param secUser
     * @return
     */
    @PostMapping("/addOrg")
    @ResponseBody
    public Result addOrg(SecUser secUser, String roleId) {
        if(secUser!=null && roleId!=null && roleId!="") {
            synchronized (this){
                String arr [] = secUser.getOrgId().split(",");
                for(String orgId : arr) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("roleId",roleId);
                    map.put("orgId",orgId);
                    List<String> memberList = secRoleMemberService.getMemberAll(map);
                    for(String userId : memberList){
                        SecRoleMember secRoleMember = new SecRoleMember();
                        secRoleMember.setId(UUID.randomUUID().toString());
                        secRoleMember.setUserId(userId);
                        secRoleMember.setRoleId(roleId);
                        secRoleMemberService.save(secRoleMember);
                    }
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 批量删除角色的成员
     * @param secRoleMemberJson
     * @return
     */
    @PostMapping("/deleteTrue")
    @ResponseBody
    public Result deleteTrue(String secRoleMemberJson) {
        SecRoleMember secRoleMember = JSON.parseObject(secRoleMemberJson,SecRoleMember.class);

        String arr [] = secRoleMember.getUserId().split(",");
        for(String str : arr) {
            secRoleMember.setUserId(str);
            secRoleMemberService.deleteUserIdAndRoleId(secRoleMember);
        }
        return ResultGenerator.genSuccessResult();
    }




    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecRoleMember> list = secRoleMemberService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

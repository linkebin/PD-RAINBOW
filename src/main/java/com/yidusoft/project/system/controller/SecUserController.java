package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.*;
import com.yidusoft.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
* Created by CodeGenerator on 2017/07/18.
*/
@Controller
@RequestMapping("/sec/user")
public class SecUserController {
    @Resource
    private SecUserService secUserService;

    @Resource
    private SecMenuMemberService secMenuMemberService;


    @PostMapping("/updateUserInfoPass")
    @ResponseBody
    public Result updateUserInfoPass(String json) {
        SecUser secUser = JSON.parseObject(json,SecUser.class);
        PasswordHelper.encryptPassword(secUser);
        SecUser secUser1 = secUserService.findById(Security.getUserId());
        if (secUser.getUserPass().equals(secUser1.getUserPass())){
            secUser1.setUserPass(secUser.getUserName());
            PasswordHelper.encryptPassword(secUser1);
            secUserService.update(secUser1);
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("旧密码不正确");
        }
    }


    @PostMapping("/updateUserPass")
    @ResponseBody
    public Result updateUserPass(String json) {
        SecUser secUser = JSON.parseObject(json,SecUser.class);
        SecUser secUser1 = secUserService.findById(secUser.getId());
        secUser1.setUserPass(secUser.getUserPass());
        PasswordHelper.encryptPassword(secUser1);
        secUserService.update(secUser1);
        return ResultGenerator.genSuccessResult(secUser);
    }

    @PostMapping("/findSecUserOrgInfos")
    @ResponseBody
    public Result findSecUserOrgInfos(String ids) {
        String arr[] = ids.split(",");
        List<String> idslist = new ArrayList<String>();
        for (String s :arr){
            idslist.add(s);
        }
        return ResultGenerator.genSuccessResult(secUserService.findSecUserOrgInfos(idslist));
    }

    /**
     * 分页获取组织内用户列表
     * @param
     * @return
     */
    @PostMapping("/userList")
    @ResponseBody
    public Result userList(String userJson, int page, int size) {
        Map<String,Object> map = new HashMap<String,Object>();
        SecUser secUser = JSON.parseObject(userJson,SecUser.class);
        int total = secUserService.findOrgByUsers(secUser).size();

        PageHelper.startPage(page, size);
        List<SecUser> secUsers = secUserService.findOrgByUsers(secUser);
        map.put("Rows",secUsers);
        map.put("Total",total);
        return ResultGenerator.genSuccessResult(map);
    }


    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(int page,
                             int size, String params) {
        SecUser secUser = JSON.parseObject(params,SecUser.class);
        //查询分页数据
        int total = secUserService.getQueryAll(secUser).size();

        PageHelper.startPage(page,size);

        List<SecUser> secUsers = secUserService.getQueryAll(secUser);

        PageInfo pageInfo = new PageInfo(secUsers);
        pageInfo.setTotal(total);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @RequestMapping(value="/linkAddRole",method= RequestMethod.GET)
    public String linkAddRole(Model model, String id){
        model.addAttribute("id",id);
        return "secuser/user-role-add";
    }



    @RequestMapping(value ="/loadRole")
    public String loaduserrole(Model model, String id){
        model.addAttribute("id",id);
        return  "secuser/load-user-role";
    }

    @RequestMapping(value ="/linkUpdate")
    public String linkUpdate(Model model, String id){
        SecUser secUser = secUserService.findById(id);
        model.addAttribute("secUser",secUser);
        return  "secuser/secuser-update";
    }

    @PostMapping("/checkAccount")
    @ResponseBody
    public Result checkAccount(String account) {
        return ResultGenerator.genSuccessResult(secUserService.isSecUser1(account));
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(String params) {
        return secUserService.addUser(params);
    }


   //把数据永久删除
    @PostMapping("/deleteTrue")
    @ResponseBody
    public Result deleteTrue(String  id) {
        secUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    //逻辑删除，数据还在
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        SecUser secUser = secUserService.findById(id);
        secUser.setDeleted(1);
        secUserService.update(secUser);
        return ResultGenerator.genSuccessResult();
    }


    //批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            SecUser secUser = secUserService.findById(str);
            if (secUser!=null){
                secUser.setDeleted(1);
                secUserService.update(secUser);
            }
        }
        return ResultGenerator.genSuccessResult("删除成功！");
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(String params) {
        JSONObject jsonObject = JSONObject.parseObject(params);
        if (jsonObject.get("id")==null){
            jsonObject.put("id", Security.getUserId());
        }
        params = JSONObject.toJSONString(jsonObject);

        secUserService.updateUser(params);
            //更新
            SecUser user = secUserService.getSecUserInfo(Security.getUser().getAccount());
            if (user!=null){
                if(user.getHeadImg()!=null && !"".equals(user.getHeadImg())){
                    //将图片转换成base64
                    user.setHeadImg(Base64ToImage.getImageStr(user.getHeadImg()));
                }

            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSessionId", user.getId());
            session.setAttribute("userSession", user);
        }

       return secUserService.updateUser(params);
    }

    @PostMapping("/userAddorg")
    @ResponseBody
    public Result userAddorg(String ids, String orgId) {

        String arr [] = ids.split(",");
        for(String s:arr){
          SecUser secUser =  secUserService.findById(s);
          if(orgId.equals("0")){
              secUser.setOrgId(null);
          }else{
              secUser.setOrgId(orgId);
          }

            secUserService.update(secUser);
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/detail")
    @ResponseBody
    public Result detail(String id) {
        SecUser secUser = secUserService.findUserInfoAndOrgInfoById(id);
        return ResultGenerator.genSuccessResult(secUser);
    }

    @Resource
    private SecMenuRoleService secMenuRoleService;
    @Resource
    private SecMenuService secMenuService;

    /**
     * 根据用户查出菜单树
     * @param userId
     * @return
     */
    @PostMapping("/menuTreeByUser")
    @ResponseBody
    public Result menuTreeByUser(String userId) {
        List<TreeNode> treeNodes = secMenuService.getTree(userId);
        List<SecMenu> menuList = secMenuMemberService.findMenuByUser(userId);
        List<SecMenu> menuListRole = secMenuMemberService.findMenuByUserRole(userId);

        for (SecMenu menu : menuList) {
            for (TreeNode treeNode : treeNodes) {
                if (menu.getId().equals(treeNode.getId())) {
                    treeNode.setFlag("1");//当前用户自身权限
                }
            }
        }
        for (SecMenu menu : menuListRole) {
            for (TreeNode treeNode : treeNodes) {
                if (menu.getId().equals(treeNode.getId())) {
                    treeNode.setFlag("0");//当前用户角色权限
                }
            }
        }
        return ResultGenerator.genSuccessResult(treeNodes);
    }

    @PostMapping("/list1")
    @ResponseBody
    public Map<String,Object> list1() {
        List<SecUser> list = secUserService.getQueryAll(new SecUser());
        Map<String,Object> map = new HashMap<String,Object>();


        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }

    @PostMapping("/list")
    @ResponseBody
    public Result list(String params) {
        SecUser secUser = JSON.parseObject(params, SecUser.class);
        List<SecUser> secUsers = new ArrayList<SecUser>();
        if (secUser.getOrgId()!="") {
            String arr[] = secUser.getOrgId().split(",");
            for (int i = 0; i < arr.length; i++) {
                secUser.setOrgId(arr[i]);
                List<SecUser> list = secUserService.getQueryAll(secUser);
                secUsers.addAll(list);
            }
        }else{
            List<SecUser> list = secUserService.getQueryAll(secUser);
            secUsers.addAll(list);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("Rows",secUsers);
        map.put("Total",secUsers.size());
        return ResultGenerator.genSuccessResult(map);
    }

    @Resource
    private SecRoleService secRoleService;

    @PostMapping("/rolelist")
    @ResponseBody
    public Result rolelist(String id) {
        List<SecRole> secRoles = secRoleService.getSecUserRoles(id);
        PageInfo pageInfo = new PageInfo(secRoles);
        pageInfo.setTotal(10000);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取用户未存在的角色列表
     * @param id
     * @return
     */
    @PostMapping("/getUserNoRoleList")
    @ResponseBody
    public Result getUserNoRoleList(String id) {
        List<SecRole> secRoles = secUserService.getUserNoRoleList(id);
        PageInfo pageInfo = new PageInfo(secRoles);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/deleteOrgUser")
    @ResponseBody
    public Result deleteOrgUser(String ids) {

        String arr [] = ids.split(",");
        for(String str : arr){
           SecUser secUser = secUserService.findById(str);
           secUser.setOrgId("");
           secUserService.update(secUser);
        }

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/userTreeList")
    @ResponseBody
    public Result userTreeList(String id) {
        List<TreeNode> list = secUserService.userTreeList(id);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/leadingTreeList")
    @ResponseBody
    public Result leadingTreeList(String json){
        SecUser secUser = JSON.parseObject(json,SecUser.class);
        return  ResultGenerator.genSuccessResult(secUserService.leadingTreeList(secUser));
    }


}

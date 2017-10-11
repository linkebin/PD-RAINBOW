package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuRole;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.service.SecMenuRoleService;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.project.system.service.SecRoleMemberService;
import com.yidusoft.project.system.service.SecRoleService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import com.yidusoft.utils.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Controller
@RequestMapping("/sec/role")
public class SecRoleController {
    //@Resource
    //private ShiroService shiroService;
    @Resource
    private SecRoleService secRoleService;
    @Resource
    private SecRoleMemberService secRoleMemberService;
    @Resource
    private SecMenuRoleService secMenuRoleService;
    @Resource
    private SecMenuService secMenuService;
    /**
     * 跳转到角色添加页面
     * @return
     */
    @RequestMapping(value ="/linkAdd")
    public String linkAdd(){
        return  "secrole/secrole-add";
    }

    /**
     * 跳转到角色列表页面
     * @return
     */
    @RequestMapping(value ="/linkList")
    public String linkList(){
        return  "secrole/secrole-list";
    }

    /**
     * 跳转到角色修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value ="/linkUpdate")
    public String linkUpdate(Model model, String id){
        SecRole secRole = secRoleService.findById(id);
        model.addAttribute("secRole",secRole);
        return  "secrole/secrole-update";
    }

    /**
     * 角色增加
     * @param secRoleJson
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(String secRoleJson) {
        SecRole secRole = JSON.parseObject(secRoleJson,SecRole.class);
        synchronized (this){
            secRole.setRoleCode(CodeHelper.getCode("R"));
            secRole.setId(UUID.randomUUID().toString());
            secRole.setCreator(Security.getUser().getUserName());
            secRole.setCreateTime(new Date());
            secRole.setDeleted(0);
            secRoleService.save(secRole);
        }
        return ResultGenerator.genSuccessResult(secRole);
    }

    /**
     * 角色列表数据
     * @param secRole
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> list(SecRole secRole) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<SecRole> list = new ArrayList<>();
        if(secRole.getRoleName()!=null && secRole.getRoleName()!=""){
            list = secRoleService.getAll(secRole);
        }else {
            list = secRoleService.getRole();
        }
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }

    /**
     * 角色修改
     * @param secRoleJson
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result update(String secRoleJson) {
        SecRole secRole = JSON.parseObject(secRoleJson,SecRole.class);
        SecRole srcRole1 = secRoleService.findById(secRole.getId());
        srcRole1.setRoleName(secRole.getRoleName());
        secRoleService.update(srcRole1);
        return ResultGenerator.genSuccessResult(srcRole1);
    }

    //逻辑删除，数据还在 批量删除

    /**
     * 角色批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            SecRole secRole = secRoleService.findById(str);
            secRole.setDeleted(1);
            secRoleService.update(secRole);
            secRoleMemberService.deleteByRoleId(str);
            secMenuRoleService.deleteByRoleId(str);
            //更新权限
//            shiroService.updatePermission();
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 查询角色名称是否存在
     * @param roleName
     * @return
     */
    @PostMapping("/checkRoleName")
    @ResponseBody
    public Result checkRoleName(String roleName) {
        List<String> list = secRoleService.findByRoleName(roleName);
        if(list.size()>0){
            return ResultGenerator.genSuccessResult(1);
        }
        return ResultGenerator.genSuccessResult();
    }

    //把数据永久删除
    @PostMapping("/deleteTrue")
    @ResponseBody
    public Result deleteTrue(String  id) {
        secRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    //逻辑删除，数据还在
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        SecRole secRole = secRoleService.findById(id);
        secRole.setDeleted(1);
        secRoleService.update(secRole);
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/detail")
    @ResponseBody
    public Result detail(@PathVariable String id) {
        SecRole secRole = secRoleService.findById(id);
        return ResultGenerator.genSuccessResult(secRole);
    }

    /**
     * 角色和成员的权限
     * @param id
     * @return
     */
    @PostMapping("/roleAndMemberList")
    @ResponseBody
    public  List<TreeNode> roleAndMemberList(String id) {
        List<TreeNode> nodes =secMenuService.menuTree();
        List<SecMenuRole> secMenuRoles = secMenuRoleService.findRoleMenu(id);
        for(SecMenuRole secMenuRole : secMenuRoles){
            for(TreeNode treeNode : nodes){
                if(secMenuRole.getMenuId().equals(treeNode.getId())){
                    treeNode.setFlag("0");//角色权限
                }
            }
        }
        return nodes;
    }

    @PostMapping("/roleMenuhList")
    @ResponseBody
    public Map<String,Object> roleMenuhList(String id) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<SecMenuRole> secMenuRoles = secMenuRoleService.findRoleMenu(id);
        map.put("roles",secMenuRoles);
        return map;
    }

    /**
     * 分页获取某公司下的全部角色信息列表
     * @param roleJson
     * @param page
     * @param pagesize
     * @return
     */
    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(String roleJson, int page, int pagesize) {

        SecRole secRole = JSON.parseObject(roleJson,SecRole.class);
       // int  total = secRoleService.findRoleByCompanyId(secRole).size();
        PageHelper.startPage(page, pagesize);
        List<SecRole> secRoles = secRoleService.findRoleByCompanyId(secRole);
        PageInfo pageInfo = new PageInfo(secRoles);
       // pageInfo.setTotal(total);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据角色查出菜单树
     * @param roleId
     * @return
     */
    @PostMapping("/menuTreeByRole")
    @ResponseBody
    public Result menuTreeByRole(String roleId, String userId) {
        List<TreeNode> treeNodes = secMenuService.getTree(userId);
        List<SecMenu> menuList = secMenuService.findCompanyRoleMenuByRoleId(roleId);
        for (SecMenu menu : menuList) {
            for (TreeNode treeNode : treeNodes) {
                if (menu.getId().equals(treeNode.getId())) {
                    treeNode.setFlag("0");//绑定角色拥有的权限
                }
            }
        }
        return ResultGenerator.genSuccessResult(treeNodes);
    }


}

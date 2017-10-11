package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuRole;
import com.yidusoft.project.system.service.SecMenuRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2017/07/18.
*/
@Controller
@RequestMapping("/sec/menu/role")
public class SecMenuRoleController {
    @Resource
    private SecMenuRoleService secMenuRoleService;

    /**
     * 更新角色的权限
     * @param secMenuRoleJson
     * @return
     */
    @PostMapping("/saveMenuRole")
    @ResponseBody
    public Result saveMenuRole(String secMenuRoleJson){
        SecMenuRole secMenuRole = JSON.parseObject(secMenuRoleJson,SecMenuRole.class);
        /*
        模拟前端传递的参数
        secMenuRole.setMenuId("1,2,3,4,5");
        secMenuRole.setRoleId("2");
         */
        /*if(StringUtils.isEmpty(secMenuRole.getRoleId()))*/
        try {
            if(secMenuRole!=null) {
                secMenuRoleService.addMenuRole(secMenuRole);
                return ResultGenerator.genSuccessResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除角色的权限
     * @param secMenuRole
     * @return
     */
    @PostMapping("/deleteMenuRole")
    @ResponseBody
    public void deleteMenuRole(SecMenuRole secMenuRole){
        System.out.println(secMenuRole.getRoleId());
        try {
            System.out.println("roleId:"+secMenuRole.getRoleId());
            secMenuRoleService.deleteMenuRole(secMenuRole);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询某个角色的权限
     * @param roleId
     * @return
     */
    @PostMapping("/queryMenuRole")
    @ResponseBody
    public Map<String,Object> queryMenuRole(String roleId){
        List<SecMenu> list = secMenuRoleService.findByRole(roleId);
        Map<String,Object> map = new HashMap<String,Object>();
        for (SecMenu secMenu : list){
            System.out.println("循环bianhao:"+secMenu.getMenuCode());
            System.out.println("循环Name:"+secMenu.getMenuName());
            System.out.println("循环id:"+secMenu.getId());
        }
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }

    @RequestMapping(value ="/linkAdd")
    public String linkAdd(){
        return  "secmenurole/secmenurole-add";
    }

    @RequestMapping(value ="/linkList")
    public String linkList(String id,Model model){
        model.addAttribute("id",id);
        return  "secmenurole/secmenurole-list";
    }

    @RequestMapping(value ="/linkUpdate")
    public String linkUpdate(Model model, String id){
        SecMenuRole secMenuRole = secMenuRoleService.findById(id);
        model.addAttribute("secMenuRole",secMenuRole);
        return  "secmenurole/secmenurole-update";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(SecMenuRole secMenuRole) {
        secMenuRoleService.save(secMenuRole);
        return ResultGenerator.genSuccessResult();
    }

   //把数据永久删除
    @PostMapping("/deleteTrue")
    @ResponseBody
    public Result deleteTrue(String  id) {
        secMenuRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    //逻辑删除，数据还在
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        SecMenuRole secMenuRole = secMenuRoleService.findById(id);
//        secMenuRole.setDeleted(1);
        secMenuRoleService.update(secMenuRole);
        return ResultGenerator.genSuccessResult();
    }


    //逻辑删除，数据还在 批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            SecMenuRole secMenuRole = secMenuRoleService.findById(str);
//            secMenuRole.setDeleted(1);
            secMenuRoleService.update(secMenuRole);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(SecMenuRole secMenuRole) {
        secMenuRoleService.update(secMenuRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    @ResponseBody
    public Result detail(@PathVariable String id) {
        SecMenuRole secMenuRole = secMenuRoleService.findById(id);
        return ResultGenerator.genSuccessResult(secMenuRole);
    }


    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> list() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<SecMenuRole> list = secMenuRoleService.findAll();
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }


    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecMenuRole> list = secMenuRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}

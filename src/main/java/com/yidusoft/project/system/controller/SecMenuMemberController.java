package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuMember;
import com.yidusoft.project.system.service.SecMenuMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/07/18.
*/
@Controller
@RequestMapping("/sec/menu/member")
public class SecMenuMemberController {
    @Resource
    private SecMenuMemberService secMenuMemberService;

//    @Autowired
//    private
//
// userRealm;对角色或者用户或者组织进行资源重新分配时需要清除权限缓存

    /**
     * 更新用户的权限
     * @param secMenuMember
     * @return
     */
    @RequestMapping("/saveMenuMember")
    public String saveMenuMember(SecMenuMember secMenuMember){
        if(StringUtils.isEmpty(secMenuMember.getUserId()))
            return "error";
        try {
            secMenuMemberService.addMenuMember(secMenuMember);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 删除用户的权限
     * @param secMenuMember
     * @return
     */
    @RequestMapping("/deleteMenuRole")
    public String deleteMenuRole(SecMenuMember secMenuMember){
        if(StringUtils.isEmpty(secMenuMember.getUserId()))
            return "error";
        try {
            secMenuMemberService.deleteMenuMember(secMenuMember);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * 查询某个用户的权限
     * @param userId
     * @return
     */
    @RequestMapping("/queryMenuMember")
    public Result queryMenuMember(String userId){
        if(StringUtils.isEmpty(userId))
            return ResultGenerator.genFailResult("未选中用户");

        List<SecMenu> menuList = secMenuMemberService.findMenuByUser(userId);
        for (SecMenu menu : menuList){
            System.out.println(menu.getId());
        }
        return ResultGenerator.genSuccessResult(menuList);
    }


    @RequestMapping(value ="/linkAdd")
    public String linkAdd(){
        return  "secmenumember/secmenumember-add";
    }

    @RequestMapping(value ="/linkList")
    public String linkList(){
        return  "secmenumember/secmenumember-list";
    }

    @RequestMapping(value ="/linkUpdate")
    public String linkUpdate(Model model, String id){
        SecMenuMember secMenuMember = secMenuMemberService.findById(id);
        model.addAttribute("secMenuMember",secMenuMember);
        return  "secmenumember/secmenumember-update";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(SecMenuMember secMenuMember) {
        secMenuMemberService.save(secMenuMember);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/bacthAdd")
    @ResponseBody
    public Result bacthAdd(String params) {
        SecMenuMember secMenuMember = JSON.parseObject(params, SecMenuMember.class);
        if(!secMenuMember.getMenuId().equals("")){
            String arr [] =secMenuMember.getMenuId().split(",");
            if(arr.length>0) {
                secMenuMemberService.deleteByUserIdAll(secMenuMember.getUserId());
                for (int i = 0; i < arr.length; i++) {
                    secMenuMember.setId(UUID.randomUUID().toString());
                    secMenuMember.setMenuId(arr[i]);
                    secMenuMemberService.save(secMenuMember);
                }
            }
        }else{
            secMenuMemberService.deleteByUserIdAll(secMenuMember.getUserId());
        }
        //userRealm.clearCached();对角色或者用户或者组织进行资源重新分配时需要清除权限缓存
        return ResultGenerator.genSuccessResult();
    }

   //把数据永久删除
    @PostMapping("/deleteTrue")
    @ResponseBody
    public Result deleteTrue(String  id) {
        secMenuMemberService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    //逻辑删除，数据还在
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        SecMenuMember secMenuMember = secMenuMemberService.findById(id);
//        secMenuMember.setDeleted(1);
        secMenuMemberService.update(secMenuMember);
        return ResultGenerator.genSuccessResult();
    }


    //逻辑删除，数据还在 批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            SecMenuMember secMenuMember = secMenuMemberService.findById(str);
//            secMenuMember.setDeleted(1);
            secMenuMemberService.update(secMenuMember);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(SecMenuMember secMenuMember) {
        secMenuMemberService.update(secMenuMember);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    @ResponseBody
    public Result detail(@PathVariable String id) {
        SecMenuMember secMenuMember = secMenuMemberService.findById(id);
        return ResultGenerator.genSuccessResult(secMenuMember);
    }


    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> list() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<SecMenuMember> list = secMenuMemberService.findAll();
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }


    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecMenuMember> list = secMenuMemberService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

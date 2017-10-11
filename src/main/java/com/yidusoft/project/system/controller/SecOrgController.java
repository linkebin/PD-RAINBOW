package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuOrg;
import com.yidusoft.project.system.domain.SecOrg;
import com.yidusoft.project.system.domain.SecOrgForParameter;
import com.yidusoft.project.system.service.SecMenuOrgService;
import com.yidusoft.project.system.service.SecOrgService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import com.yidusoft.utils.TreeBuilder;
import com.yidusoft.utils.TreeNode;
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
@RequestMapping("/sec/org")
public class SecOrgController {
    @Resource
    private SecOrgService secOrgService;

    @Resource
    private SecMenuOrgService secMenuOrgService;

    @PostMapping("/findOrgParentStringIds")
    @ResponseBody
    public Result findOrgParentStringIds(String orgId){
        return ResultGenerator.genSuccessResult(secOrgService.findOrgParentStringIds(orgId));
    }

    /**
     * 保存公司组织的菜单
     * @param json
     * @return
     */
    @PostMapping("/addSecMenuOrg")
    @ResponseBody
    public Result addSecMenuOrg(String json){
        SecMenuOrg secMenuOrg = JSON.parseObject(json,SecMenuOrg.class);
        String arr[] = secMenuOrg.getMenuId().split(",");
        try {
            secMenuOrgService.deleteOrgMenuAllByOrgId(secMenuOrg.getOrgId());
            for (String s : arr){
                SecMenuOrg secMenuOrg1 = new SecMenuOrg();
                secMenuOrg1.setId(UUID.randomUUID().toString());
                secMenuOrg1.setOrgId(secMenuOrg.getOrgId());
                secMenuOrg1.setMenuId(s);
                secMenuOrgService.save(secMenuOrg1);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("保存失败");
        }
        return ResultGenerator.genSuccessResult();
    }


    /**
     * 后台管理组织获取已经分配的菜单
     * @param json
     * @return
     */
    @PostMapping("/findOrgAllotMenuList")
    @ResponseBody
    public Result findOrgAllotMenuList(String json){
        SecMenu secMenu = JSON.parseObject(json,SecMenu.class);

        return ResultGenerator.genSuccessResult(secOrgService.findOrgAllotMenuList(secMenu));
    }

    @PostMapping("/tree")
    @ResponseBody
    public Result tree() {
        List<TreeNode> nodes =secOrgService.getTree(Security.getUserId());
        List<TreeNode> treeNodes = TreeBuilder.buildByRecursive(nodes);
        Result result = ResultGenerator.genSuccessResult(treeNodes);
        return result;
    }

    /**
     * 后台管理全部组织
     * @param
     * @return
     */
    @PostMapping("/manageTree")
    @ResponseBody
    public Result manageTree() {

        List<SecOrg> secOrgs =secOrgService.getSecOrgList(new SecOrg());
        Result result = ResultGenerator.genSuccessResult(secOrgs);
        return result;
    }

    /**
     * 分页获取组织--后台管理
     * @param
     * @return
     */
    @PostMapping("/orgList")
    @ResponseBody
    public Result orgList(String orgJson, int page, int pagesize) {
        Map<String,Object> map = new HashMap<String,Object>();
        SecOrg secOrg = JSON.parseObject(orgJson,SecOrg.class);
        int total = secOrgService.getSecOrgList(secOrg).size();
        PageHelper.startPage(page, pagesize);
        List<SecOrg> secOrgs = secOrgService.getSecOrgList(secOrg);
        map.put("Rows",secOrgs);
        map.put("Total",total);
        return ResultGenerator.genSuccessResult(map);
    }


    @PostMapping("/listByPages")
    @ResponseBody
    public Result listByPages(int page,
                              int size, String params) {
        SecOrgForParameter secOrg = JSON.parseObject(params,SecOrgForParameter.class);
        //查询分页数据
        int total=secOrgService.getQueryAllTotal(secOrg);

        PageHelper.startPage(page,size);

        List<SecOrg> secOrgList=secOrgService.getQueryAll(secOrg);
        PageInfo pageInfo = new PageInfo(secOrgList);
        pageInfo.setTotal(total);
        return   ResultGenerator.genSuccessResult(pageInfo);
    }


    @RequestMapping(value ="/lookUser")
    public String lookUser(Model model, String id){
        Map<String,Object> map = new HashMap<String,Object>();
        model.addAttribute("id",id);
        return  "secorg/look-user";
    }

    @RequestMapping(value ="/loadUser")
    public String loadUser(Model model, String id){
        Map<String,Object> map = new HashMap<String,Object>();
        model.addAttribute("id",id);
        return  "secorg/load-user";
    }


    @RequestMapping(value ="/orgLoad")
    public String orgLoad(Model model){
        return  "secorg/load-user";
    }


    /**
     * 获取编辑组织的信息
     * @param orgId
     * @return
     */
    @RequestMapping(value ="/findOrgUpdateInfo")
    @ResponseBody
    public Result findOrgUpdateInfo(String orgId){
        SecOrg secOrg = secOrgService.findById(orgId);
        return  ResultGenerator.genSuccessResult(secOrg);
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(String secOrgJson) {
        SecOrg secOrg=JSON.parseObject(secOrgJson,SecOrg.class);
        secOrg.setCreateTime(new Date());
        secOrg.setId(UUID.randomUUID().toString());
        secOrg.setOrgCode(CodeHelper.getCode("ZZ"));
        secOrg.setDeleted(0);
        secOrgService.save(secOrg);
        return ResultGenerator.genSuccessResult();
    }



    //逻辑删除，数据还在
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        SecOrg secOrg = secOrgService.findById(id);
        secOrg.setDeleted(1);
        secOrgService.update(secOrg);
        return ResultGenerator.genSuccessResult();
    }


    //逻辑删除，数据还在 批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            SecOrg secOrg = secOrgService.findById(str);
            secOrg.setDeleted(1);
            secOrgService.update(secOrg);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(String secOrgJson) {
        SecOrg secOrg=JSON.parseObject(secOrgJson,SecOrg.class);
        SecOrg secOrg1=secOrgService.findById(secOrg.getId());
        secOrg1.setOrgName(secOrg.getOrgName());
        secOrgService.update(secOrg1);
        return ResultGenerator.genSuccessResult(secOrg1);
    }

    /**
     * 验证某公司下的组织名称是否重复
     * @param orgName 名称
     * @param orgTopId 公司
     * @return
     */
    @PostMapping("/checkedOrgName")
    @ResponseBody
    public Result checkedOrgName(String orgName, String orgTopId) {
       int i = secOrgService.findOrgNameCheckedIsExist(orgName,orgTopId);
        return ResultGenerator.genSuccessResult(i);
    }


    @PostMapping("/detail")
    @ResponseBody
    public Result detail(String id) {
        SecOrg secOrg = secOrgService.findById(id);
        return ResultGenerator.genSuccessResult(secOrg);
    }


    @PostMapping("/list")
    @ResponseBody
    public Result list(String params) {
        SecOrg secOrg = JSON.parseObject(params, SecOrg.class);
        Map<String,Object> map = new HashMap<String,Object>();
        List<SecOrg> list = secOrgService.getSecOrgList(secOrg);
        map.put("Rows",list);
        map.put("Total",list.size());
        return ResultGenerator.genSuccessResult(map);
    }


    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecOrg> list = secOrgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuForParameter;
import com.yidusoft.project.system.service.SecMenuOrgService;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import com.yidusoft.utils.TreeBuilder;
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
@RequestMapping("/sec/menu")
public class SecMenuController {
    @Resource
     private SecMenuService secMenuService;
     @Resource
     private SecMenuOrgService secMenuOrgService;

    @RequestMapping(value ="/findMenuSortCountByParentId")
    @ResponseBody
    public Result findMenuSortCountByParentId(String menuId){
        return ResultGenerator.genSuccessResult(secMenuService.findMenuSortCountByParentId(menuId));
    }

    /**
     * 获取菜单添加之前的数据
     * @param menuId
     * @return
     */
    @RequestMapping(value ="/findMenuAddInfo")
    @ResponseBody
    public Result findSecMenuAddInfo(String menuId){
        List<Integer> sorts = secMenuService.querySort(menuId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sorts",sorts);
        map.put("currentSort",sorts.size()+1);
        return ResultGenerator.genSuccessResult(map);
    }

    /**
     * 获取菜单编辑之前的数据
     * @param id
     * @return
     */
    @RequestMapping(value ="/findMenuUpdateInfo")
    @ResponseBody
    public Result findMenuUpdateInfo(String id){
        Map<String,Object> map = new HashMap<String,Object>();
        SecMenu secMenu = secMenuService.findById(id);
        List<Integer> sorts = secMenuService.querySort(secMenu.getParentId());
        map.put("menu",secMenu);
        map.put("sorts",sorts);
        return ResultGenerator.genSuccessResult(map);
    }

    /**
     * 添加菜单
     * @param
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(String menuJson) {
        SecMenu secMenu = JSON.parseObject(menuJson,SecMenu.class);
        if (secMenu.getUrl()==null||secMenu.getUrl()==""){
            secMenu.setUrl("/1");
        }
        synchronized (this){
            String parentId = secMenu.getParentId();
            secMenu.setId(UUID.randomUUID().toString());
            secMenu.setMenuCode(CodeHelper.getCode("CD"));
            secMenu.setCreateTime(new Date());
            secMenuService.addSort(parentId,secMenu.getSort());
            secMenuService.save(secMenu);
            //更新权限
//            shiroService.updatePermission();
        }
        return ResultGenerator.genSuccessResult(secMenu);
    }



    //逻辑删除菜单
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        SecMenu secMenu = secMenuService.findById(id);
        secMenu.setDeleted(1);
        secMenuService.deleteSort(id);
        secMenuService.update(secMenu);
        //更新权限
//        shiroService.updatePermission();
        return ResultGenerator.genSuccessResult(secMenu);
    }


    //逻辑删除，数据还在 批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            //删除排序
           // secMenuService.deleteSort(str);
            List<String> list = new ArrayList<>();
            list=secMenuService.getChildrenById(str);
            for(String  id: list){
                secMenuService.deleteSort(str);
            }
        }
        for(String str : arr){
            SecMenu secMenu = secMenuService.findById(str);
            secMenu.setDeleted(1);
            List<String> list = new ArrayList<>();
            list=secMenuService.getChildrenById(str);
            for(String  id: list){
                SecMenu Menu = secMenuService.findById(id);
                Menu.setDeleted(1);
                secMenuService.update(Menu);
            }
            secMenuService.update(secMenu);
            //更新权限
//            shiroService.updatePermission();
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(String menuJson) {
        SecMenu secMenu = JSON.parseObject(menuJson,SecMenu.class);

        if (secMenu.getUrl()==null||secMenu.getUrl()==""){
            secMenu.setUrl("/1");
        }
        secMenuService.updateSort(secMenu.getId(),secMenu.getSort());
        secMenuService.update(secMenu);

        //更新权限
//        shiroService.updatePermission();
        return ResultGenerator.genSuccessResult(secMenu);
    }



    @PostMapping("/detail")
    @ResponseBody
    public Result detail(@PathVariable String id) {
        SecMenu secMenu = secMenuService.findById(id);
        return ResultGenerator.genSuccessResult(secMenu);
    }




    /**
     * 分页获取菜单
     * @param
     * @return
     */
    @PostMapping("/menuList")
    @ResponseBody
    public Result menuList(String menuJson, int page, int size) {
        Map<String,Object> map = new HashMap<String,Object>();

        SecMenu secMenu = JSON.parseObject(menuJson,SecMenu.class);
        int total = secMenuService.getMenu(secMenu).size();

        PageHelper.startPage(page, size);
        List<SecMenu> secMenus = secMenuService.getMenu(secMenu);

        map.put("Rows",secMenus);
        map.put("Total",total);
        return ResultGenerator.genSuccessResult(map);
    }



    /**
     * 首页菜单树
     * @return
     */
    @PostMapping("/indexMenuTree")
    @ResponseBody
    public Result indexMenuTree() {
        return ResultGenerator.genSuccessResult(secMenuService.indexMenuTree(Security.getUserId(), Security.getUser().getCompanyId()));
    }


    @PostMapping("/checkMenuName")
    @ResponseBody
    public Result findByMenuName(String menuName){
        List<String> list = secMenuService.findByMenuName(menuName);
        if(list.size()>0){
            return ResultGenerator.genSuccessResult(1);
        }
        return ResultGenerator.genSuccessResult();
    }



    @PostMapping("/menuTree")
    @ResponseBody
    public List<TreeNode> menuTree() {
        return   secMenuService.menuTree();
    }

    @PostMapping("/menuToTree")
    @ResponseBody
    public List<SecMenu> menuToTree(String id) {
        List<SecMenu>  menu=secMenuService.menuToTree(id);
        return   menu;
    }


    /**
     * 菜单树
     * @param menuType
     * @return
     */
    @PostMapping("/findMenuAll")
    @ResponseBody
    public Result findMenuAll(String menuType) {
        List<SecMenu>  menu=secMenuService.findMenuAll(menuType);
        return   ResultGenerator.genSuccessResult(menu);
    }





    //查询可用 没被删除的菜单
    @RequestMapping(value={"/getMenuForAvailable",""})
    public String getMenuForAvailable(Model model){
      /*  List<SecMenu> list = secMenuService.getMenuForAvailable();
        model.addAttribute("menu",list);
        model.addAttribute("m",list);*/
        return  "index";
    }

    @PostMapping("/getChildrenById")
    @ResponseBody
    public List<String> getChildrenById(String ids){
        String arr [] = ids.split(",");
        List<String> list = new ArrayList<>();
        for(String id : arr) {
            System.out.println(secMenuService.getChildrenById(id));
            list=secMenuService.getChildrenById(id);
        }
        return list;
    }
   /* ------------------------------------一道华丽的分割线------------------------------------*/
   @PostMapping("/listByPages")
   @ResponseBody
   public Result listByPages(int page,
                             int size, String params, String userId) {
       SecMenuForParameter secMenuForParameter = JSON.parseObject(params,SecMenuForParameter.class);
       secMenuForParameter.setUserId(userId);
       //查询分页数据
      // int total=secMenuService.getQueryAllTotal(secMenuForParameter);
       PageHelper.startPage(page,size);
       List<SecMenu> secOrgList=secMenuService.getQueryAll(secMenuForParameter);
       PageInfo pageInfo = new PageInfo(secOrgList);
      // pageInfo.setTotal(total);
       return   ResultGenerator.genSuccessResult(pageInfo);
   }


    @PostMapping("/tree")
    @ResponseBody
    public Result tree(String userId) {
        List<TreeNode> nodes =secMenuService.getTree(userId);
        List<TreeNode> treeNodes = TreeBuilder.buildByRecursive(nodes);
        Result result = ResultGenerator.genSuccessResult(treeNodes);
        return result;
    }

    @PostMapping("/byRoleList")
    @ResponseBody
    public Result findCompanyRoleMenuByRoleId(String roleId){
        return ResultGenerator.genSuccessResult(secMenuService.findCompanyRoleMenuByRoleId(roleId));
    }




    //添加创建菜单
    @PostMapping("/addMenu")
    @ResponseBody
    public Result addMenu(String menuJson) {
        SecMenu secMenu = JSON.parseObject(menuJson,SecMenu.class);
        if (secMenu.getUrl()==null||secMenu.getUrl()==""){
            secMenu.setUrl("/1");
        }
        synchronized (this){
            String parentId = secMenu.getParentId();
            String menuId=UUID.randomUUID().toString();
            secMenu.setId(menuId);
            secMenu.setMenuCode(CodeHelper.getCode("CD"));
            secMenu.setCreateTime(new Date());
            //排序设置
            secMenuService.addSort(secMenu.getParentId(),secMenu.getSort());
            secMenuService.save(secMenu);

            //更新权限
//            shiroService.updatePermission();
        }

        return  ResultGenerator.genSuccessResult();
    }
//获取菜单排序数字
@PostMapping("/querySort")
@ResponseBody
public Result querySort(String parentId) {

    HashMap maps=new HashMap();
    maps.put("parentId",parentId);
    maps.put("userId", Security.getUserId());
     List<Integer> sort= secMenuService.webQuerySort(maps);

    Map<String,Object> map=new HashMap<>();
    map.put("sort",sort.size());
    return  ResultGenerator.genSuccessResult(map);
}
  //查询信息
    @PostMapping("/findMenuInfo")
    @ResponseBody
    public Result findMenuInfo(String id) {
       String  idStr="\""+id+"\"";
        List<SecMenu> secMenus= secMenuService.findByIds(idStr);
        Map<String,Object> map=new HashMap<>();
        map.put("menus",secMenus.get(0));
        return  ResultGenerator.genSuccessResult(map);
    }
    //修改菜单

    @PostMapping("/updateMenu")
    @ResponseBody
    public Result updateMenu(String menuJson) {
        SecMenu secMenu = JSON.parseObject(menuJson,SecMenu.class);
        secMenu.setCreateTime(new Date());
        //排序设置
        secMenuService.addSort(secMenu.getParentId(),secMenu.getSort());
        secMenuService.update(secMenu);
        return  ResultGenerator.genSuccessResult();
    }


}

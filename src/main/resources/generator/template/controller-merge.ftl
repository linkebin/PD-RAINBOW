package ${basePackage}.controller;
<#--import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;-->
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import ${basePackage}.domain.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by ${author} on ${date}.
*/
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;


    @RequestMapping(value ="/linkAdd")
    public String linkAdd(Model model){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("UUID",UUID.randomUUID().toString());
        map.put("createDate",new Date());
        model.addAttribute("infos",map);
        return  "${modelNameUpperCamelLc}/${modelNameUpperCamelLc}-add";
    }

    @RequestMapping(value ="/linkList")
    public String linkList(){
        return  "${modelNameUpperCamelLc}/${modelNameUpperCamelLc}-list";
    }

    @RequestMapping(value ="/linkUpdate")
    public String linkUpdate(Model model,String id){
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        model.addAttribute("${modelNameLowerCamel}",${modelNameLowerCamel});
        return  "${modelNameUpperCamelLc}/${modelNameUpperCamelLc}-update";
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

   //把数据永久删除
    @PostMapping("/deleteTrue")
    @ResponseBody
    public Result deleteTrue(String  id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    //逻辑删除，数据还在
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(String  id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        ${modelNameLowerCamel}.setDeleted(1);
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }


    //逻辑删除，数据还在 批量删除
    @PostMapping("/deleteBacth")
    @ResponseBody
    public Result deleteBacth(String  ids) {
        String arr [] = ids.split(",");
        for(String str : arr){
            ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(str);
            ${modelNameLowerCamel}.setDeleted(1);
            ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    @ResponseBody
    public Result detail(@PathVariable String id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }


    @PostMapping("/list")
    @ResponseBody
    public Map<String,Object> list() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        map.put("Rows",list);
        map.put("Total",list.size());
        return map;
    }


    @PostMapping("/listByPage")
    @ResponseBody
    public Result listByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

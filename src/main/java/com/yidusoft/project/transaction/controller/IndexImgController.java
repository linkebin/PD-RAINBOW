package com.yidusoft.project.transaction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.IndexImg;
import com.yidusoft.project.transaction.service.IndexImgService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2017/10/26.
*/
@RestController
@RequestMapping("/index/img")
public class IndexImgController {
    @Resource
    private IndexImgService indexImgService;

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/listByPage")
    public Result listByPage(Integer page,Integer size) {
        PageHelper.startPage(page, size);
        List<IndexImg> list = indexImgService.getIndexImgAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 添加首页图片
     * @param indexImgJson
     * @return
     */
    @PostMapping("/add")
    public Result add(String indexImgJson) {
        IndexImg indexImg= JSON.parseObject(indexImgJson,IndexImg.class);
        indexImg.setId(UUID.randomUUID().toString());
        indexImg.setCreateTime(new Date());
        indexImg.setCreator(Security.getUser().getUserName());
        indexImg.setDeleted(0);
        indexImgService.save(indexImg);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改图片
     * @param indexImgJson
     * @return
     */
    @PostMapping("/update")
    public Result update(String indexImgJson) {
        IndexImg indexImg= JSON.parseObject(indexImgJson,IndexImg.class);
        IndexImg indexImg1 = indexImgService.findById(indexImg.getId());
        indexImg1.setImgPath(indexImg.getImgPath());
        indexImg1.setImgType(indexImg.getImgType());
        indexImg1.setState(indexImg.getState());
        indexImg1.setObjectId(indexImg.getObjectId());
        indexImgService.update(indexImg1);
        return ResultGenerator.genSuccessResult();
    }


    /**
     * 获取首页图片列表
     * @return
     */
    @GetMapping("list")
    public Result list() {
        List<IndexImg> list = indexImgService.getIndexImgAll();
        return ResultGenerator.genSuccessResult(list);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/batchDeletion")
    public Result batchDeletion(String  ids) {
        String arr [] = ids.split(",");
        for(String id : arr){
            IndexImg indexImg = indexImgService.findById(id);
            indexImg.setDeleted(1);
            indexImgService.update(indexImg);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(IndexImg indexImg) {
        indexImgService.update(indexImg);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        IndexImg indexImg = indexImgService.findById(id);
        return ResultGenerator.genSuccessResult(indexImg);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<IndexImg> list = indexImgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

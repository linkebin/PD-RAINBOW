package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.SceneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/scene")
public class SceneController {
    @Resource
    private SceneService sceneService;

    @PostMapping
    public Result add(Scene scene) {
        sceneService.save(scene);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        sceneService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(Scene scene) {
        sceneService.update(scene);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        Scene scene = sceneService.findById(id);
        return ResultGenerator.genSuccessResult(scene);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Scene> list = sceneService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

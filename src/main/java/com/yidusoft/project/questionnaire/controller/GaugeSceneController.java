package com.yidusoft.project.questionnaire.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.GaugeScene;
import com.yidusoft.project.questionnaire.service.GaugeSceneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/gauge/scene")
public class GaugeSceneController {
    @Resource
    private GaugeSceneService gaugeSceneService;


    /***
     * 删除量表选择的场景
     * @param ids
     * @return
     */
    @PostMapping("/deleteGaugeScene")
    public Result deleteGaugeScene(String  ids,String gaugeId) {
        GaugeScene gaugeScene=new GaugeScene();
        gaugeScene.setGaugeId(gaugeId);
        gaugeScene.setSceneId(ids);
        return  gaugeSceneService.deleteGaugeScene(gaugeScene);
    }

    @PostMapping("/addGaugeScene")
    public Result addGaugeScene(String  ids,String gaugeId) {

        return  gaugeSceneService.addGaugeScene(  ids, gaugeId);
    }









    @PostMapping
    public Result add(GaugeScene gaugeScene) {
        gaugeSceneService.save(gaugeScene);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        gaugeSceneService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(GaugeScene gaugeScene) {
        gaugeSceneService.update(gaugeScene);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        GaugeScene gaugeScene = gaugeSceneService.findById(id);
        return ResultGenerator.genSuccessResult(gaugeScene);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<GaugeScene> list = gaugeSceneService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

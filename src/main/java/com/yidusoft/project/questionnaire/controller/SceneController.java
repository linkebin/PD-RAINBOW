package com.yidusoft.project.questionnaire.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.questionnaire.domain.QuestionnaireTag;
import com.yidusoft.project.questionnaire.domain.Scene;
import com.yidusoft.project.questionnaire.service.SceneService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2017/10/11.
 */
@RestController
@RequestMapping("/scene")
public class SceneController {
    @Resource
    private SceneService sceneService;
    private Logger logger = LoggerFactory.getLogger(SceneController.class);

    @PostMapping
    public Result add(Scene scene) {
        sceneService.save(scene);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
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

    /*=====================分割线===================================*/

    /***
     * 添加或者修改问卷场景
     * @param typeJson
     * @return
     */
    @PostMapping("/addORupdateQuestionnaireScene")
    @ResponseBody
    public Result addORupdateQuestion(String typeJson) {

        Scene scene = JSON.parseObject(typeJson, Scene.class);
        logger.info("scenename" + scene.getSceneName() + "=========");
        try {
            if ("".equals(scene.getId())) {
                scene.setId(UUID.randomUUID().toString());
                scene.setCreateTime(new Date());
                scene.setCreator(Security.getUser().getUserName());
                scene.setDeleted("0");
                scene.setSceneCode(CodeHelper.getCode("WQS"));
                sceneService.save(scene);
            } else {
                sceneService.update(scene);
            }
        } catch (Exception e) {
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();

    }

    /**
     * 分页条件查询所有的问卷场景
     *
     * @param params
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/questionnaireSceneListByPage")
    @ResponseBody
    public Result questionListByPage(String params, int page, int size) {
        Scene scene = JSON.parseObject(params, Scene.class);
        PageHelper.startPage(page, size);
        List<Scene> list = sceneService.questionnaireSceneListByPage(scene);
        //查询所有的相关数据
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    /***
     * 逻辑删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteQuestionnaireScene")
    @ResponseBody
    public Result deleteQuestion(String ids) {
        try {
            String arr[] = ids.split(",");
            for (String str : arr) {
                Scene scene = sceneService.findById(str);
                scene.setDeleted("1");
                sceneService.update(scene);
            }

        } catch (Exception e) {
            return ResultGenerator.genFailResult("删除失败！");
        }

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改之前 查询的问卷场景信息
     * @param id
     * @return
     */
    @PostMapping("/findQuestionnaireSceneForUpdate")
    @ResponseBody
    public  Result findQuestionForUpdate(String id){
        Scene scene= sceneService.findById(id);
        return ResultGenerator.genSuccessResult(scene);
    }

}

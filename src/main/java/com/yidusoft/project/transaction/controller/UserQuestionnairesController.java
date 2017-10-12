package com.yidusoft.project.transaction.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;
import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/user/questionnaires")
public class UserQuestionnairesController {
    @Resource
    private UserQuestionnairesService userQuestionnairesService;

    @PostMapping
    public Result add(UserQuestionnaires userQuestionnaires) {
        userQuestionnairesService.save(userQuestionnaires);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        userQuestionnairesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(UserQuestionnaires userQuestionnaires) {
        userQuestionnairesService.update(userQuestionnaires);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        UserQuestionnaires userQuestionnaires = userQuestionnairesService.findById(id);
        return ResultGenerator.genSuccessResult(userQuestionnaires);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserQuestionnaires> list = userQuestionnairesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

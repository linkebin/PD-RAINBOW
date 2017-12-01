package com.yidusoft.project.transaction.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.UserQuestionnaires;
import com.yidusoft.project.transaction.service.UserQuestionnairesService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2017/10/11.
*/
@RestController
@RequestMapping("/user/questionnaires")
public class UserQuestionnairesController {
    @Resource
    private UserQuestionnairesService userQuestionnairesService;

    /**
     * 获取用户的问卷账号
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        UserQuestionnaires userQuestionnaires = userQuestionnairesService.findBy("userId", Security.getUserId());
        return ResultGenerator.genSuccessResult(userQuestionnaires);
    }

    /**
     * 获取用户问卷账号信息
     * @param userId
     * @return
     */
    @PostMapping("/detail")
    public Result detail(String userId) {
        UserQuestionnaires userQuestionnaires = userQuestionnairesService.findBy("userId",userId);
        return ResultGenerator.genSuccessResult(userQuestionnaires);
    }

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
}

package com.yidusoft.project.transaction.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.AccountInfo;
import com.yidusoft.project.transaction.service.AccountInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2017/11/17.
 */
@RestController
@RequestMapping("/account/info")
public class AccountInfoController {
    @Resource
    private AccountInfoService accountInfoService;

    @PostMapping("/listPage")
    public Result list(Integer page, Integer size, Date startTime, Date endTime) {
        PageHelper.startPage(page, size);
        Map<String, Date> map = new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<AccountInfo> list = accountInfoService.getAccountByTime(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping
    public Result add(AccountInfo accountInfo) {
        accountInfoService.save(accountInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        accountInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(AccountInfo accountInfo) {
        accountInfoService.update(accountInfo);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable String id) {
        AccountInfo accountInfo = accountInfoService.findById(id);
        return ResultGenerator.genSuccessResult(accountInfo);
    }
}

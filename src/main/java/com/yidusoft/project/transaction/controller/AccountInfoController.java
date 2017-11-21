package com.yidusoft.project.transaction.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.transaction.domain.AccountInfo;
import com.yidusoft.project.transaction.service.AccountInfoService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    /**
     * 分页获取账户信息
     * @param page
     * @param size
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/listPage")
    public Result list(Integer page, Integer size, String startTime, String endTime) {
        PageHelper.startPage(page, size);
        Map<String, String> map = new HashMap<>();
        map.put("userId", Security.getUser().getId());
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<AccountInfo> list = accountInfoService.getAccountByTime(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 获取所有的账户信息
     * @return
     */
    @GetMapping("/list")
    public Result list() {
        Map<String, String> map = new HashMap<>();
        map.put("userId", Security.getUser().getId());
        List<AccountInfo> list = accountInfoService.getAccountByTime(map);
        return ResultGenerator.genSuccessResult(list);
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

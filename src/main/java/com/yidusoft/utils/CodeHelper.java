package com.yidusoft.utils;

import com.yidusoft.project.system.service.UtilService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by linkb on 2017/7/20.
 */
@Component
public class CodeHelper {
    @Resource
    private UtilService utilService;

    private static CodeHelper codeHelper;

    public void setUtilService(UtilService utilService) {
        this.utilService = utilService;
    }

    @PostConstruct
    public void init() {
        codeHelper = this;
        codeHelper.utilService = this.utilService;
    }

    public static String getCode(String codeName) {
        return codeHelper.utilService.getCode(codeName);
    }

}

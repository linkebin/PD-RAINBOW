package com.yidusoft.utils;

import com.yidusoft.project.system.service.UtilService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Random;

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


    public static String randomCode(int count){
        try {
            String str="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder sb = new StringBuilder();
            Random r = new Random(System.currentTimeMillis());
            for (int i = 0; i < count; i++) {
                int d =r.nextInt(62);
                sb.append(str.charAt(d));
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

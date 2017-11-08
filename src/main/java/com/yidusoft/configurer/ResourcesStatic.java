package com.yidusoft.configurer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcb on 2017/11/8.
 */
public  class  ResourcesStatic {
    public final   static Map<String,Object> OPERRESOURCES=new HashMap<>();
    static {
        //问卷的操作
        OPERRESOURCES.put("/questionnaire/addQuestionnaire","添加问卷");
        OPERRESOURCES.put("/questionnaire/updateQuestionnaire","修改问卷");
        OPERRESOURCES.put("/questionnaire/invalidQuestionnaire","问卷下架");
        OPERRESOURCES.put("/questionnaire/deleteQuestionnaire","删除问卷");
    }


}

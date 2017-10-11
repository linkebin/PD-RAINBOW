package com.yidusoft.utils;

import com.yidusoft.project.system.domain.SecUser;
import org.apache.shiro.SecurityUtils;

/**
 * Created by linkb on 2017/8/8.
 */
public final class Security {


    /**
     * 获取当前登录用户的用户信息
     * @return
     */
    public static SecUser getUser(){
        return (SecUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
    }
    /**
     * 获取当前登录用户的用户ID
     * @return
     */
    public static String  getUserId(){
        return (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
    }
}

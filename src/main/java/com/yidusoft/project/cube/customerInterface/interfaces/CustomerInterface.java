package com.yidusoft.project.cube.customerInterface.interfaces;

import org.apache.shiro.session.Session;

import javax.servlet.http.HttpSession;

/**
 * Created by zcb on 2017/12/14.
 */
public interface CustomerInterface {
    /**
     * 生成token,存在session里面
     */
     String getToken();
    /**
     * 清空session里面的token
     * @param name
     */
     void deleteSession(String name);
    /**
     * 获取session
     */
      Session getSession();
}

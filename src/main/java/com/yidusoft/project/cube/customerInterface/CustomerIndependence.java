package com.yidusoft.project.cube.customerInterface;

import com.yidusoft.project.cube.customerInterface.interfaces.CustomerInterface;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.UUID;

/**
 * Created by zcb on 2017/12/14.
 */
public class CustomerIndependence implements CustomerInterface {


    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void deleteSession(String name) {
        Subject subjects= SecurityUtils.getSubject();
        subjects.getSession().removeAttribute(name);
    }

    @Override
    public Session getSession() {
        Subject subjects= SecurityUtils.getSubject();
        return subjects.getSession();
    }
}

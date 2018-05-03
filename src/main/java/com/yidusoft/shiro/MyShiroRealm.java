package com.yidusoft.shiro;

import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecMenuMemberService;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.redisMq.MsgGenerator;
import com.yidusoft.redisMq.MsgSend;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/21.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private SecUserService secUserService;

    @Resource
    private SecMenuMemberService secMenuMemberService;



    @Autowired
    private MsgSend msgSend;

    //清空权限
//    public void clearCached() {
//        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
//        super.clearCache(principals);
//    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SecUser secUser= (SecUser) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='admin', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
        Map<String,Object> map = new HashMap<String,Object>();
        List<SecMenu> menuList = secMenuMemberService.findMenuByUser(secUser.getId());
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(SecMenu secMenu: menuList){
            info.addStringPermission(secMenu.getUrl());
        }
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String account = token.getUsername();
//        String password = String.valueOf(token.getPassword());

        // 从数据库获取对应用户名密码的用户
        SecUser  user = secUserService.getSecUserInfo(account);
        if(user==null) throw new UnknownAccountException("账号不存在!");//账号删除
        if (user.getStatus()==0) throw new LockedAccountException("账号被锁定!"); // 帐号锁定

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSessionId", user.getId());
        session.setAttribute("userSession", user);

        user.setIp(session.getHost());
        //记录登录日志
        msgSend.send(MsgGenerator.genLoginLogMessage(user));

        return new SimpleAuthenticationInfo(user, user.getUserPass(), ByteSource.Util.bytes("yidusoft"), getName());
    }

}

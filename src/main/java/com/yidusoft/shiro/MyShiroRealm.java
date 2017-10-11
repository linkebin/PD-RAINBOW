package com.yidusoft.shiro;

import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecMenuMemberService;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.Base64ToImage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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

    //清空权限
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

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

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String account = (String)token.getPrincipal();
        SecUser user = secUserService.getSecUserInfo(account);
        if(user==null) throw new UnknownAccountException();//账号删除
        if (user.getStatus()==0) throw new LockedAccountException(); // 帐号锁定
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getUserPass(), //密码
                ByteSource.Util.bytes("yidusoft"),//输入的账号
                getName()  //realm name
        );

        try {
            //将图片转换成base64
            if(user.getHeadImg()!=null && !user.getHeadImg().equals("")){
                user.setHeadImg(Base64ToImage.getImageStr(user.getHeadImg()));
            }
        }catch (Exception e){
            user.setHeadImg("");
        }finally {
            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSessionId", user.getId());
            session.setAttribute("userSession", user);
            return authenticationInfo;
        }

    }
}

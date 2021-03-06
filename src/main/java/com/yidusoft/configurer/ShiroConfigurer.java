package com.yidusoft.configurer;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.github.pagehelper.util.StringUtil;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.shiro.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/23.
 */
@Configuration
public class ShiroConfigurer {

    @Autowired(required = false)
    private SecMenuService secMenuService;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
     @Bean
     public ShiroDialect shiroDialect() {
        return new ShiroDialect();
     }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是会报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
          shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/Scripts/**","anon");
        filterChainDefinitionMap.put("/Styles/**","anon");
        filterChainDefinitionMap.put("/date2format.js","anon");
        filterChainDefinitionMap.put("/navConfig.js","anon");
        filterChainDefinitionMap.put("/pack.ajax.js","anon");
        filterChainDefinitionMap.put("/utils.js","anon");
        filterChainDefinitionMap.put("/questionnaire/**","anon");
        filterChainDefinitionMap.put("/card.js","anon");
        filterChainDefinitionMap.put("/nation.js","anon");

        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/files/**","anon");
        filterChainDefinitionMap.put("/appLogin","anon");
        filterChainDefinitionMap.put("/app/**","anon");
        filterChainDefinitionMap.put("/sign/**","anon");
        filterChainDefinitionMap.put("/upload/uploadImglayUi","anon");
        //活动问卷的放行
        filterChainDefinitionMap.put("/web/activities/fillingPage","anon");
        filterChainDefinitionMap.put("/sign/code","anon");
        filterChainDefinitionMap.put("/sign/check/mobileCode","anon");
        filterChainDefinitionMap.put("/launch/activities/getIdByPorn","anon");
        filterChainDefinitionMap.put("/launch/activities/getActivityById","anon");
        filterChainDefinitionMap.put("/data/acquisition/findCount","anon");
        filterChainDefinitionMap.put("/user/questionnaires/detail","anon");
        filterChainDefinitionMap.put("/active/participant/add","anon");
        filterChainDefinitionMap.put("/web/cube/getQuestionnaireFill","anon");
        filterChainDefinitionMap.put("project/cube/questionnaire/other-questionnaire","anon");
        filterChainDefinitionMap.put("/cube/questionnaire/question/findQuestionForQuestionnaire","anon");
        filterChainDefinitionMap.put("/cube/questionnaire/submitQuestionnaire","anon");
        filterChainDefinitionMap.put("/cube/questionnaire/subQuestionnaire","anon");
        filterChainDefinitionMap.put("/web/cube/getSuccess","anon");
        filterChainDefinitionMap.put("/alipay/notify/notifyUrl","anon");
        filterChainDefinitionMap.put("/user/modifyPsd/code","anon");
        filterChainDefinitionMap.put("/user/modifyPsd/code","anon");
        filterChainDefinitionMap.put("/user/check/msgCode","anon");
        //来访者问卷的填写
        filterChainDefinitionMap.put("/web/cube/getQuestionnaireGuide","anon");
        filterChainDefinitionMap.put("/web/cube/getQuestionnaireFillIn","anon");
        filterChainDefinitionMap.put("/cube/questionnaire/submitQuestionnaire","anon");
        filterChainDefinitionMap.put("/cube/questionnaire/question/findQuestionForQuestionnaire","anon");
        //来访登记放行
        filterChainDefinitionMap.put("/web/customer/checkIn","anon");
        filterChainDefinitionMap.put("/web/customer/checkInResponse","anon");
        filterChainDefinitionMap.put("/visitor/register/add","anon");
        filterChainDefinitionMap.put("/upload/findCertification","anon");
        filterChainDefinitionMap.put("/web/customer/dna","anon");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 *放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //自定义加载权限资源关系
        List<SecMenu> secMenuList = secMenuService.queryAll();
         for(SecMenu secMenu:secMenuList){
            if (StringUtil.isNotEmpty(secMenu.getUrl())) {
                String permission = "perms[" + secMenu.getUrl()+ "]";
                filterChainDefinitionMap.put(secMenu.getUrl(),permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }


    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
       // sessionManager.
        SimpleCookie simpleCookie=new SimpleCookie("sessionIdTo");
        simpleCookie.setPath("/");
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }

}

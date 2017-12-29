package com.yidusoft.aspect;

import com.yidusoft.project.cube.customerInterface.CustomerIndependence;
import com.yidusoft.project.cube.customerInterface.interfaces.CustomerInterface;
import com.yidusoft.utils.Security;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
/**
 * 针对前台来访客户的一个操作拦截
 *
 * @author zcb
 */
  @Aspect
  @Component
  @Order(-5)
public class CustomerAspect {


    /**
     * 后台所有的方法
     */
   @Pointcut("execution(* com.yidusoft.project.*.controller..*.*(..))")
   public void backstage(){}
    /**
     * 前台所有的页面跳转方法
     */
   @Pointcut("execution(* com.yidusoft.project.cube.*.web.*.*(..))")
    public void reception (){}
   // com\yidusoft\project\cube\questionnaire\WebController

    /**
     * 首页拦截
     */
   @Pointcut("execution(* com.yidusoft.project.system.controller.IndexController.*(..))")
   public void reception1 (){}
    /**
     * 问卷引导页面
     */
    @Pointcut("execution(* com.yidusoft.project.cube.questionnaire.web.WebCubeQuestionnaireController.getQuestionnaireGuide(..))")
    public void reception2 (){}
    /***
     * 问卷提交方法
     */
    @Pointcut("execution(* com.yidusoft.project.cube.questionnaire.CubeQuestionnaireController.submitQuestionnaire(..))")
    public void reception3 (){}

    /**
     * 登记页面
     */
    @Pointcut("execution(* com.yidusoft.project.cube.customer.web.WebCustomerController.checkIn(..))")
    public void reception4 (){}
    /**
     * 登记提交
     */
    @Pointcut("execution(*  com.yidusoft.project.business.controller.VisitorRegisterController.add(..))")
    public void reception5 (){}
    /**
     * 问卷填写
     */
    @Pointcut("execution(* com.yidusoft.project.cube.questionnaire.web.WebCubeQuestionnaireController.getQuestionnaireFillIn(..))")
    public void independent(){}

   // @Before("independent()")
    public void doIndependent(){
         //token 为空，给token赋值
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response=attributes.getResponse();
        String token=UUID.randomUUID().toString();
         Cookie cookie=new Cookie("token",token);
         cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println(token);
    }

    /**
     *
     * 拦截所有的方法，判断token里面是否有值，如果是空，直接放回原来的链接
     *不包括活动填写，问卷填写，来访登记的 页面连接
     */
   // @Before("reception() || reception1()")
    public void verificationToken1(JoinPoint  joinPoint)throws Exception{
         if(Security.getUser()!=null){
           // execution(String com.yidusoft.project.system.controller.IndexController.login()
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String method=joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            if(!"com.yidusoft.project.cube.questionnaire.web.WebCubeQuestionnaireController.getQuestionnaireFillIn"
                    .equals(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())){
             //获取域名
              String domainName=request.getRequestURL().toString()
                                .replaceAll(request.getRequestURI().toString(),"")
                                .trim();
            //获得头部的信息 token
            String tokens= request.getHeader("token");
            //定义当前页面的url
            String url="";
            Cookie[] cookies= request.getCookies();
                for(int i=0;i<cookies.length;i++){
                    if(cookies[i].getName().equals("token")){
                        System.out.println("我是token："+cookies[i].getValue());
                    }else  if(cookies[i].getName().equals("uris")){
                        url=cookies[i].getValue();
                        System.out.println("我是uris："+cookies[i].getValue());
                    }
                }
              HttpServletResponse response=attributes.getResponse();
                String uri=url.replaceAll(domainName,"").trim();
                 response.sendRedirect(uri);
         }
         }
    }

    /**
     * 方法执行retrun时
     *问卷填写之前 来访者登记之前
     */
    @AfterReturning("reception2() || reception4()")
    public void  generateToken(JoinPoint  joinPoint) throws  Exception{
        try {
            if(Security.getUser()!=null){
                CustomerInterface customerInterface =new CustomerIndependence();
                //获取token
                String token= customerInterface.getToken();
                //退出登陆
                Subject subject= SecurityUtils.getSubject();
                subject.logout();
                //存入session
                Subject subjects= SecurityUtils.getSubject();
                subjects.getSession().setAttribute("token",token);

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /**
     * 环绕通知
     * 问卷提交  客户登记提交
     */
     @Around("reception3()||reception5() ")
    public Object  verificationToken(ProceedingJoinPoint joinPoint)throws Exception{
        CustomerInterface customerInterface =new CustomerIndependence();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        Object result = null;
        //获取请求头的信息
        String token= request.getHeader("token");
        //判断 token 是否与session的token相等
        Object tokenSession=customerInterface.getSession().getAttribute("token");
        if(!StringUtils.isEmpty(token)&&
            tokenSession!=null&&
            !tokenSession.toString().equals("") &&
            token.equals(tokenSession.toString())){
            try {
                //清空session
                customerInterface.getSession().removeAttribute("token");
                //执行方法
                result=joinPoint.proceed();

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return  result;
    }


}

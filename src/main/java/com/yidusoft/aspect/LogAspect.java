package com.yidusoft.aspect;

import com.alibaba.fastjson.JSON;
import com.yidusoft.configurer.ResourcesStatic;
import com.yidusoft.project.monitor.domain.OperLog;
import com.yidusoft.project.monitor.service.OperLogService;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.redisMq.MsgGenerator;
import com.yidusoft.redisMq.MsgSend;
import com.yidusoft.utils.Security;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zcb on 2017/11/6.
 *
 * 1 @Aspect：将一个java类定义为切面类
 * 2 @Component：bean的注入
 * 3 @Order(-5)：优先级 越小 越优先
 * 4 @Before在切入点开始处切入内容
 * 5 @After在切入点结尾处切入内容
 * 6 @AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 * 7 @Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 * 8 @AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 */
@Aspect
@Component
@Order(-6)
public class LogAspect {
     @Autowired
     private OperLogService operLogService;

    @Autowired
    private MsgSend msgSend;

     private Logger logger =LoggerFactory.getLogger(this.getClass());

     ThreadLocal<Long> operWhenLong = new ThreadLocal<Long>();
     ThreadLocal<OperLog> operLog = new ThreadLocal<OperLog>();
      /**
     * 定义一个切入点.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二 三个 * 任意包名
     * ~ 第四个 * 定义在controller包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
        @Pointcut("execution(* com.yidusoft.project.*.controller..*.*(..))")
        public void webLog(){}
        @Before("webLog()")
        public void doBefore(JoinPoint joinPoint){
        //获得进来的时间
        operWhenLong.set(System.currentTimeMillis()); ;
        // 接收到请求，记录请求内容
        logger.info("----------------请求日志开始---------------------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
            operLog.set(new OperLog());
            operLog.get().setOperId(UUID.randomUUID().toString());
            operLog.get().setOperInfo("");
            operLog.get().setOperType("");
            operLog.get().setOperIp(request.getRemoteAddr());
            operLog.get().setOperUrl(request.getRequestURI());
            operLog.get().setUrlType(request.getMethod());
              //System.currentTimeMillis  精确到毫秒
            operLog.get().setOperTime(new Date());
            operLog.get().setSessionId(request.getSession().getId());
            operLog.get().setUrlMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
              //获取所有参数方法一：
              Enumeration<String> enu=request.getParameterNames();
              Map<String,Object> map=new HashMap<>();
              while(enu.hasMoreElements()){
                  String paraName=(String)enu.nextElement();
                  map.put(paraName,request.getParameter(paraName));
              }
            operLog.get().setUrlParam(JSON.toJSONString(map));
            logger.info("参数: " + JSON.toJSONString(map));
         // 记录下请求内容
         logger.info("URI : " + request.getRequestURI());
         logger.info("HTTP_METHOD : " + request.getMethod());
         logger.info("IP : " + request.getRemoteAddr());
         logger.info("sessionId : " +request.getSession().getId());
         logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
         logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
         logger.info("标识时间："+(operWhenLong.get()));
        }

        /**
         * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
         * @param joinPoint
         */
        @AfterReturning("webLog()")
        public void  doAfterReturning(JoinPoint joinPoint){
         // 处理完请求，返回内容
          logger.info("处理完请求，返回内容");
            operLog.get().setUrlResult("正常");
            addOperLog();
          }

        /**
         * 所有方法的执行作为切入点 抛出异常执行
         * @param ex
         */
        @AfterThrowing(throwing="ex",pointcut="execution(* com.yidusoft.project.*.controller..*.*(..))")
        public void doRecoveryActions(Throwable ex) {
            // 声明ex时指定的类型会限制目标方法必须抛出指定类型的异常  
            // 此处将ex的类型声明为Throwable，意味着对目标方法抛出的异常不加限制  
            logger.info("*******异常********");
            operLog.get().setUrlResult("异常");
            addOperLog();

        }

    /**
     *添加行为操作
     *
     */
    public void addOperLog(){
           //获得进来的时间
           Long now = System.currentTimeMillis();
           operLog.get().setOperWhenLong( now-operWhenLong.get()+"ms");
           SecUser secUser= Security.getUser();
           if(secUser!=null){
               operLog.get().setUserName(secUser.getUserName());
               logger.info("操作人："+secUser.getUserName());
           }
           logger.info("标识时间："+(operWhenLong.get()));
           logger.info("用去时长："+(now-operWhenLong.get()+"ms"));

           if( ResourcesStatic.OPERRESOURCES.get(operLog.get().getOperUrl())!=null){
               operLog.get().setOperInfo(ResourcesStatic.OPERRESOURCES.get(operLog.get().getOperUrl()).toString());

               msgSend.send(MsgGenerator.genOperMessage(operLog.get()));

//               operLogService.save( operLog.get());
           }
        operWhenLong.remove();
        operLog.remove();
    }
}

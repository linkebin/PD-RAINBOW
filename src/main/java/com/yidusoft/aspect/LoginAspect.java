package com.yidusoft.aspect;

import com.yidusoft.utils.Security;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//@Aspect
//@Component
//@Order(-5)
public class LoginAspect {
     private Logger logger =LoggerFactory.getLogger(this.getClass());
      /**
     * 定义一个切入点.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二 三个 * 任意包名
     * ~ 第四个 * 定义在controller包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */

        /*@Pointcut("execution(* com.yidusoft.project.cube.*..*..*.*(..))")
        public void cube(){}*/
        /**
         * 前台管理
         * @param joinPoint
         */
        @Before("cube()")
        public void Reception (JoinPoint joinPoint)throws IOException {
            HttpServletResponse response=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
            //判断进入的用户是不是系统管理员
            int  type=Security.getUser().getAccountType();
            if(type==0){
                response.sendRedirect("/403");
            }
        }
}

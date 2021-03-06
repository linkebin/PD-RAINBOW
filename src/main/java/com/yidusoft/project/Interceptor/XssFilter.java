package com.yidusoft.project.Interceptor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Name:陈文杰
 * Date: 2018/6/21
 * Role:sql盲注，跨站脚本，跨站点请求伪造防范规则
 */
@Component
public class XssFilter implements Filter {
    FilterConfig filterConfig = null;
    @Value("${server.ip}")
    private String flag;

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String referer = request1.getHeader("Referer");
        String path = request1.getServletPath();
//        System.out.println("Referer----");
//        System.out.println(referer);
//        System.out.println(path);

        //跨站点请求伪造，条件为部署项目地址域名或者ip
        if (referer != null) {
            if (referer.indexOf(flag) == -1) {
                chain.doFilter(null, response);
                response1.sendRedirect(request1.getContextPath() + "/login");
                return;
            }
        } else {
            if (path.indexOf("/login") == -1&&path.indexOf("alipay/notify") == -1 && path.indexOf("web/activities/fillingPage") == -1&&path.indexOf("/Scripts") == -1) {
                chain.doFilter(null, response);
                response1.sendRedirect(request1.getContextPath() + "/login");
                return;
            }
        }

        Enumeration e = request.getParameterNames();
        int a = 0;
//        System.out.println("xss漏洞日志");
        while (e.hasMoreElements()) {
            String paramName = (String) e.nextElement();
            String value2 = new String(request.getParameter(paramName).getBytes("ISO-8859-1"), "gb2312");
//            System.out.println("---原始参数---："+paramName + "=" + request.getParameter(paramName));
            if (paramName.equals("t")||paramName.equals("json")) {
                break;
            }
            if (paramName.equals("image") && request.getParameter(paramName).toString().indexOf("data:image") != -1) {
                break;
            }

            //防止拦截请求参数里面的数据格式符号(请勿随意修改顺序)
            value2 = value2.replace("(中国标准时间)", "");
            value2 = value2.replace("GMT+0800", "");
            value2 = value2.replace("(??????)", "");
            if (value2.indexOf("imageText") != -1) {
                value2 = value2.replace(value2.substring(value2.indexOf("imageText"), value2.length()), "");
            }
            value2 = value2.replace("\",\"", "");
            value2 = value2.replace("\":[\"", "");
            value2 = value2.replace("{\"", "");
            value2 = value2.replace("\"]}", "");
            value2 = value2.replace("\"}", "");
            value2 = value2.replace("\":\"", "");
            value2 = value2.replace("\":null,\"", "");
            value2 = value2.replace("\":null", "");
            value2 = value2.replace("\":", "");
            value2 = value2.replace(",\"", "");
            if (path.indexOf("alipay/notify") == -1) {
                a = select(value2);
            }
            if (a == 1) {
                chain.doFilter(null, response);
            }
        }


        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public int select(String value) {
        if (value.indexOf(">") != -1 || value.indexOf("<") != -1 || value.indexOf("+") != -1 || value.indexOf("&") != -1 || value.indexOf(")") != -1 || value.indexOf("(") != -1 || value.indexOf("%") != -1 || value.indexOf(";") != -1 || value.indexOf("\'") != -1 || value.indexOf("\"") != -1) {
            System.out.println("被拦截："+value);
            return 1;
        }
        return 0;
    }

}

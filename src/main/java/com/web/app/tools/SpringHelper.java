package com.web.app.tools;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Title：SpringHelper 
 * @Description: SpringHelp工具类   
 * @Auth: LiangRui
 * @CreateTime:2015年4月1日 上午10:49:59     
 * @version V1.0
 */
@Component
public class SpringHelper implements ApplicationContextAware, ServletContextAware {

    private static ApplicationContext ac;
    private static ServletContext sc;

    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        SpringHelper.ac = ac;
    }

    public void setServletContext(ServletContext sc) {
        SpringHelper.sc = sc;
    }

    public static ApplicationContext getApplicatoinContext() {
        return ac;
    }

    public static ServletContext getServletContext() {
        return sc;
    }

    public static Object getSpringBean(String name) {
        if (ac == null) {
            throw new IllegalStateException("spring环境尚未启动！");
        }
        return ac.getBean(name);
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static String getContextPath() {
        String ctx = getRequest().getContextPath();
        return ctx.endsWith("/") ? ctx : ctx + "/";
    }

    public static String getAbsoluteUrl() {
        HttpServletRequest request = getRequest();
        return request.getLocalAddr() + ":" + request.getLocalPort() + request.getContextPath();
    }
    /**
      * @Title: getIPAddress
      * @Description: 获取IP地址
      * @param @return
      * @return String
      * @author LiangRui
      * @throws
      * @Time 2015年4月2日 下午12:30:19
     */
    public static String getIPAddress() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

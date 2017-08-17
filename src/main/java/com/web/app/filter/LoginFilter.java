package com.web.app.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.app.entity.Menu;
import com.web.app.entity.SubURL;

public class LoginFilter implements Filter {
    private String redirectURL = null;
    private final List<String> ignoreCheckURLStr = new ArrayList<String>();
    private String sessionKey = null;

    @SuppressWarnings("unchecked")
	public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getServletPath()
                + (request.getPathInfo() == null ? "" : request.getPathInfo());
        if (sessionKey == null) {
            filterChain.doFilter(request, response);
            return;
        }

        if ((!ignoreCheckURLStr.contains(uri))
                && session.getAttribute(sessionKey) == null) {
        	if (uri.indexOf("process") != -1 || uri.indexOf("mobile") != -1 || uri.indexOf("resource") != -1) {
        		response.sendRedirect(request.getContextPath() + "/mobile/pages/login.jsp");
        		return;
        	}
            response.sendRedirect(request.getContextPath() + redirectURL);
            return;
        } else {
        	if (ignoreCheckURLStr.contains(uri)) {
        		filterChain.doFilter(servletRequest, servletResponse);
        	} else if(uri.indexOf("user/logout") != -1) {
        		filterChain.doFilter(servletRequest, servletResponse);
        	} else if (uri.indexOf("/user/home") != -1) {
        		filterChain.doFilter(servletRequest, servletResponse);
        	}else if (uri.indexOf("process") != -1 || uri.indexOf("resource") != -1) {
        		filterChain.doFilter(servletRequest, servletResponse);
        	} else {
            	String queryString = request.getQueryString();
            	queryString = queryString == null ? "" : queryString;
        		int index = queryString.indexOf("&");
            	if (index != -1) {
            		queryString = queryString.substring(0, index);
            	} else {
            		queryString = queryString.substring(0, queryString.length());
            	}
            	if (queryString.indexOf("applyType") != -1 || queryString.indexOf("type") != -1) {
            		uri = uri + "?" +  queryString;
            	}
                List<Menu> menus = (List<Menu>) session.getAttribute("menus");
                String url = null;
                List<String> urls = new ArrayList<String>();
                for (Menu menu: menus) {
                	if (menu.getUrl() != null) {
                		String str = menu.getUrl();
                		if (str.indexOf("applyType") != -1 || str.indexOf("type") != -1) {
                			url = str.substring(str.indexOf('/'), str.length());
                		} else {
                			url = str.substring(str.indexOf('/'), str.indexOf("?"));
                		}
                		if (menu.getSubURLs() != null && menu.getSubURLs().size() > 0) {
                			for (SubURL s : menu.getSubURLs()) {
                    			urls.add(s.getSurl());
                    		}
                		}
                    	urls.add(url);
                	}
                }
                /*urls.add("/position/addPosition");
                urls.add("/position/deletePositionById");
                urls.add("/position/updatePositionById");
                urls.add("/position/getPositionById");*/
                if (urls.contains(uri)) {
                	filterChain.doFilter(servletRequest, servletResponse);
                } else {
                	response.sendRedirect(request.getContextPath() + redirectURL);
                    return;
                }
        	}
        }
        //filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        redirectURL = "";
        ignoreCheckURLStr.clear();
        sessionKey = "";
    }
    
    /**
     * 权限初始化不控制模块.
     * 2017-03-01
     */
    public void init(FilterConfig arg0) throws ServletException {
    	redirectURL = "/login.jsp";
        ignoreCheckURLStr.add("/login.jsp");
        ignoreCheckURLStr.add("/version.jsp");
        ignoreCheckURLStr.add("/user/loginAction");
        ignoreCheckURLStr.add("/user/changePassword");
        sessionKey = "user";
    }
}


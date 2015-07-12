package com.v4java.lostandlove.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.v4java.lal.view.admin.AdminUserVO;
import com.v4java.lostandlove.constant.ServletPathConst;
import com.v4java.lostandlove.constant.SessionConst;

/**
 * 
 * @author vincent
 **@Project Name:lostandlove-admin-web 
 * @File Name:LoginInterceptor.java 
 * @Package Name:com.v4java.lostandlove.interceptor 
 * @Date:2015年5月25日下午1:47:59 
 * @Copyright (c) 2015, 15091627595@163.com All Rights Reserved.
 */
public class LoginInterceptor implements HandlerInterceptor {

	public LoginInterceptor() { }  

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		if(ServletPathConst.ADMIN_MAPPING_URLS.contains(url)){
			return true;
		}
/*		AdminUserVO  adminUserVO=(AdminUserVO) request.getSession().getAttribute(SessionConst.ADMIN_USER);
		if (adminUserVO==null) {
			request.getRequestDispatcher("/showLogin.do").forward(request, response);
			return false;
		}*/
/*		String value = CookieUtil.getCookieValue(request.getCookies(), AdminCookieConst.ADMINUSER_COOKIE_NAME);
		if (StringUtils.isEmpty(value)) {
			new YuleException("用户未登录");
			request.getRequestDispatcher("/showLogin.do").forward(request, response);
			return false;
		}*/
		
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}

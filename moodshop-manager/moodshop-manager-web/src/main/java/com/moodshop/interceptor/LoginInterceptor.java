package com.moodshop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.service.LoginService;

/**
 * 用户登录拦截器
 * 
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1、拦截请求url
		// 2、从cookie中取admin
		String admin = CookieUtils.getCookieValue(request, "admin");
		String rank= CookieUtils.getCookieValue(request, "rank");
		// 5、如果用户session已经过期，跳转到登录页面
		if (admin == null||admin.equals("")) {
			response.sendRedirect("/");
			return false;
		}
		// 把用户对象放入request中
		request.setAttribute("admin", admin);
		request.setAttribute("rank", rank);
		// 6、如果没有过期，放行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

package com.moodshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moodshop.comm.pojo.MSResult;

/**
 * 管理员登陆
 * @author Administrator
 *
 */
public interface LoginService {
	//用户登录
	MSResult login(String username,String password, HttpServletRequest request, HttpServletResponse response);
}

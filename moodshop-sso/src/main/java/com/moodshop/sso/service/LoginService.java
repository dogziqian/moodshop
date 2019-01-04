package com.moodshop.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moodshop.comm.pojo.MSResult;
/**
 * 用户登录接口
 * @author Administrator
 *
 */
public interface LoginService {
	//用户登录
	MSResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);
	//从token中获取用户信息
	MSResult getUserByToken(String token);
	//用户退出登录，（删除缓存中的token信息）
	MSResult logout(String token,HttpServletRequest request, HttpServletResponse response);
}

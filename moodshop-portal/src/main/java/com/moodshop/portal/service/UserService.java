package com.moodshop.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moodshop.pojo.TbUser;

public interface UserService {
	//从token中获取用户信息
	TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response);
	
}

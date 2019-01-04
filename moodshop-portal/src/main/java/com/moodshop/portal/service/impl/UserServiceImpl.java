package com.moodshop.portal.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.pojo.TbUser;
import com.moodshop.portal.service.UserService;

/**
 * 用户查询管理
 * 
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final static Logger logger=Logger.getLogger(UserServiceImpl.class);
	
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN_SERVICE}")
	private String SSO_USER_TOKEN_SERVICE;

	@Override
	public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 从cookie中取token
			String token = CookieUtils.getCookieValue(request, "MS_TOKEN");
			logger.info("从cookie中获取信息:"+token);
			// 判断token是否有值
			if (StringUtils.isBlank(token)) {
				return null;
			}
			// 调用sso的服务查询用户信息
			logger.info("查询URL："+SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN_SERVICE + token);
			logger.info("查询到的用户信息为："+json);
			// 把json转换成java对象
			MSResult result = MSResult.format(json);
			logger.info("转换结果为："+result);
			if (result.getStatus() != 200) {
				return null;
			}
			// 取用户对象
			result = MSResult.formatToPojo(json, TbUser.class);
			TbUser user = (TbUser) result.getData();
			logger.info("获取到的用户为："+user);
			return user;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

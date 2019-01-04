package com.moodshop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.sso.service.LoginService;

@Controller
public class LoginController {
	
	private final static Logger logger=Logger.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public MSResult login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("用户登录开始.......");
		logger.info("前端传递参数：username="+username+",password="+password);
		try {
			MSResult result = loginService.login(username, password, request, response);
			logger.info("返回结果为："+result);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 从token中获取用户信息
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("/user/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
		logger.info("开始从token中获取结果.......");
		try {
			MSResult result = loginService.getUserByToken(token);
			logger.info("获取到的结果为："+result);
			//支持jsonp调用
			if (StringUtils.isNotBlank(callback)) {
				logger.info("开始jsonp调用.......");
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/user/logout/{token}")
	public MSResult logout(@PathVariable String token,HttpServletRequest request, HttpServletResponse response){
		logger.info("开始退出登录.......");
		logger.info("前端获取到的token为："+token);
		
		MSResult result=loginService.logout(token, request, response);
		
		return result;
	}
}

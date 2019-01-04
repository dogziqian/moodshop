package com.moodshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.service.LoginService;

/**
 * 管理员登陆
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	
	private final static Logger logger=Logger.getLogger(LoginController.class);
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/admin/login",method=RequestMethod.POST )
	@ResponseBody
	public MSResult adminLogin(String username, String password,HttpServletRequest request,HttpServletResponse response){
		logger.info("用户登录开始.......");
		logger.info("前端传递参数：username="+username+",password="+password);
			MSResult result = loginService.login(username, password,request,response);
			logger.info("返回结果为："+result);
			if(result.getData().equals("登录成功")){
				logger.info("登录成功");
				return MSResult.ok("index");
			}else if(result.getData().equals("密码错误")){
				return MSResult.ok("login");
			}
			return MSResult.ok("error");
	}
}

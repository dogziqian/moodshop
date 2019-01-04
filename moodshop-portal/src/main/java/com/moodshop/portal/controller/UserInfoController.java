package com.moodshop.portal.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbUser;
import com.moodshop.pojo.TbUserdetail;
import com.moodshop.portal.service.UserInfoService;
import com.moodshop.portal.service.UserService;
/**
 * 用户信息控制层
 * @author Administrator
 *
 */
@Controller
public class UserInfoController {

	private final static Logger logger = Logger.getLogger(UserInfoController.class);

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserService userService;
	
	@Value("${SSO_LOGIN_URL}")
	private String SSO_LOGIN_URL;
	
	@RequestMapping("/my-info")
	public String getUserInfo(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info("跳转个人信息页面....");
		// 取用户信息
		TbUser user = userService.getUserByToken(request, response);
		//TbUser user=new TbUser();
		//user.setId((long) 35);
		if (user == null) {
			response.sendRedirect(SSO_LOGIN_URL+"?redirectURL="+request.getRequestURL());
			return "";
		}
		logger.info("获取到的user为："+user);
		TbUserdetail userDetail=userInfoService.getUserInfo(user.getId());
		logger.info("向页面传递参数为："+userDetail);
		model.addAttribute("userDetail",userDetail);
		return "my-info";
	}
	/**
	 * 更新用户信息
	 * @param userDetail
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/update/userinfo",method=RequestMethod.POST)
	@ResponseBody
	public MSResult updateUserInfo(@RequestBody String paramJson,HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info("更新个人信息.....");
		paramJson=URLDecoder.decode(paramJson,"utf-8");
		logger.info("前段传递参数为："+paramJson);
		TbUserdetail userInfo=JsonUtils.jsonToPojo(paramJson, TbUserdetail.class);
		TbUser user = userService.getUserByToken(request, response);
		logger.info("获取到的user为："+user);
		MSResult result=userInfoService.updateUserInfo(user.getId(), userInfo);
		return result;
	}
	
	@RequestMapping("/queryProvence")
	@ResponseBody
	public MSResult getProvence(){
		logger.info("开始查询省份信息");
		MSResult result=userInfoService.getProvence();
		return result;
	}
	@RequestMapping("/queryCity")
	@ResponseBody
	public MSResult getCity(Integer pid){
		logger.info("开始查询省份信息");
		MSResult result=userInfoService.getCity(pid);
		return result;
	}
	
}

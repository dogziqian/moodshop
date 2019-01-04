package com.moodshop.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbUserdetail;
import com.moodshop.rest.service.UserInfoService;
import com.sun.tools.internal.ws.processor.model.Request;

@Controller
public class UserInfoController {
	
	private Logger logger=Logger.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/user/info/query/{userid}")
	@ResponseBody
	public MSResult getUserDetail(@PathVariable Long userid){
		logger.info("前端传递参数为："+userid);
		MSResult result=userInfoService.getUserDetail(userid);
		return result;
	}
	@RequestMapping(value="/user/info/update",method=RequestMethod.POST)
	@ResponseBody
	public MSResult updateUserDetail(@RequestBody String reqJson){
		
		logger.info("前端传递参数为："+reqJson);
		TbUserdetail userDetail=JsonUtils.jsonToPojo(reqJson, TbUserdetail.class);
		MSResult result=userInfoService.updateUserDetail(userDetail);
		return result;
	}
	@RequestMapping(value="/user/info/add",method=RequestMethod.POST)
	@ResponseBody
	public MSResult addUserDetail(@RequestBody String reqJson){
		
		logger.info("前端传递参数为："+reqJson);
		TbUserdetail userDetail=JsonUtils.jsonToPojo(reqJson, TbUserdetail.class);
		MSResult result=userInfoService.addUserDetail(userDetail);
		return result;
	}
}

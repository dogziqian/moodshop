package com.moodshop.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.rest.service.AddressService;
/**
 * 获取省份城市信息
 * @author Administrator
 *
 */
@Controller
public class AddressController {
	private final static Logger logger=Logger.getLogger(AddressController.class);
	
	@Autowired
	AddressService addressService;
	
	@RequestMapping("/address/provence")
	@ResponseBody
	public MSResult getProvence(){
		logger.info("开始查询省份信息");
		MSResult result=addressService.getProvence();
		return result;
	}
	
	@RequestMapping("/address/city/{pid}")
	@ResponseBody
	public MSResult getCity(@PathVariable Integer pid){
		logger.info("开始查询城市信息.....");
		MSResult result=addressService.getCityByProvence(pid);
		return result;
	}
}

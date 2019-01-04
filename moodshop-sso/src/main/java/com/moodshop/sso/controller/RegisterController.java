package com.moodshop.sso.controller;

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
import com.moodshop.pojo.TbUser;
import com.moodshop.sso.service.RegisterService;

/**
 * 用户注册控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class RegisterController {
	private final static Logger logger = Logger.getLogger(RegisterController.class);
	@Autowired
	private RegisterService registerService;
	/**
	 * 校验数据
	 * @param param
	 * @param type
	 * @param callback
	 * @return
	 */
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		logger.info("开始检验数据.......");
		logger.info("前段传递参数为：param=" + param + ",type=" + type + ",callback=" + callback);
		try {
			MSResult result = registerService.checkData(param, type);
			if (StringUtils.isNotBlank(callback)) {
				logger.info("jsonp请求开始.......");
				;
				// 请求为jsonp调用，需要支持
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			}
			logger.info("返回结果为：result=" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public MSResult register(TbUser user) {
		logger.info("用户注册开始........");
		try {
			MSResult result = registerService.register(user);
			logger.info("返回结果为："+result);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

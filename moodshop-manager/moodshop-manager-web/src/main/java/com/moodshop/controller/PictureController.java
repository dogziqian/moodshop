package com.moodshop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.moodshop.comm.pojo.PictureResult;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.service.PictureService;
/**
 * 上传图片控制层
 * @author Administrator
 *
 */
@Controller
public class PictureController {
	private final static Logger logger=Logger.getLogger(PictureController.class);
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		logger.info("开始上传图片........");
		PictureResult result = pictureService.uploadPic(uploadFile);
		//解决火狐浏览器兼容性问题
		String json=JsonUtils.objectToJson(result);
		logger.info("图片上传返回结果："+json);
		return json;
	}
}

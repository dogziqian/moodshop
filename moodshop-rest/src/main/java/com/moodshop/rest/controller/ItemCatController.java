package com.moodshop.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.rest.pojo.ItemCatResult;
import com.moodshop.rest.service.ItemCatService;
/**
 * 商品分类控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	private Logger logger=Logger.getLogger(ItemCatController.class);
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		logger.info("获取商品分类列表......."+callback);
		ItemCatResult result = itemCatService.getItemCatList();
		if (StringUtils.isBlank(callback)) {
			//需要把result转换成字符串
			String json = JsonUtils.objectToJson(result);
			return json;
		}
		//如果字符串不为空，需要支持jsonp调用
		//需要把result转换成字符串
		String json = JsonUtils.objectToJson(result);
		return callback + "(" + json + ");";
	}
	
	@RequestMapping("/sync/itemCat/{parentId}")
	@ResponseBody
	public MSResult sysncContent(@PathVariable Long parentId) {
		logger.info("开始同步redis缓存,前段传递参数为："+parentId);
		try {
			MSResult result=itemCatService.syncItemCat(parentId);
			return result;
		} catch (Exception e) {
			logger.info("同步缓存异常.....");
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

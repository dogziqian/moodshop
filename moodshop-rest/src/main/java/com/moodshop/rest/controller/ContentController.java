package com.moodshop.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.pojo.TbContent;
import com.moodshop.rest.service.ContentService;

/**
 * 内容控制层
 * @author Administrator
 *
 */
@Controller
public class ContentController {
	
	private final static Logger logger=Logger.getLogger(ContentController.class);
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/{cid}")
	@ResponseBody
	public MSResult getContentList(@PathVariable Long cid) {
		logger.info("开始根据cid查询内容列表,前段传递参数为："+cid);
		try {
			List<TbContent> list = contentService.getContentList(cid);
			return MSResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/sync/content/{cid}")
	@ResponseBody
	public MSResult sysncContent(@PathVariable Long cid) {
		logger.info("开始同步redis缓存,前段传递参数为："+cid);
		try {
			MSResult result=contentService.syncContent(cid);
			return result;
		} catch (Exception e) {
			logger.info("同步缓存异常.....");
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

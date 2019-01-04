package com.moodshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.portal.service.StaticPageService;

@Controller
public class StaticPageController {
	@Autowired
	private StaticPageService staticPageService;
	
	@RequestMapping("/gen/item/{itemId}")
	@ResponseBody
	public MSResult genItemPage(@PathVariable Long itemId) {
		try {
			MSResult result = staticPageService.genItemHtml(itemId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

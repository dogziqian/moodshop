package com.moodshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moodshop.portal.service.ContentService;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	
	/**
	 * 访问首页
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//取大广告位内容
		String json=contentService.getAd1List();
		//传递给页面
		model.addAttribute("ad1", json);
		return "index";
	}
	/**
	 * 跳转其他页面
	 * @param page
	 * @return
	 */
//	@RequestMapping("/{page}")
//	public String showPage(@PathVariable String page) {
//		return page;
//	}
//	/**
//	 * post测试
//	 * @param name
//	 * @param pass
//	 * @return
//	 */
//	@RequestMapping(value="/posttest",method=RequestMethod.POST)
//	@ResponseBody
//	public String testPost(String name,String pass){
//		System.out.println(name);
//		System.out.println(pass);
//		return "OK";
//	}
}

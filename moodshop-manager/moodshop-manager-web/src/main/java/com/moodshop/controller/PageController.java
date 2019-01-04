package com.moodshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制
 * @author Administrator
 *
 */

@Controller
public class PageController {
	
	/**
	 * 首页展示
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex() {
		return "login";
	}
	
	/**
	 * 页面展示
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
}

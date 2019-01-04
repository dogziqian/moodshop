package com.moodshop.portal.controller;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moodshop.portal.pojo.SearchResult;
import com.moodshop.portal.service.SearchService;

@Controller
public class SearchController {
	private final static Logger logger=Logger.getLogger(SearchController.class);
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam("q")String keyword, 
			@RequestParam(defaultValue="1")Integer page, 
			@RequestParam(defaultValue="60")Integer rows, Model model) {
		logger.info("开始查询商品。。。。");
		logger.info("前段传递数据：keyword="+keyword+",page="+page+",rows="+rows);
		//get乱码处理
		try {
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			keyword = "";
			e.printStackTrace();
		}
		SearchResult searchResult = searchService.search(keyword, page, rows);
		//参数传递 给页面
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", searchResult.getCurPage());
		
		//返回逻辑视图
		return "search";
	}
}

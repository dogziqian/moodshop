package com.moodshop.search.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.search.pojo.SearchResult;
import com.moodshop.search.service.SearchService;
/**
 * 查询发布服务
 * @author Administrator
 *
 */
@Controller
public class SearchController {
	private final static Logger logger=Logger.getLogger(SearchController.class);
	@Autowired
	private SearchService searchService;

	@RequestMapping("/q")
	@ResponseBody
	public MSResult search(@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
		logger.info("开始查询......前端传递参数为：keyword="+keyword+",page="+page+",rows="+rows);
		try {
			// 转换字符集
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
			SearchResult searchResult = searchService.search(keyword, page, rows);
			return MSResult.ok(searchResult);
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

package com.moodshop.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.search.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/importall")
	@ResponseBody
	public MSResult importall(){
		try {
			MSResult result=itemService.importItems();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}

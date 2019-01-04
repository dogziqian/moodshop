package com.moodshop.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.service.ItemParamService;
import com.moodshop.service.impl.AdminLogUtil;

/**
 * 商品参数处理类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	private final static Logger logger=Logger.getLogger(ItemParamController.class);
	@Autowired
	private ItemParamService itemParamService;
	@Autowired
	private AdminLogUtil adminLogUtil;
	
	/**
	 * 获取分页结果
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page,Integer rows){
		logger.info("查询商品参数列表开始......");
		EasyUIDataGridResult result=itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	/**
	 * 根据分页id获取商品你参数
	 * @param cid
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public MSResult getItemCatByCid(@PathVariable Long cid) {
		logger.info("根据分类id查询商品参数.....");
		logger.info("前端传递参数------------cid="+cid);
		MSResult result = itemParamService.getItemParamByCid(cid);
		return result;
	}
	
	/**
	 * 插入商品参数
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public MSResult insertItemParam(@PathVariable Long cid, String paramData,HttpServletRequest request) {
		logger.info("插入商品参数......");
		logger.info("前端传递参数------------cid="+cid);
		MSResult result = itemParamService.insertItemParam(cid, paramData);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "插入商品类别参数"+cid);
		return result;
	}
	/**
	 * 删除商品参数
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public MSResult deleteItemParam(@RequestParam("ids") Long id,HttpServletRequest request){
		logger.info("删除商品参数.......");
		logger.info("前端传递参数------------id="+id);
		MSResult result=itemParamService.deleteItemParam(id);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "删除商品类别参数"+id);
		return result;
	}
}

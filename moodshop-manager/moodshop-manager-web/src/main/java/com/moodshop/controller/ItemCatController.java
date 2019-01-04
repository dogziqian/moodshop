package com.moodshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.EasyUITreeNode;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.service.ItemCatService;
import com.moodshop.service.impl.AdminLogUtil;

/**
 * 商品列表控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	private final static Logger logger=Logger.getLogger(ItemCatController.class);
	
	@Autowired
	private ItemCatService itemCatService;
	
	@Autowired
	private AdminLogUtil adminLogUtil;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		logger.info("查询商品分类列表");
		logger.info("前段传递参数--------parentId="+parentId);
		List<EasyUITreeNode> list=itemCatService.getItemTagList(parentId);
		return list;
	}
	/**
	 * 创建新节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public MSResult createNode(HttpServletRequest request,Long parentId, String name) {
		logger.info("前端传递参数：parentID："+parentId+"名称："+name);
		MSResult result = itemCatService.insertCatgory(parentId, name);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "创建商品分类"+name);
		return result;
	}
	/**
	 * 更新分类名称
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public MSResult updateNode(HttpServletRequest request,Long id, String name){
		logger.info("开始更新商品分类,前段传递参数为：id："+id+",name:"+name);
		MSResult result=itemCatService.updateCatgory(id, name);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "更新商品分类"+name);
		return result;
	}
	/**
	 * 删除分类名称
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public MSResult deleteNode(HttpServletRequest request,Long id){
		logger.info("开始商品分类,前段传递参数为：id："+id);
		MSResult result=itemCatService.deleteCategory(id);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "删除商品分类"+id);
		return result;
	}
}

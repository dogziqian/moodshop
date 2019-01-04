package com.moodshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.EasyUITreeNode;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.pojo.TbAdminLog;
import com.moodshop.service.ContentCatgoryService;
import com.moodshop.service.impl.AdminLogUtil;
/**
 * 内容分类控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	private final static Logger logger=Logger.getLogger(ContentCategoryController.class);
	@Autowired
	private ContentCatgoryService contentCatgoryService;
	@Autowired
	private AdminLogUtil adminLogUtil;
	/**
	 * 获取内容分类列表
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		logger.info("前端传递参数为："+parentId);
		List<EasyUITreeNode> list = contentCatgoryService.getContentCatList(parentId);
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
		MSResult result = contentCatgoryService.insertCatgory(parentId, name);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "创建内容节点"+name);
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
		logger.info("开始更新节点信息,前段传递参数为：id："+id+",name:"+name);
		MSResult result=contentCatgoryService.updateCatgory(id, name);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "更新内容分类节点"+name);
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
		logger.info("开始删除节点信息,前段传递参数为：id："+id);
		MSResult result=contentCatgoryService.deleteCategory(id);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "删除内容分类节点"+id);
		return result;
	}
}

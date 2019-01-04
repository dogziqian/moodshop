package com.moodshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.pojo.TbContent;
import com.moodshop.service.ContentService;
import com.moodshop.service.impl.AdminLogUtil;

/**
 * 内容管理控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	private final static Logger logger=Logger.getLogger(ContentController.class);
	@Autowired
	private ContentService contentService;
	@Autowired
	private AdminLogUtil adminLogUtil;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	/**
	 * 根据分类id查询内容列表
	 * @param page
	 * @param rows
	 * @param categroyId
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody	
	public EasyUIDataGridResult getContentList(Integer page,Integer rows,Long categoryId){
		logger.info("开始查询内容列表,前段传递参数为：page:"+page+",rows:"+rows+",categroyId:"+categoryId);
		EasyUIDataGridResult result=contentService.getContentList(page,rows,categoryId);	
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public MSResult insertContent(HttpServletRequest request,TbContent content){
		logger.info("开始插入数据，前段传递参数："+content);
		MSResult result=contentService.insertContent(content);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "新建内容"+content.getId());
		//调用rest中发布的服务,同步缓存
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public MSResult deleteContent(@RequestParam("ids") Long id,HttpServletRequest request){
		logger.info("开始删除数据，前段传递参数："+id);
		MSResult result=contentService.deleteContent(id);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "删除内容"+id);
		//调用rest中发布的服务,同步缓存
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+id);
		return result;
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public MSResult editContent(TbContent content,HttpServletRequest request){
		logger.info("开始更新数据，前段传递参数："+content);
		MSResult result=contentService.updateContent(content);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "编辑内容"+content.getId());
		//调用rest中发布的服务,同步缓存
		HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_SYNC_URL+content.getCategoryId());
		return result;
	}
}

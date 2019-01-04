package com.moodshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.pojo.TbAdmin;
import com.moodshop.service.AdminService;
import com.moodshop.service.impl.AdminLogUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger=Logger.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	@Autowired
	AdminLogUtil adminLogUtil;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getAdminList(Integer page,Integer rows){
		logger.info("获取管理员列表");
		EasyUIDataGridResult result=adminService.getAdminList(page,rows);
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public MSResult addAdmin(TbAdmin admin,HttpServletRequest request){
		logger.info("添加管理员,前端传递参数为："+admin.getUsername());
		
		MSResult result=adminService.addAdmin(admin);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "新增管理员"+admin.getUsername());
		return result;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public MSResult updateAdmin(TbAdmin admin,HttpServletRequest request){
		logger.info("修改管理员状态,前端传递参数为："+admin.getUsername());
		MSResult result=adminService.updateAdmin(admin);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "修改管理员状态"+admin.getUsername());
		return result;
	}
	@RequestMapping("/adminLog/list")
	@ResponseBody
	public EasyUIDataGridResult getLog(Integer page,Integer rows){
		logger.info("获取管理员操作日志");
		EasyUIDataGridResult result=adminLogUtil.getAdminLogList(page, rows);
		return result;
	}
	
}

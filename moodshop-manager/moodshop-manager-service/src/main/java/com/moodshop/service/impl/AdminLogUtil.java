package com.moodshop.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.mapper.TbAdminLogMapper;
import com.moodshop.pojo.TbAdminLog;
import com.moodshop.pojo.TbAdminLogExample;

@Service
public class AdminLogUtil {
	
	private final static Logger logger=Logger.getLogger(AdminLogUtil.class);
	@Autowired
	private TbAdminLogMapper adminLogMapper;
	
	
	public  MSResult addLog(String adminName,String opration){
		logger.info("添加日志.....");
		logger.info("准备实体类");
		TbAdminLog adminLog=new TbAdminLog();
		adminLog.setUsername(adminName);
		adminLog.setOptime(new Date());
		adminLog.setOpration(opration);
		Integer count=adminLogMapper.insert(adminLog);
		logger.info("影响行数："+count);
		return MSResult.ok();
	} 
	
	
	public EasyUIDataGridResult getAdminLogList(int page, int rows) {
		logger.info("查询管理员列表");
		// 分页处理
		PageHelper.startPage(page, rows);
		TbAdminLogExample example = new TbAdminLogExample();
		List<TbAdminLog> list = adminLogMapper.selectByExample(example);
		// 获取分页结果
		PageInfo<TbAdminLog> pageInfo = new PageInfo<>(list);
		// 返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		logger.info("共查询到：" + pageInfo.getTotal() + "条记录");
		return result;
	}
	
}

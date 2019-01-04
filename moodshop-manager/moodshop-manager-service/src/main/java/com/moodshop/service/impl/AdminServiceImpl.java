package com.moodshop.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.mapper.TbAdminMapper;
import com.moodshop.pojo.TbAdmin;
import com.moodshop.pojo.TbAdminExample;
import com.moodshop.pojo.TbItem;
import com.moodshop.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

	@Autowired
	private TbAdminMapper adminMappper;

	/**
	 * 获取管理员列表
	 */
	@Override
	public EasyUIDataGridResult getAdminList(int page, int rows) {
		logger.info("查询管理员列表");
		// 分页处理
		PageHelper.startPage(page, rows);
		TbAdminExample example = new TbAdminExample();
		List<TbAdmin> list = adminMappper.selectByExample(example);
		// 获取分页结果
		PageInfo<TbAdmin> pageInfo = new PageInfo<>(list);
		// 返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		logger.info("共查询到：" + pageInfo.getTotal() + "条记录");
		return result;
	}

	@Override
	public MSResult updateAdmin(TbAdmin admin) {
		logger.info("更新管理员.......");
		Integer count = adminMappper.updateByPrimaryKeySelective(admin);
		logger.info("影响的行数为：" + count);
		return MSResult.ok();
	}

	@Override
	public MSResult addAdmin(TbAdmin admin) {
		logger.info("新增管理员....");
		Integer count = adminMappper.insertSelective(admin);
		logger.info("影响的行数为：" + count);
		return MSResult.ok();
	}

}

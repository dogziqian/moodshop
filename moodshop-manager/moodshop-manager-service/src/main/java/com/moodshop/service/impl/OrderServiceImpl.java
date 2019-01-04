package com.moodshop.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.mapper.TbOrderMapper;
import com.moodshop.pojo.TbOrder;
import com.moodshop.pojo.TbOrderExample;
import com.moodshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final static Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	private TbOrderMapper orderMapper;

	/**
	 * 获得订单列表
	 */
	@Override
	public EasyUIDataGridResult getOrderList(int page, int rows) {
		logger.info("开始分页查询订单列表........");
		// 分页处理
		PageHelper.startPage(page, rows);
		// 执行查询
		TbOrderExample example = new TbOrderExample();
		List<TbOrder> list = orderMapper.selectByExample(example);
		// 获取分页结果
		PageInfo<TbOrder> pageInfo = new PageInfo<>(list);
		// 返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		logger.info("共查询到：" + pageInfo.getTotal() + "条记录");
		return result;
	}
	/**
	 * 更新订单信息
	 */
	@Override
	public MSResult updateOrder(TbOrder order) {
		logger.info("开始更新订单信息........"+order.getOrderId());
		int num=orderMapper.updateByPrimaryKeySelective(order);
		logger.info("影响行数为："+num);
		logger.info("订单信息更新完成......");
		return MSResult.ok();
	}

}

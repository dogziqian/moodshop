package com.moodshop.service;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbOrder;

/**
 * 订单业务层接口
 * @author Administrator
 *
 */
public interface OrderService {
	/**
	 * 获得订单列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGridResult getOrderList(int page,int rows);
	/**
	 * 更新订单
	 * @param orderId
	 * @param order
	 * @return
	 */
	MSResult updateOrder(TbOrder order);
}

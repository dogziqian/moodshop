package com.moodshop.order.service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.order.pojo.OrderInfo;
import com.moodshop.pojo.TbOrder;
/**
 * 订单业务层接口
 * @author Administrator
 *
 */
public interface OrderService {
	//创建订单
	MSResult createOrder(OrderInfo orderInfo);
	//更新订单
	MSResult updateOrder(TbOrder order);
	//根据订单ID获取订单项
	MSResult getOrderItem(String orderId);
	//根据用户id查询订单列表
	MSResult getOrderList(Long userId);
	//根据订单id删除订单信息
	MSResult deleteOrder(String orderId);
}

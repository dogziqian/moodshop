package com.moodshop.portal.service;

import java.util.List;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbOrder;
import com.moodshop.pojo.TbOrderItem;
import com.moodshop.portal.pojo.OrderInfo;

/**
 * 订单业务层接口
 * @author Administrator
 *
 */
public interface OrderService {
	//创建订单
	String createOrder(OrderInfo orderInfo);
	//更新订单状态
	MSResult updateOrderStatus(TbOrder order);
	//获取订单项
	TbOrderItem getOrderItem(String orderId);
	//获取订单列表
	List<OrderInfo> getOrderList(Long userId);
	//删除订单
	MSResult deleteOrder(String orderId);
}

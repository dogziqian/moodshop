package com.moodshop.pojo;

import java.util.List;

import com.moodshop.pojo.TbOrder;
import com.moodshop.pojo.TbOrderItem;
import com.moodshop.pojo.TbOrderShipping;
/**
 * 接收订单信息实体类
 * @author Administrator
 *
 */
public class OrderInfo extends TbOrder {
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}

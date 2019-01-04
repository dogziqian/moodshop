package com.moodshop.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.pojo.TbOrder;
import com.moodshop.service.OrderService;
import com.moodshop.service.impl.AdminLogUtil;

@Controller
public class OrderController {
	private final static Logger logger=Logger.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private AdminLogUtil adminLogUtil;
	
	/**
	 * 获取订单列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/order/list")
	@ResponseBody
	public EasyUIDataGridResult getOrderList(Integer page,Integer rows){
		logger.info("开始获取分页列表");
		EasyUIDataGridResult result= orderService.getOrderList(page, rows);
		return result;
	} 
	/**
	 * 更新订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/order/edit",method=RequestMethod.POST)
	@ResponseBody
	public MSResult updateOrder(TbOrder order,HttpServletRequest request){
		logger.info("开始更新订单信息"+order.getOrderId()+"-"+order.getPayment()+"-"+order.getShippingCode()+"-"+order.getShippingName());
		order.setUpdateTime(new Date());
		String orderId=order.getOrderId().replace(",", "");
		order.setOrderId(orderId);
		MSResult result= orderService.updateOrder(order);
		String adminName = CookieUtils.getCookieValue(request, "admin");
		adminLogUtil.addLog(adminName, "更新订单"+order.getOrderId());
		return result;
	} 
}

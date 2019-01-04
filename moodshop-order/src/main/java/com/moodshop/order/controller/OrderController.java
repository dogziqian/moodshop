package com.moodshop.order.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.ExceptionUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.order.pojo.OrderInfo;
import com.moodshop.order.service.OrderService;
import com.moodshop.pojo.TbOrder;

/**
 * 订单控制层
 * 
 * @author Administrator
 *
 */
@Controller
public class OrderController {
	private final static Logger logger = Logger.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;

	/**
	 * 创建订单
	 * 
	 * @param orderInfo
	 * @return
	 */
	@RequestMapping(value = "/order/create", method = RequestMethod.POST)
	@ResponseBody
	public MSResult createOrder(@RequestBody OrderInfo orderInfo) {
		logger.info("前端传递参数为:" + orderInfo);
		try {
			MSResult result = orderService.createOrder(orderInfo);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	/**
	 * 更新订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/order/update", method = RequestMethod.POST)
	@ResponseBody
	public MSResult createOrder(@RequestBody String reqjson) {
		logger.info("前端传递参数为：" + reqjson);
		try {
			TbOrder order = JsonUtils.jsonToPojo(reqjson, TbOrder.class);
			MSResult result = orderService.updateOrder(order);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return MSResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 根据订单id查询订单项
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/order/query/{orderId}")
	@ResponseBody
	public MSResult getOrder(@PathVariable String orderId) {
		logger.info("前端传递参数为：orderId=" + orderId );
		MSResult result = orderService.getOrderItem(orderId);
		return result;

	}
	/**
	 * 根据用户id查询订单列表
	 * @param userId
	 * @return
	 */
	@RequestMapping("/order/list/{userId}")
	@ResponseBody
	public MSResult orderList(@PathVariable Long userId){
		logger.info("前端传递参数为："+userId);
		MSResult result=orderService.getOrderList(userId);
		return result;
	}
	/**
	 * 根据订单id删除订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/order/delete/{orderId}")
	public MSResult deleteOrder(@PathVariable String orderId){
		logger.info("开始删除订单....");
		logger.info("前端传递参数为："+orderId);
		MSResult result=orderService.deleteOrder(orderId);
		return result;
	}
}

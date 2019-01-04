package com.moodshop.portal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbOrder;
import com.moodshop.pojo.TbOrderItem;
import com.moodshop.portal.pojo.OrderInfo;
import com.moodshop.portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	@Value("${ORDER_UPD_URL}")
	private String ORDER_UPD_URL;
	@Value("${ORDERITEM_QUERY_URL}")
	private String ORDERITEM_QUERY_URL;
	@Value("${ORDER_LIST_URL}")
	private String ORDER_LIST_URL;
	@Value("${ORDER_DEL_URL}")
	private String ORDER_DEL_URL;

	
	@Override
	public String createOrder(OrderInfo orderInfo) {
		// 把OrderInfo转换成json
		String json = JsonUtils.objectToJson(orderInfo);
		// 提交订单数据
		logger.info("请求的URL为：" + ORDER_BASE_URL + ORDER_CREATE_URL);
		String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
		logger.info("返回结果为：" + jsonResult);
		// 转换成java对象
		MSResult msResult = MSResult.format(jsonResult);
		// 取订单号
		String orderId="";
		try {
			orderId = msResult.getData().toString();
			logger.info("订单号为：" + orderId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}

	@Override
	public MSResult updateOrderStatus(TbOrder order) {
		logger.info("请求的URL为：" + ORDER_BASE_URL + ORDER_UPD_URL );
		String reqjson=JsonUtils.objectToJson(order);
		String rspjson = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_UPD_URL, reqjson);
		logger.info("返回报文为：" + rspjson);
		// 转换成java对象
		MSResult msResult = MSResult.format(rspjson);
		return msResult;
	}

	@Override
	public TbOrderItem getOrderItem(String orderId) {
		logger.info("请求的URL为：" + ORDER_BASE_URL + ORDERITEM_QUERY_URL+orderId );
		String rspjson=HttpClientUtil.doGet(ORDER_BASE_URL + ORDERITEM_QUERY_URL+orderId);
		logger.info("返回报文为："+rspjson);
		MSResult msResult = MSResult.formatToPojo(rspjson, TbOrderItem.class);
		TbOrderItem orderItem=(TbOrderItem) msResult.getData();
		return orderItem;
	}

	@Override
	public List<OrderInfo> getOrderList(Long userId) {
		logger.info("请求的URL为：" + ORDER_BASE_URL + ORDER_LIST_URL+userId );
		String rspjson=HttpClientUtil.doGet(ORDER_BASE_URL + ORDER_LIST_URL+userId);
		logger.info("返回报文为："+rspjson);
		MSResult result=MSResult.formatToList(rspjson, OrderInfo.class);
		List<OrderInfo> list=(List<OrderInfo>) result.getData();
		return list;
	}

	@Override
	public MSResult deleteOrder(String orderId) {
		logger.info("请求的URL为：" + ORDER_BASE_URL + ORDER_DEL_URL+orderId );
		String rspjson=HttpClientUtil.doGet(ORDER_BASE_URL + ORDER_DEL_URL+orderId);
		logger.info("返回报文为："+rspjson);
		MSResult result=MSResult.format(rspjson);
		return result;
	}

}

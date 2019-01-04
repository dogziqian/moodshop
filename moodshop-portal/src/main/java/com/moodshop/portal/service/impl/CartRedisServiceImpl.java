package com.moodshop.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbItem;
import com.moodshop.portal.service.CartRedisService;

@Service
public class CartRedisServiceImpl implements CartRedisService {
	
	private Logger logger=Logger.getLogger(CartRedisServiceImpl.class);
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${CART_ADD_API_URL}")
	private String CART_ADD_API_URL;
	
	@Override
	public MSResult addItemToCart(Long itemId, Integer num, String cartKey) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("num", Integer.toString(num));
		params.put("itemId",Long.toString(itemId) );
		params.put("cartKey", cartKey);
		String reqjson=JsonUtils.objectToJson(params);
		logger.info("请求的json为："+reqjson);
		logger.info("请求的url为："+REST_BASE_URL+CART_ADD_API_URL);
		String rspjson=HttpClientUtil.doGet(REST_BASE_URL+CART_ADD_API_URL, params);
		logger.info("返回的结果为："+rspjson);
		MSResult msResult=MSResult.formatToPojo(reqjson, MSResult.class);
		return msResult;
	}

}

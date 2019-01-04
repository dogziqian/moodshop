package com.moodshop.portal.service;

import com.moodshop.comm.pojo.MSResult;

public interface CartRedisService {

	MSResult addItemToCart(Long itemId, Integer num, String cartKey);
	
}

package com.moodshop.rest.service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbCart;

public interface CartService {
	/**
	 * 添加购物车缓存
	 * @param itemId
	 * @param nums
	 * @param cartKey
	 * @return
	 */
	MSResult addRedisCart(Long itemId, Integer num, String cartKey);
	/**
	 * 添加数据库
	 * @param cart
	 * @return
	 */
	MSResult addItemToCart(TbCart cart);
	//获取购物车列表
	MSResult getCartList(Long userId);
	//更新
	MSResult updateCart(Long itemId, Integer num, Long userId);
	//删除
	MSResult deleteCart(Long itemId, Long userId);
}

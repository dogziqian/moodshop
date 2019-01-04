package com.moodshop.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbOrderItem;
import com.moodshop.portal.pojo.CartItem;

/**
 * 购物车接口
 * 
 * @author Administrator
 *
 */
public interface CartService {
	// 添加购物车
	MSResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
	// 获取购物车列表
	List<CartItem> getCartItems(HttpServletRequest request);
	//更新购物车物品数量
	MSResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
	//删除购物车中商品
	MSResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
	//持久化保存购物车内容
	MSResult addEnCart(Long itemId, Integer num,Long userId, HttpServletRequest request);
	//从数据库获取购物车内容
	List<CartItem> getCartEnItems(Long userId);
	//更新数据库中购物车内容
	MSResult updateEnCartItem(Long itemId, Integer num, Long userId);
	//删除数据库中购物车内容
	MSResult deleteEnCartItem(Long itemId, Long userId);

}

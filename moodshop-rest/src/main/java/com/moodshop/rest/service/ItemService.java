package com.moodshop.rest.service;


import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbItem;
import com.moodshop.pojo.TbItemDesc;
import com.moodshop.pojo.TbItemParamItem;
import com.moodshop.rest.pojo.SearchResult;

public interface ItemService {
	//根据商品id获取商品
	TbItem getItemById(Long id);
	//根据商品id获取描述信息
	TbItemDesc getItemDescById(Long id);
	//根据商品id获取参数信息
	TbItemParamItem getItemParamById(Long id);
	/**
	 * 同步商品缓存
	 * @param itemId
	 * @return
	 */
	MSResult syncItem(long itemId);
	/**
	 * 获取打折价格
	 * @param itemId
	 * @param num
	 * @return
	 */
	String getItemDiscountById(Long itemId, Integer num);
	//根据分类id获取商品列表
	SearchResult getItemByCatId(String catId);
	//更新商品库存
	Integer updateItemNum(Long itemId,Integer num);
}

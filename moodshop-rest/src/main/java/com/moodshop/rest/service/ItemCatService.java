package com.moodshop.rest.service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.rest.pojo.ItemCatResult;
/**
 * 商品分类接口
 * @author Administrator
 *
 */
public interface ItemCatService {
	/**
	 * 获取商品列表
	 * @return
	 */
	ItemCatResult getItemCatList();
	/**
	 * 同步缓存信息
	 * @param cid
	 * @return
	 */
	MSResult syncItemCat(long parentId);
}

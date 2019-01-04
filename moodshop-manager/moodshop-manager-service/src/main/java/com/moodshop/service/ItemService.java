package com.moodshop.service;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbItem;
/**
 * 商品业务层接口
 * @author Administrator
 *
 */
public interface ItemService {
	/**
	 * 通过商品id获取商品
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(Long itemId);
	/**
	 * 获取分页结果
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGridResult getItemList(int page,int rows);
	/**
	 * 新增商品
	 * @param item
	 * @param desc
	 * @return
	 */
	MSResult createItem(TbItem item,String desc,String itemParam,String disprice);
	/**
	 * 根据商品id获取商品参数列表html片段
	 * @param itemId
	 * @return
	 */
	String getItemParamHtml(Long itemId);
	/**
	 * 修改商品状态
	 * @param itemId
	 * @param status
	 * @return
	 */
	MSResult updateItemStatus(Long itemId,Byte status);
	/**
	 * 编辑商品
	 * @param itemId
	 * @param status
	 * @return
	 */
	MSResult updateItem(Long itemId,TbItem item,String desc,String itemParam);
	/**
	 * 获取商品描述
	 * @param itemId
	 * @return
	 */
	MSResult getItemDesc(Long itemId);
	/**
	 * 获取商品参数信息
	 * @param itemId
	 * @return
	 */
	MSResult getItemParam(Long itemId);
	/**
	 * 获取商品打折信息
	 * @param itemId
	 * @return
	 */
	MSResult getItemDiscount(Long itemId);
}

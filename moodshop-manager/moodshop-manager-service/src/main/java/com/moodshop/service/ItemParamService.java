package com.moodshop.service;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
/**
 * 商品参数业务层接口
 * @author Administrator
 *
 */
import com.moodshop.comm.pojo.MSResult;
public interface ItemParamService {
	/**
	 * 获取分页结果
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGridResult getItemParamList(int page,int rows);
	/**
	 * 根据分类id获取商品参数
	 * @param cid
	 * @return
	 */
	MSResult getItemParamByCid(Long cid);
	/**
	 * 向规格参数表中插入记录
	 * @param cid
	 * @param paramData
	 * @return
	 */
	MSResult insertItemParam(Long cid, String paramData);
	/**
	 * 根据id删除商品规格参数
	 * @param id
	 * @return
	 */
	MSResult deleteItemParam(Long id);
}

package com.moodshop.service;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbContent;

/**
 * 内容业务层接口
 * @author Administrator
 *
 */
public interface ContentService {
	/**
	 * 根据分类id获取内容列表
	 * @param categroyId
	 * @return
	 */
	EasyUIDataGridResult getContentList(int page,int rows,Long categroyId);
	/**
	 * 插入内容
	 * @param content
	 * @return
	 */
	MSResult insertContent(TbContent content);
	/**
	 * 删除内容
	 * @param content
	 * @return
	 */
	MSResult deleteContent(Long id);
	/**
	 * 更新内容
	 * @param content
	 * @return
	 */
	MSResult updateContent(TbContent content);
}

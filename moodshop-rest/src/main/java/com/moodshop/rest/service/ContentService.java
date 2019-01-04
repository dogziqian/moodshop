package com.moodshop.rest.service;

import java.util.List;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbContent;

/**
 * 内容业务层接口
 * @author Administrator
 *
 */
public interface ContentService {
	/**
	 * 根据分类id查询内容列表
	 * @param cid
	 * @return
	 */
	List<TbContent> getContentList(Long cid);
	/**
	 * 同步redis缓存
	 * @param cid
	 * @return
	 */
	MSResult syncContent(Long cid);
}

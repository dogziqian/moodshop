package com.moodshop.service;

import java.util.List;

import com.moodshop.comm.pojo.EasyUITreeNode;
import com.moodshop.comm.pojo.MSResult;
/**
 * 商品种类业务层接口
 * @author Administrator
 *
 */
public interface ItemCatService {
	/**
	 * 根据父级ID获取商品种类
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getItemTagList(long parentId);
	/**
	 * 新建分类
	 * @param parentId
	 * @param name
	 * @return
	 */
	MSResult insertCatgory(Long parentId, String name);
	/**
	 * 更新分类
	 * @param id
	 * @param name
	 * @return
	 */
	MSResult updateCatgory(Long id, String name);
	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	MSResult deleteCategory(Long id);
}

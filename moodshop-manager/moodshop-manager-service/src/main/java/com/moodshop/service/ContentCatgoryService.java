package com.moodshop.service;

import java.util.List;

import com.moodshop.comm.pojo.EasyUITreeNode;
import com.moodshop.comm.pojo.MSResult;
/**
 * 内容分类管理业务层接口
 * @author Administrator
 *
 */
public interface ContentCatgoryService {
	/**
	 * 获得内容分类菜单列表
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getContentCatList(Long parentId);
	/**
	 * 新增内容菜单
	 * @param parentId
	 * @param name
	 * @return
	 */
	MSResult insertCatgory(Long parentId, String name);
	/**
	 * 更新内容菜单
	 * @param id
	 * @param newname
	 * @return
	 */
	MSResult updateCatgory(Long id, String newname);
	/**
	 * 删除菜单内容
	 * @param id
	 * @return
	 */
	MSResult deleteCategory(Long id);
}

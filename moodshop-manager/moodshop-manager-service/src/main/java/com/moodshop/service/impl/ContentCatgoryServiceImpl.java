package com.moodshop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.EasyUITreeNode;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.mapper.TbContentCategoryMapper;
import com.moodshop.pojo.TbContentCategory;
import com.moodshop.pojo.TbContentCategoryExample;
import com.moodshop.pojo.TbContentCategoryExample.Criteria;
import com.moodshop.service.ContentCatgoryService;

/**
 * 获取内容分类业务层实现类
 * 
 * @author Administrator
 *
 */
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	private final static Logger logger = Logger.getLogger(ContentCatgoryServiceImpl.class);
	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	/**
	 * 获取内容分类列表
	 */
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		logger.info("根据parentId查询子节点列表.......");
		// 根据parentId查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		logger.info("获取的结果为：" + list);
		// 转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			// 创建一EasyUITreeNode节点
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			// 添加到列表
			resultList.add(node);
		}
		return resultList;
	}

	/**
	 * 新增内容菜单
	 */
	@Override
	public MSResult insertCatgory(Long parentId, String name) {
		logger.info("新增内容菜单.......");
		// 创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		// 1(正常),2(删除)
		contentCategory.setStatus(1);
		contentCategory.setIsParent(false);
		// '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数'
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		logger.info("开始插入数据.......");
		// 插入数据
		contentCategoryMapper.insert(contentCategory);
		// 取返回的主键
		Long id = contentCategory.getId();
		logger.info("新添加的节点的id为："+id);
		// 判断父节点的isparent属性
		// 查询父节点
		logger.info("开始查询父节点.......");
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			// 更新父节点
			logger.info("更新父节点.......");
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		
		// 返回主键
		return MSResult.ok(id);
	}
	/**
	 * 更新内容菜单名称
	 */
	@Override
	public MSResult updateCatgory(Long id, String newname) {
		logger.info("开始更新内容分类名称..........");
		//创建pojo
		TbContentCategory contenCategory=new TbContentCategory();
		contenCategory.setId(id);
		contenCategory.setName(newname);
		contentCategoryMapper.updateByPrimaryKeySelective(contenCategory);
		return MSResult.ok();
	}
	/**
	 * 删除菜单名称
	 */
	@Override
	public MSResult deleteCategory(Long id) {
		logger.info("开始删除菜单内容........");
		//查询该节点是否为父节点
		TbContentCategory contentCategory=contentCategoryMapper.selectByPrimaryKey(id);
		if(contentCategory.getIsParent()){
			//是父节点
			logger.info("递归删除子节点");
			deleteNodeByParent(id);
		}else{
			//是子节点，判断父节点是否包含其他子节点
			logger.info("查询父节点相同的子节点......");
			TbContentCategoryExample example=new TbContentCategoryExample();
			Criteria criteria=example.createCriteria();
			criteria.andParentIdEqualTo(contentCategory.getParentId());
			List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
			if(list.size()<=1){
				//父节点没有其余子节点，需要更改父节点isparent为0
				TbContentCategory parentcontentCategory=new TbContentCategory();
				parentcontentCategory.setId(contentCategory.getParentId());
				parentcontentCategory.setIsParent(false);
				logger.info("更新父节点状态.......");
				contentCategoryMapper.updateByPrimaryKeySelective(parentcontentCategory);
			}
			logger.info("删除该节点.......");
			contentCategoryMapper.deleteByPrimaryKey(id);
		}
		return MSResult.ok();
	}
	/**
	 * 递归删除节点
	 * @param id
	 */
	private void deleteNodeByParent(Long id) {
		//根据父id查询子id
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
		//遍历数组中的元素，查询是否为父节点
		if(list!=null&&list.size()>0){
			for(TbContentCategory contentCategroy:list){
				//如果是父节点，继续递归
				if(contentCategroy.getIsParent()){
					deleteCategory(contentCategroy.getId());
				}else{
					//不是父节点，将查询出的节点删除掉
					contentCategoryMapper.deleteByPrimaryKey(contentCategroy.getId());
				}
			}
			//最后删除该节点
			contentCategoryMapper.deleteByPrimaryKey(id);
		}else{
			//如果没有parentid为该节点的子节点，直接删除该节点
			contentCategoryMapper.deleteByPrimaryKey(id);
		}
		
	}

}

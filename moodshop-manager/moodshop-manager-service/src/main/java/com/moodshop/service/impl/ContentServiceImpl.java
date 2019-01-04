package com.moodshop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.mapper.TbContentMapper;
import com.moodshop.pojo.TbContent;
import com.moodshop.pojo.TbContentExample;
import com.moodshop.pojo.TbContentExample.Criteria;
import com.moodshop.service.ContentService;

/**
 * 内容业务层实现类
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	private final static Logger logger=Logger.getLogger(ContentServiceImpl.class);
	@Autowired
	private TbContentMapper contentMapper;
	
	/**
	 * 根据分类查询内容列表
	 */
	@Override
	public EasyUIDataGridResult getContentList(int page,int rows,Long categroyId) {
		logger.info("开始查询内容列表......categoryId="+categroyId);
		//分页处理
		PageHelper.startPage(page, rows);
		//新增查询条件
		TbContentExample example=new TbContentExample();
		Criteria criteria=example.createCriteria();
		criteria.andCategoryIdEqualTo(categroyId);
		List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		
		logger.info("共查询到："+pageInfo.getTotal()+"条记录.");
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public MSResult insertContent(TbContent content) {
		logger.info("开始插入内容..........");
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		return MSResult.ok();
	}

	@Override
	public MSResult deleteContent(Long id) {
		contentMapper.deleteByPrimaryKey(id);
		return MSResult.ok();
	}

	@Override
	public MSResult updateContent(TbContent content) {
		logger.info("开始更新内容.....");
		contentMapper.updateByPrimaryKeySelective(content);
		return MSResult.ok();
	}
}

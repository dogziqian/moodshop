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
import com.moodshop.mapper.TbItemParamMapper;
import com.moodshop.pojo.TbItemParam;
import com.moodshop.pojo.TbItemParamExample;
import com.moodshop.pojo.TbItemParamExample.Criteria;
import com.moodshop.service.ItemParamService;

/**
 * 商品参数业务层实现类
 * 
 * @author Administrator
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	private final static Logger logger=Logger.getLogger(ItemParamServiceImpl.class);
	@Autowired
	private TbItemParamMapper itemParamMapper;

	/**
	 * 分页显示结果
	 */
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		logger.info("分页处理开始.......");
		// 分页处理
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 获取分页结果
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		logger.info("共查询到"+pageInfo.getTotal()+"条记录");
		return result;
	}

	/**
	 * 根据分类id获取商品参数
	 */
	@Override
	public MSResult getItemParamByCid(Long cid) {
		logger.info("开始根据分类id查询商品参数......");
		// 根据cid查询规格参数模板
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		// 执行查询
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断是否查询到结果
		if (list != null && list.size() > 0) {
			TbItemParam itemParam = list.get(0);
			logger.info("查询到结果为："+list.get(0));
			return MSResult.ok(itemParam);
		}
		return MSResult.ok();
	}
	/**
	 * 插入商品参数数据
	 */
	@Override
	public MSResult insertItemParam(Long cid, String paramData) {
		logger.info("开始插入商品参数数据......");
		//创建pojo
		TbItemParam itemParam=new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入记录
		itemParamMapper.insert(itemParam);
		return MSResult.ok();
	}
	/**
	 * 删除商品规格参数
	 */
	@Override
	public MSResult deleteItemParam(Long id) {
		logger.info("开始删除商品参数.......");
		itemParamMapper.deleteByPrimaryKey(id);
		return MSResult.ok();
	}

}

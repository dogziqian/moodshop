package com.moodshop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.IDUtils;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.mapper.TbItemDescMapper;
import com.moodshop.mapper.TbItemDiscountMapper;
import com.moodshop.mapper.TbItemMapper;
import com.moodshop.mapper.TbItemParamItemMapper;
import com.moodshop.pojo.TbItem;
import com.moodshop.pojo.TbItemDesc;
import com.moodshop.pojo.TbItemDiscount;
import com.moodshop.pojo.TbItemDiscountExample;
import com.moodshop.pojo.TbItemExample;
import com.moodshop.pojo.TbItemExample.Criteria;
import com.moodshop.pojo.TbItemParamItem;
import com.moodshop.pojo.TbItemParamItemExample;
import com.moodshop.service.ItemService;

/**
 * 商品业务层实现类
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	private final static Logger logger=Logger.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	@Autowired
	private TbItemDiscountMapper itemDiscountMapper;
	/**
	 * 根据商品id获取商品
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		//根据主键查询
		//TbItem item=itemMapper.selectByPrimaryKey(itemId);
		//根据查询条件查询
		logger.info("根据商品id查询商品..........");
		TbItemExample tbItemExample=new TbItemExample();
		Criteria criteria= tbItemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list=itemMapper.selectByExample(tbItemExample);
		//System.out.println("list:"+list);
		TbItem item=null;
		if(list!=null &&list.size()>0){
			item=list.get(0);
			logger.info("查询到的结果为:"+list.get(0));
		}
		return item;
	}
	/**
	 * 获取easyUI分页结果
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		logger.info("分页查询商品开始......");
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list=itemMapper.selectByExample(example);
		//获取分页结果
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		logger.info("共查询到："+pageInfo.getTotal()+"条记录");
		return result;
	}
	/**
	 * 新增商品
	 */
	@Override
	public MSResult createItem(TbItem item, String desc,String itemParam,String disprice) {
		logger.info("新增商品开始......");
		//生成商品ID
		long itemId=IDUtils.genItemId();
		logger.info("生成的商品id为:"+itemId);
		//补全item属性
		item.setId(itemId);
		//商品状态1-正常 2-下架 3-删除
		item.setStatus((byte)1);
		//设置创建时间和更新时间
		Date date=new Date();
		item.setCreated(date);
		item.setUpdated(date);
		//插入商品表
		itemMapper.insert(item);
		logger.info("商品描述处理......");
		//商品描述
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setCreated(date);
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(itemId);
		itemDesc.setUpdated(date);
		//插入商品描述数据
		itemDescMapper.insert(itemDesc);
		logger.info("添加商品参数规格处理......");
		//添加商品参数规格处理
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insertSelective(itemParamItem);
		
		logger.info("商品打折参数处理......");
		TbItemDiscount itemDiscount=new TbItemDiscount();
		itemDiscount.setItemId(itemId);
		itemDiscount.setDisPrice(disprice);
		itemDiscount.setCreated(new Date());
		itemDiscount.setUpdated(new Date());
		itemDiscountMapper.insertSelective(itemDiscount);
		
		return MSResult.ok();
	}
	/**
	 * 根据商品id获取商品参数列表html片段
	 */
	@Override
	public String getItemParamHtml(Long itemId) {
		logger.info("查询参数列表");
		TbItemParamItemExample example=new TbItemParamItemExample();
		com.moodshop.pojo.TbItemParamItemExample.Criteria criteria=example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		logger.info("执行查询");
		List<TbItemParamItem> list=itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list==null||list.isEmpty()){
			return "";
		}
		logger.info("取规格参数......");
		TbItemParamItem itemParamItem=list.get(0);
		//json对象
		String paramDataJson=itemParamItem.getParamData();
		logger.info("取出的规格参数为：-----"+paramDataJson);
		//转换成java对象
		List<Map> paramData=JsonUtils.jsonToList(paramDataJson, Map.class);
		logger.info("遍历list生成html");
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("	<tbody>\n");
		for (Map map : paramData) {
			sb.append("		<tr>\n");
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
			sb.append("		</tr>\n");
			//取规格项
			List<Map>mapList2 = (List<Map>) map.get("params");
			for (Map map2 : mapList2) {
				sb.append("		<tr>\n");
				sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
				sb.append("			<td>"+map2.get("v")+"</td>\n");
				sb.append("		</tr>\n");
			}
		}
		sb.append("	</tbody>\n");
		sb.append("</table>");
		
		return sb.toString();
	}
	/**
	 * 修改商品状态
	 */
	@Override
	public MSResult updateItemStatus(Long itemId, Byte status) {
		logger.info("根据id查询商品......");
		TbItem item=itemMapper.selectByPrimaryKey(itemId);
		item.setStatus(status);
		logger.info("更新商品状态....");
		itemMapper.updateByPrimaryKeySelective(item);
		logger.info("更新完成后的商品状态为:"+item.getStatus());
		return MSResult.ok();
	}
	/**
	 * 修改商品
	 */
	@Override
	public MSResult updateItem(Long itemId,TbItem item, String desc, String itemParam) {
		//补全item属性
		item.setId(itemId);
		//设置创建时间和更新时间
		Date date=new Date();
		item.setUpdated(date);
		//插入商品表
		itemMapper.updateByPrimaryKeySelective(item);
		logger.info("商品描述处理......");
		//商品描述
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(itemId);
		itemDesc.setUpdated(date);
		//插入商品描述数据
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		logger.info("添加商品参数规格处理......");
		//添加商品参数规格处理
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
		
		return MSResult.ok();
	}
	/**
	 * 根据商品id获取商品描述
	 */
	@Override
	public MSResult getItemDesc(Long itemId) {
		TbItemDesc desc=itemDescMapper.selectByPrimaryKey(itemId);
		logger.info("查询结果为："+desc);
		return MSResult.ok(desc);
	}
	@Override
	public MSResult getItemParam(Long itemId) {
		logger.info("查询参数列表");
		TbItemParamItemExample example=new TbItemParamItemExample();
		com.moodshop.pojo.TbItemParamItemExample.Criteria criteria=example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		logger.info("执行查询");
		List<TbItemParamItem> list=itemParamItemMapper.selectByExampleWithBLOBs(example);
		if(list==null||list.isEmpty()){
			return MSResult.ok("");
		}
		logger.info("取规格参数......");
		TbItemParamItem itemParamItem=list.get(0);

		return MSResult.ok(itemParamItem);
	}
	@Override
	public MSResult getItemDiscount(Long itemId) {
		logger.info("查询商品打折参数....");
		TbItemDiscountExample example=new TbItemDiscountExample();
		com.moodshop.pojo.TbItemDiscountExample.Criteria criteria=example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		logger.info("执行查询");
		List<TbItemDiscount> list=itemDiscountMapper.selectByExample(example);
		if(list==null&&list.isEmpty()){
			return MSResult.ok("");
		}
		TbItemDiscount discount=list.get(0);
		String disJson=discount.getDisPrice();
		logger.info("取出的参数为：-----"+disJson);
		//转换成java对象
//		List<Map> disData=JsonUtils.jsonToList(disJson, Map.class);
//		for(Map map:disData){
//			
//		}
		return MSResult.ok(disJson);
	}	
}

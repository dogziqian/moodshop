package com.moodshop.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.mapper.TbItemCatMapper;
import com.moodshop.pojo.TbContent;
import com.moodshop.pojo.TbItemCat;
import com.moodshop.pojo.TbItemCatExample;
import com.moodshop.pojo.TbItemCatExample.Criteria;
import com.moodshop.rest.component.JedisClient;
import com.moodshop.rest.pojo.CatNode;
import com.moodshop.rest.pojo.ItemCatResult;
import com.moodshop.rest.service.ItemCatService;

/**
 * 商品分类业务实现层
 * 
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	private final static Logger logger = Logger.getLogger(ItemCatServiceImpl.class);
	@Autowired
	private TbItemCatMapper itemCatMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEMCAT_KEY}")
	private String REDIS_ITEMCAT_KEY;

	/**
	 * 获取分类列表
	 */
	@Override
	public ItemCatResult getItemCatList() {
		ItemCatResult result = new ItemCatResult();
		// 调用递归方法查询商品分类列表
		List catList = getItemCatList(0l);
		// 返回结果
		result.setData(catList);
		return result;
	}

	private List getItemCatList(long parentId) {
		List<TbItemCat> list=new ArrayList<>();
		// 如果有就从缓存中直接返回
		try {
			logger.info("从redis中取缓存内容....");
			String json = jedisClient.hget(REDIS_ITEMCAT_KEY, parentId + "");
			logger.info("从redis中取到的数据为：" + json);
			if (!StringUtils.isBlank(json)) {
				logger.info("将json转换成list");
				list= JsonUtils.jsonToList(json, TbItemCat.class);
			}
		} catch (Exception e) {
			logger.info("从缓存中查询数据异常！" );
			e.getStackTrace();
		}

		// 根据parentId查询列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		list = itemCatMapper.selectByExample(example);
		try {
			logger.info("向缓存中添加数据.....");
			jedisClient.hset(REDIS_ITEMCAT_KEY, parentId+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			logger.info("向缓存中添加数据失败...");
			e.getStackTrace();
		}
		List resultList = new ArrayList<>();
		int index = 0;
		for (TbItemCat tbItemCat : list) {
			if (index >= 14) {
				break;
			}
			// 如果是父节点
			if (tbItemCat.getIsParent()) {
				CatNode node = new CatNode();
				node.setUrl("/products/" + tbItemCat.getId() + ".html");
				// 如果当前节点为第一级节点
				if (tbItemCat.getParentId() == 0) {
					node.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
					// 第一级节点不能超过14个，index为计数器
					index++;
				} else {
					node.setName(tbItemCat.getName());
				}
				node.setItems(getItemCatList(tbItemCat.getId()));
				// 把node添加到列表
				resultList.add(node);
			} else {
				// 如果是叶子节点
				String item = "/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				resultList.add(item);
			}
		}
		
		return resultList;
	}
	/**
	 * 删除缓存内容
	 */
	@Override
	public MSResult syncItemCat(long parentId) {
		jedisClient.hdel(REDIS_ITEMCAT_KEY, parentId+"");
		return MSResult.ok();
	}

}

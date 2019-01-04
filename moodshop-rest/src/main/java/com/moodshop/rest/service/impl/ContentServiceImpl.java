package com.moodshop.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.mapper.TbContentMapper;
import com.moodshop.pojo.TbContent;
import com.moodshop.pojo.TbContentExample;
import com.moodshop.pojo.TbContentExample.Criteria;
import com.moodshop.rest.component.JedisClient;
import com.moodshop.rest.service.ContentService;

/**
 * 内容管理业务层实现类
 * 
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	private final static Logger logger=Logger.getLogger(ContentServiceImpl.class);
	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;
	
	/**
	 * 获取内容列表
	 */
	@Override
	public List<TbContent> getContentList(Long cid) {
		//添加缓存
		//如果有就从缓存中直接返回
		try {
			logger.info("从redis中取缓存内容....");
			String json=jedisClient.hget(REDIS_CONTENT_KEY, cid+"");
			logger.info("从redis中取到的数据为："+json);
			if(!StringUtils.isBlank(json)){
				logger.info("将json转换成list");
				List<TbContent> list=JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			logger.info("从缓存中查询数据异常！");
			e.getStackTrace();
		}
		logger.info("根据cid查询内容列表......");
		// 根据cid查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		
		//查询完成之后再添加到缓存中
		try {
			logger.info("向缓存中添加数据.....");
			jedisClient.hset(REDIS_CONTENT_KEY, cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			logger.info("向缓存中添加数据失败...");
			e.getStackTrace();
		}
		return list;
	}
	/**
	 * 删除缓存中信息
	 */
	@Override
	public MSResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		return MSResult.ok();
	}

}

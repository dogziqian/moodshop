package com.moodshop.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbContent;
import com.moodshop.portal.pojo.AdNode;
import com.moodshop.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;
	@Value("${REST_CONTENT_AD1_CID}")
	private String REST_CONTENT_AD1_CID;
	/**
	 * 获取大广告位列表
	 */
	@Override
	public String getAd1List() {
		//调用服务获得数据
		String json=HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+REST_CONTENT_AD1_CID);
		//将json转换成java对象
		MSResult msResult=MSResult.formatToList(json, TbContent.class);
		//取data属性，内容列表
		List<TbContent> contentList=(List<TbContent>) msResult.getData();
		//把内容列表转换成adNode列表
		List<AdNode> resultList=new ArrayList<>();
		for (TbContent tbContent : contentList) {
			AdNode node=new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(tbContent.getPic());
			
			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());
			
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			
			resultList.add(node);
		}
		String resultJson=JsonUtils.objectToJson(resultList);
		return resultJson;
	}
	@Override
	public String getRight() {
		// TODO Auto-generated method stub
		return null;
	}

}

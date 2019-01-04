package com.moodshop.portal.service;

import com.moodshop.comm.pojo.MSResult;
/**
 * 生成静态文件
 * @author Administrator
 *
 */
public interface StaticPageService {
	MSResult genItemHtml(Long itemId) throws Exception;
}

package com.moodshop.portal.service;

import com.moodshop.portal.pojo.SearchResult;
/**
 * 查询service接口
 * @author Administrator
 *
 */
public interface SearchService {
	SearchResult search(String keyword, int page, int rows);
}

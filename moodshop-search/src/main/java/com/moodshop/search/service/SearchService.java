package com.moodshop.search.service;

import com.moodshop.search.pojo.SearchResult;

public interface SearchService {
	//分页获取查询结果
	SearchResult search(String queryString, int page, int rows) throws Exception;
}

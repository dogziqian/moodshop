package com.moodshop.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.moodshop.search.pojo.SearchResult;

public interface SearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}

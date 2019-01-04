package com.moodshop.search.service;

import com.moodshop.comm.pojo.MSResult;

public interface ItemService {
	//导入商品索引
	MSResult importItems()throws Exception;
}

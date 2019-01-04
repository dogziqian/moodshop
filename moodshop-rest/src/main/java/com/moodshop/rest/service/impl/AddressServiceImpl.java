package com.moodshop.rest.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.mapper.TbCityMapper;
import com.moodshop.mapper.TbProvenceMapper;
import com.moodshop.pojo.TbCity;
import com.moodshop.pojo.TbCityExample;
import com.moodshop.pojo.TbCityExample.Criteria;
import com.moodshop.pojo.TbProvence;
import com.moodshop.pojo.TbProvenceExample;
import com.moodshop.rest.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private final static Logger logger = Logger.getLogger(AddressServiceImpl.class);

	@Autowired
	TbProvenceMapper provenceMapper;
	@Autowired
	TbCityMapper cityMapper;

	@Override
	public MSResult getProvence() {
		TbProvenceExample example = new TbProvenceExample();
		List<TbProvence> list = provenceMapper.selectByExample(example);
		logger.info("查询到的结果为："+list);
		return MSResult.ok(list);
	}

	@Override
	public MSResult getCityByProvence(Integer pid) {
		TbCityExample example=new TbCityExample();
		Criteria criteria=example.createCriteria();
		criteria.andProvenceIdEqualTo(pid);
		List<TbCity> list=cityMapper.selectByExample(example);
		logger.info("查询到的结果为：");
		return MSResult.ok(list);
	}

}

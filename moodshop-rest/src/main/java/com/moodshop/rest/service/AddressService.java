package com.moodshop.rest.service;

import com.moodshop.comm.pojo.MSResult;

public interface AddressService {
	/**
	 * 获取省份信息
	 * @return
	 */
	MSResult getProvence();
	/**
	 * 根据省份获取城市信息
	 * @param pid
	 * @return
	 */
	MSResult getCityByProvence(Integer pid);
}

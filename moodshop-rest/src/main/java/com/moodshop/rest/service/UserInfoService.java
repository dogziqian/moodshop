package com.moodshop.rest.service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbUserdetail;

public interface UserInfoService {
	/**
	 * 获取用户具体信息
	 * @param userId
	 * @return
	 */
	MSResult getUserDetail(Long userId);
	/**
	 * 更新用户信息
	 * @param userId
	 * @param userDetail
	 * @return
	 */
	MSResult updateUserDetail(TbUserdetail userDetail);
	/**
	 * 
	 * @param userDetail
	 * @return
	 */
	MSResult addUserDetail(TbUserdetail userDetail);
}

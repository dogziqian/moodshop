package com.moodshop.portal.service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbUserdetail;

/**
 * 用户信息接口
 * @author Administrator
 *
 */
public interface UserInfoService {
	/**
	 * 获取用户信息
	 * @return
	 */
	TbUserdetail getUserInfo(Long userid);
	/**
	 * 更新个人信息
	 * @return
	 */
	MSResult updateUserInfo(Long userid,TbUserdetail userDetail);
	/**
	 * 获取省份信息
	 * @return
	 */
	MSResult getProvence();
	/**
	 * 获取城市信息
	 * @param pid
	 * @return
	 */
	MSResult getCity(Integer pid);
}

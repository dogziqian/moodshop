package com.moodshop.sso.service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbUser;
/**
 * 用户注册接口
 * @author Administrator
 *
 */
public interface RegisterService {
	//数据校验
	MSResult checkData(String param, int type);
	//用户注册
	MSResult register(TbUser user);
}

package com.moodshop.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.mapper.TbUserMapper;
import com.moodshop.pojo.TbUser;
import com.moodshop.pojo.TbUserExample;
import com.moodshop.pojo.TbUserExample.Criteria;
import com.moodshop.sso.service.RegisterService;

/**
 * 注册实现类
 * 
 * @author Administrator
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public MSResult checkData(String param, int type) {
		// 根据数据类型检查数据
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 1、2、3分别代表username、phone、email
		if (1 == type) {
			criteria.andUsernameEqualTo(param);
		} else if (2 == type) {
			criteria.andPhoneEqualTo(param);
		} else if (3 == type) {
			criteria.andEmailEqualTo(param);
		}
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		// 判断查询结果是否为空
		if (list == null || list.isEmpty()) {
			return MSResult.ok(true);
		}
		return MSResult.ok(false);
	}

	@Override
	public MSResult register(TbUser user) {
		// 校验数据
		// 校验用户名、密码不能为空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
			return MSResult.build(400, "用户名或密码不能为空");
		}
		// 校验数据是否重复
		// 校验用户名
		MSResult result = checkData(user.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return MSResult.build(400, "用户名重复");
		}
		// 校验手机号
		if (user.getPhone() != null) {
			result = checkData(user.getPhone(), 2);
			if (!(boolean) result.getData()) {
				return MSResult.build(400, "手机号重复");
			}
		}
		// 校验手机号
		if (user.getEmail() != null) {
			result = checkData(user.getEmail(), 3);
			if (!(boolean) result.getData()) {
				return MSResult.build(400, "邮箱重复");
			}
		}
		// 插入数据
		user.setCreated(new Date());
		user.setUpdated(new Date());
		userMapper.insert(user);

		return MSResult.ok();
	}

}

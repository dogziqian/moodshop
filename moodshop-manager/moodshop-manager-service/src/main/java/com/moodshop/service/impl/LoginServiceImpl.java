package com.moodshop.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.mapper.TbAdminMapper;
import com.moodshop.pojo.TbAdmin;
import com.moodshop.pojo.TbAdminExample;
import com.moodshop.pojo.TbAdminExample.Criteria;
import com.moodshop.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	
	private final static  Logger logger=Logger.getLogger(LoginServiceImpl.class);
	@Autowired
	private TbAdminMapper adminMapper;
	
	@Override
	public MSResult login(String username, String password,HttpServletRequest request, HttpServletResponse response) {
		
		TbAdminExample example=new TbAdminExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(username);
		logger.info("开始根据用户名查询用户....."+username);
		List<TbAdmin> list=adminMapper.selectByExample(example);
		if(list.size()>0&&list!=null){
			TbAdmin admin=list.get(0);
			logger.info("获取到的用户为："+admin);
			if(admin.getStatus()==1){
				logger.info("用户状态正常...");
				if(password.equals(admin.getPassword())){
					logger.info("密码正确.");
					CookieUtils.setCookie(request, response, "admin", admin.getUsername());
					CookieUtils.setCookie(request, response, "rank", Integer.toString(admin.getRank()));
					return MSResult.ok("登录成功");
				}
				return MSResult.ok("密码错误");
			}
			return MSResult.ok("用户处于锁定状态,请联系超级管理员!");
		}
		return MSResult.ok("用户名不存在");
	}

}

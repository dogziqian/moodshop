package com.moodshop.sso.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.mapper.TbUserMapper;
import com.moodshop.pojo.TbUser;
import com.moodshop.pojo.TbUserExample;
import com.moodshop.pojo.TbUserExample.Criteria;
import com.moodshop.sso.component.JedisClient;
import com.moodshop.sso.service.LoginService;

/**
 * 用户登录实现类
 * 
 * @author Administrator
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	private final static Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public MSResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		// 校验用户名密码是否正确
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		logger.info("开始获取用户信息.......");
		List<TbUser> list = userMapper.selectByExample(example);
		// 取用户信息
		if (list == null || list.isEmpty()) {
			return MSResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		// 校验密码
		if (!user.getPassword().equals(password)) {
			return MSResult.build(400, "用户名或密码错误");
		}
		logger.info("登陆成功.......");
		// 登录成功
		// 生成token
		String token = UUID.randomUUID().toString();
		// 把用户信息写入redis
		// key:REDIS_SESSION:{TOKEN}
		// value:user转json
		// 将密码清空，为了安全
		user.setPassword(null);
		logger.info("开始写入缓存......");
		jedisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		// 设置session的过期时间
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);
		// 写cookie，浏览器关闭，cookie失效
		CookieUtils.setCookie(request, response, "MS_TOKEN", token);
		
		return MSResult.ok(token);
	}

	@Override
	public MSResult getUserByToken(String token) {
		// 根据token取用户信息
		String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
		logger.info("从token中获取到的结果为:"+json);
		// 判断是否查询到结果
		if (StringUtils.isBlank(json)) {
			logger.info("用户session过期......");
			return MSResult.build(400, "用户session已经过期");
		}
		// 把json转换成java对象
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		// 更新session的过期时间
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE);

		return MSResult.ok(user);
	}

	@Override
	public MSResult logout(String token,HttpServletRequest request, HttpServletResponse response) {
		//从redis中查询token，如果存在就删除
		String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
		logger.info("从token中获取到的结果为:"+json);
		if (StringUtils.isBlank(json)) {
			logger.info("没有对应token信息");
			return MSResult.ok();
		}
		//删除token中信息
		jedisClient.del(REDIS_SESSION_KEY + ":" + token);
		//删除cookeie中信息
		CookieUtils.deleteCookie(request, response, "MS_TOKEN");
		return MSResult.ok();
	}

}

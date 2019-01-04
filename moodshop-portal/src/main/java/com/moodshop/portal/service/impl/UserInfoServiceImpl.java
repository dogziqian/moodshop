package com.moodshop.portal.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbCity;
import com.moodshop.pojo.TbProvence;
import com.moodshop.pojo.TbUserdetail;
import com.moodshop.portal.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${USER_INFO_Query_URL}")
	private String USER_INFO_Query_URL;
	@Value("${QUERY_PROVENCE_URL}")
	private String QUERY_PROVENCE_URL;
	@Value("${QUERY_CITY_URL}")
	private String QUERY_CITY_URL;
	@Value("${USER_INFO_ADD_URL}")
	private String USER_INFO_ADD_URL;
	@Value("${USER_INFO_UPDATE_URL}")
	private String USER_INFO_UPDATE_URL;

	private final static Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

	/**
	 * 获取用户信息
	 */
	@Override
	public TbUserdetail getUserInfo(Long userid) {
		logger.info("请求的URL为：" + REST_BASE_URL + USER_INFO_Query_URL + userid);
		// 根据商品id查询基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + USER_INFO_Query_URL + userid);
		logger.info("获取到的结果为：" + json);
		// 转换成java对象
		MSResult msResult = MSResult.formatToPojo(json, TbUserdetail.class);
		// 取商品对象
		TbUserdetail userDetail = (TbUserdetail) msResult.getData();
		logger.info("返回结果为：" + userDetail);
		return userDetail;
	}

	@Override
	public MSResult updateUserInfo(Long userid, TbUserdetail userDetail) {
		
		logger.info("获取用户信息...");
		TbUserdetail user=getUserInfo(userid);
		if(user==null){
			logger.info("用户具体信息添加........");
			userDetail.setUserid(userid);
			String reqJson = JsonUtils.objectToJson(userDetail);
			logger.info("请求json为：" + reqJson);
			// 根据商品id查询基本信息
			String json = HttpClientUtil.doPostJson(REST_BASE_URL + USER_INFO_ADD_URL, reqJson);
			logger.info("获取到的结果为：" + json);
		}else{
			logger.info("用户信息修改.....");
			userDetail.setUserid(userid);
			String reqJson = JsonUtils.objectToJson(userDetail);
			logger.info("请求json为：" + reqJson);
			// 根据商品id查询基本信息
			String json = HttpClientUtil.doPostJson(REST_BASE_URL + USER_INFO_UPDATE_URL, reqJson);
			logger.info("获取到的结果为：" + json);
		}
		
		return MSResult.ok();
	}

	@Override
	public MSResult getProvence() {
		logger.info("请求的URL为：" + REST_BASE_URL + QUERY_PROVENCE_URL);
		// 根据商品id查询基本信息
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		// 创建uri
		URIBuilder builder;
		try {
			builder = new URIBuilder(REST_BASE_URL + QUERY_PROVENCE_URL);
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = httpclient.execute(httpGet);
			logger.info("返回状态：" + response.getStatusLine());
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//String json = HttpClientUtil.doGet(REST_BASE_URL + USER_INFO_Query_URL);
		logger.info("获取到的结果为：" + resultString);
		// 转换成java对象
		//MSResult msResult = MSResult.formatToList(resultString, TbProvence.class);
		// 取商品对象
		//List<TbProvence> list = (List<TbProvence>) msResult.getData();
		//logger.info("返回结果为：" + list);
		return MSResult.ok(resultString);
	}

	@Override
	public MSResult getCity(Integer pid) {
		logger.info("请求的URL为：" + REST_BASE_URL + QUERY_CITY_URL + pid);
		// 根据商品id查询基本信息
		String json = HttpClientUtil.doGet(REST_BASE_URL + QUERY_CITY_URL + pid);
		logger.info("获取到的结果为：" + json);
		// 转换成java对象
		//MSResult msResult = MSResult.formatToPojo(json, TbCity.class);
		// 取商品对象
		//List<TbCity> list = (List<TbCity>) msResult.getData();
		//logger.info("返回结果为：" + list);
		return MSResult.ok(json);
	}

}

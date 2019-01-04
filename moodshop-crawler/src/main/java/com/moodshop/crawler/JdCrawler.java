package com.moodshop.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.moodshop.pojo.Item;

public class JdCrawler {
	private final static String BASE_URL = "https://list.jd.com/list.html?cat=9855,9856,9898&page={page}";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public void start() {
		// 入口页面
		String startUrl = StringUtils.replace(BASE_URL, "{page}", "1");

		try {
			// 获取到总页数
			String html = doGet(startUrl);
			// 解析html
			Document document = Jsoup.parse(html);
			String pageText = document.select("#J_topPage").text();
			// System.out.println(pageText);
			String[] strs = pageText.split("\\D+");
			Integer total = Integer.parseInt(strs[1]);

			// 分页查询数据
			for (int i = 1; i <= total; i++) {
				String url = StringUtils.replace(BASE_URL, "{page}", String.valueOf(i));
				String content = doGet(url);//商品数据
				//解析html
				Document root = Jsoup.parse(content);
				//商品列表
				Elements lis= root.select("#plist li.gl-item");
				Map<Long,Item> items=new HashMap<Long,Item>();
				for (Element li : lis) {
					Item item=new Item();
					Element div=li.child(0);
					//商品id
					Long id=Long.valueOf(div.attr("data-sku"));
					//商品图图片
					String imageSrc=li.select(".p-img img").attr("src");
					//商品标题
					String title=li.select(".p-name").text();
					item.setId(id);
					item.setImage(imageSrc);
					item.setTitle(title);
					//System.out.println(title);
					items.put(id, item);
				}
				//获取商品价格
				List<String> ids=new ArrayList<String>();
				for (Long itemId : items.keySet()) {
					ids.add("J_"+itemId);
				}
				String priceUrl="https://p.3.cn/prices/mgets?skuIds="+StringUtils.join(ids, ',');
				String jsonData=doGet(priceUrl);
				//System.out.println(jsonData);
				//解析json
				ArrayNode arraynode=(ArrayNode) MAPPER.readTree(jsonData);
				for (JsonNode jsonNode : arraynode) {
					Long id=Long.valueOf(StringUtils.substringAfter(jsonNode.get("id").asText(), "_"));
					items.get(id).setPrice(jsonNode.get("p").asLong());
				}
				//获取卖点
				ids.clear();
				for (Long itemId : items.keySet()) {
					ids.add("AD_"+itemId);
				}
				String sell_pointUrl="https://ad.3.cn/ads/mgets?skuids="+StringUtils.join(ids, ',');
				jsonData=doGet(sell_pointUrl);
				arraynode=(ArrayNode) MAPPER.readTree(jsonData);
				//System.out.println(jsonData);
				for (JsonNode jsonNode : arraynode) {
					Long id=Long.valueOf(StringUtils.substringAfter(jsonNode.get("id").asText(), "_"));
					items.get(id).setSellPoint(jsonNode.get("ad").asText());
				}
				for(Item item:items.values()){
					System.out.println(item);
				}
				//TODO
				break;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String doGet(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 创建http GET请求
		HttpGet httpGet = new HttpGet(url);

		CloseableHttpResponse response = null;
		try {
			// 执行请求
			response = httpClient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				String content = EntityUtils.toString(response.getEntity(), "UTF-8");
				return content;
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return "";
	}
}

package com.moodshop.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.comm.utils.HttpClientUtil;
import com.moodshop.comm.utils.JsonUtils;
import com.moodshop.pojo.TbCart;
import com.moodshop.pojo.TbItem;
import com.moodshop.portal.pojo.CartItem;
import com.moodshop.portal.service.CartService;
import com.moodshop.portal.service.ItemService;

@Service
public class CartServiceImpl implements CartService {

	private final static Logger logger = Logger.getLogger(CartServiceImpl.class);
	@Autowired
	private ItemService itemService;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${CART_ADD_API_URL}")
	private String CART_ADD_API_URL;
	@Value("${CART_QUERY_API_URL}")
	private String CART_QUERY_API_URL;
	@Value("${CART_UPD_API_URL}")
	private String CART_UPD_API_URL;
	@Value("${CART_DEL_API_URL}")
	private String CART_DEL_API_URL;

	/**
	 * 添加购物车
	 */
	@Override
	public MSResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 3、从商品列表中查询列表是否存在此商品
		boolean haveFlg = false;
		for (CartItem cartItem : itemList) {
			// 如果商品存在数量相加
			// 4、如果存在商品的数量加上参数中的商品数量
			logger.info("购物车存在该商品,增加商品数量.....");
			if (cartItem.getId().longValue() == itemId) {
				logger.info("商品数量为：" + cartItem.getNum());
				cartItem.setNum(cartItem.getNum() + num);
				haveFlg = true;
				break;
			}
		}
		// 5、如果不存在，调用rest服务，根据商品id获得商品数据。
		if (!haveFlg) {
			logger.info("购物车不存在该商品......");
			TbItem item = itemService.getItemById(itemId);
			logger.info("获取的商品数据为：" + item);
			// 转换成CartItem
			CartItem cartItem = new CartItem();
			cartItem.setId(itemId);
			cartItem.setNum(num);
			cartItem.setPrice(item.getPrice());
			cartItem.setTitle(item.getTitle());

			if (StringUtils.isNotBlank(item.getImage())) {
				String image = item.getImage();
				String[] strings = image.split(",");
				cartItem.setImage(strings[0]);
			}
			logger.info("转换成的cartItem为：" + cartItem);
			// 添加到购物车商品列表
			// 6、把商品数据添加到列表中
			itemList.add(cartItem);
		}
		// 7、把购物车商品列表写入cookie
		logger.info("将商品写入cookie......");
		CookieUtils.setCookie(request, response, "MS_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		// 8、返回MSResult
		return MSResult.ok();
	}

	/**
	 * 取购物车列表
	 * 
	 * @param request
	 * @return
	 */
	private List<CartItem> getCartItemList(HttpServletRequest request) {
		try {
			// 从cookie中取商品列表
			String json = CookieUtils.getCookieValue(request, "MS_CART", true);
			logger.info("从cookie中获取到的结果为：" + json);
			// 把json转换成java对象
			List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);
			// logger.info("获取到的list为：" + list);
			return list == null ? new ArrayList<CartItem>() : list;

		} catch (Exception e) {
			return new ArrayList<CartItem>();
		}
	}

	/**
	 * 取购物项
	 */
	@Override
	public List<CartItem> getCartItems(HttpServletRequest request) {
		List<CartItem> list = getCartItemList(request);
		return list;
	}

	/**
	 * 更新商品数量
	 */
	@Override
	public MSResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
		logger.info("从cookie中取购物车商品列表.....");
		// 从cookie中取购物车商品列表
		List<CartItem> itemList = getCartItemList(request);
		// 根据商品id查询商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId().longValue() == itemId) {
				logger.info("查询到的商品id为：" + cartItem.getId());
				// 更新数量
				cartItem.setNum(num);
				break;
			}
		}
		logger.info("开始写入cookie......");
		// 写入cookie
		CookieUtils.setCookie(request, response, "MS_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		return MSResult.ok();
	}

	/**
	 * 删除购物车商品
	 */
	@Override
	public MSResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		// 1、接收商品id
		// 2、从cookie中取购物车商品列表
		logger.info("从cookie中获取商品列表......");
		List<CartItem> itemList = getCartItemList(request);
		logger.info("获取到的商品列表为:" + itemList);
		// 3、找到对应id的商品
		for (CartItem cartItem : itemList) {
			if (cartItem.getId().longValue() == itemId) {
				// 4、删除商品。
				logger.info("获取到的商品id：" + cartItem.getId());
				itemList.remove(cartItem);
				break;
			}
		}
		logger.info("重新写入cookie......");
		// 5、再重新把商品列表写入cookie。
		CookieUtils.setCookie(request, response, "MS_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
		// 6、返回成功
		return MSResult.ok();
	}

	/**
	 * 持久化保存购物车商品
	 */
	@Override
	public MSResult addEnCart(Long itemId, Integer num, Long userId, HttpServletRequest request) {
		// logger.info("判断cookie中是否有值....");
		MSResult msResult = new MSResult();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userid", userId);
			params.put("itemid", itemId);
			TbItem item = itemService.getItemById(itemId);
			params.put("itemprice", item.getPrice());
			params.put("itemtitle", item.getTitle());
			params.put("itemimage", item.getImage());
			params.put("num", num);
			params.put("created", new Date());
			params.put("updated", new Date());

			String reqjson = JsonUtils.objectToJson(params);
			logger.info("请求的json为：" + reqjson);
			logger.info("请求的url为：" + REST_BASE_URL + CART_ADD_API_URL);
			String rspjson = HttpClientUtil.doPostJson(REST_BASE_URL + CART_ADD_API_URL, reqjson);
			logger.info("返回的结果为：" + rspjson);
			msResult = MSResult.formatToPojo(reqjson, MSResult.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msResult;
	}

	@Override
	public List<CartItem> getCartEnItems(Long userId) {
		logger.info("请求的url为：" + REST_BASE_URL + CART_QUERY_API_URL + userId);
		String rspjson = HttpClientUtil.doGet(REST_BASE_URL + CART_QUERY_API_URL + userId);
		logger.info("返回报文为：" + rspjson);
		logger.info("getData()=" + MSResult.formatToList(rspjson, TbCart.class).getData());
		List<TbCart> cartList = (List<TbCart>) MSResult.formatToList(rspjson, TbCart.class).getData();
		List<CartItem> list = new ArrayList<CartItem>();
		if (cartList.size() > 0 && cartList != null) {
			for (TbCart tbCart : cartList) {
				CartItem cartItem = new CartItem();
				cartItem.setId(tbCart.getItemid());
				if (StringUtils.isNotBlank(tbCart.getItemimage())) {
					String image = tbCart.getItemimage();
					String[] strings = image.split(",");
					cartItem.setImage(strings[0]);
				}
				cartItem.setNum(tbCart.getNum());
				// if(tbCart.getNum()>=50){
				// cartItem.setPrice((long) (tbCart.getItemprice()*0.95));
				// }else if(tbCart.getNum()>=100){
				// cartItem.setPrice((long) (tbCart.getItemprice()*0.9));
				// }else if(tbCart.getNum()>=300){
				// cartItem.setPrice((long) (tbCart.getItemprice()*0.85));
				// }else if(tbCart.getNum()>=500){
				// cartItem.setPrice((long) (tbCart.getItemprice()*0.8));
				// }else{
				// cartItem.setPrice(tbCart.getItemprice());
				// }
				if (tbCart.getNum() > 10) {
					Long disPrice = Long.valueOf(itemService.getItemDiscountById(tbCart.getItemid(), tbCart.getNum()));
					cartItem.setPrice(disPrice);
				}else{
					cartItem.setPrice(tbCart.getItemprice());
				}
				cartItem.setTitle(tbCart.getItemtitle());
				list.add(cartItem);
			}
		}
		return list;
	}

	@Override
	public MSResult updateEnCartItem(Long itemId, Integer num, Long userId) {
		logger.info("请求的url为：" + REST_BASE_URL + CART_UPD_API_URL + itemId + "/" + num + "/" + userId);
		String rspjson = HttpClientUtil
				.doGet(REST_BASE_URL + CART_UPD_API_URL + "/" + itemId + "/" + num + "/" + userId);
		logger.info("返回报文为：" + rspjson);
		MSResult result = MSResult.formatToPojo(rspjson, MSResult.class);
		return result;
	}

	@Override
	public MSResult deleteEnCartItem(Long itemId, Long userId) {
		logger.info("请求的url为：" + REST_BASE_URL + CART_DEL_API_URL + itemId + "/" + userId);
		String rspjson = HttpClientUtil.doGet(REST_BASE_URL + CART_DEL_API_URL + itemId + "/" + userId);
		logger.info("返回报文为：" + rspjson);
		MSResult result = MSResult.formatToPojo(rspjson, MSResult.class);
		return result;
	}

}

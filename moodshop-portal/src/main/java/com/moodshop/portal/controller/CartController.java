package com.moodshop.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moodshop.comm.pojo.MSResult;
import com.moodshop.comm.utils.CookieUtils;
import com.moodshop.pojo.TbUser;
import com.moodshop.portal.pojo.CartItem;
import com.moodshop.portal.service.CartRedisService;
import com.moodshop.portal.service.CartService;
import com.moodshop.portal.service.UserService;

/**
 * 购物车控制层
 * 
 * @author Administrator
 *
 */
@Controller
public class CartController {

	private Logger logger = Logger.getLogger(CartController.class);
	@Autowired
	private CartService cartService;
	@Autowired
	private CartRedisService cartRedisService;
	@Autowired
	private UserService userService;
	@Value("${COOKIE_EXPIRE}")
	private Integer COOKIE_EXPIRE;
	 public static final String CART_COOKIE_KEY ="MS_CART";

	/**
	 * 添加购物车
	 * 
	 * @param itemId
	 * @param num
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}")
	public String addCart(@PathVariable Long itemId, Integer num, HttpServletResponse response,
			HttpServletRequest request) {
		logger.info("开始添加购物车.....");
		logger.info("前端传递参数：itemId=" + itemId + ",num=" + num);
		// 取用户信息
		TbUser user = userService.getUserByToken(request, response);
		MSResult result=new MSResult();
		if(user==null){
			//未登录
			result = cartService.addCart(itemId, num, request, response);
		}else{
			//已登录
			result =cartService.addEnCart(itemId,num,user.getId(),request);
		}
		
		return "cartSuccess";
	}

	/**
	 * 显示购物车列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String showCartList(HttpServletRequest request,HttpServletResponse response, Model model) {
		logger.info("开始查询购物车列表......");
		TbUser user = userService.getUserByToken(request, response);
		List<CartItem> list=new ArrayList<CartItem>();
		
		if(user==null){
			logger.info("用户未登录，从cookie中取值....");
			list=cartService.getCartItems(request);
		}else{
			logger.info("用户已登录，从数据库中取值......");
			list = cartService.getCartEnItems(user.getId());
		}
		logger.info("购物车中商品："+list.get(0).getTitle());
		// 把商品列表传递给jsp
		model.addAttribute("cartList", list);
		return "cart";
	}

	/**
	 * 修改购物车商品数量
	 * 
	 * @param itemId
	 * @param num
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	public String updateCartItemNum(@PathVariable Long itemId, @PathVariable Integer num,
			HttpServletResponse response, HttpServletRequest request) {
		logger.info("开始修改购物车商品数量.....");
		logger.info("前端传递参数：itemId=" + itemId + ",num=" + num);
		TbUser user = userService.getUserByToken(request, response);
		MSResult result=new MSResult();
		if(user==null){
			logger.info("用户未登录，更新cookie中的数量....");
			result=cartService.updateCartItem(itemId, num, request, response);
		}else{
			logger.info("用户已经登录，更新数据库中数量.....");
			result= cartService.updateEnCartItem(itemId, num,user.getId());
		}
		 
		return "redirect:/cart/cart.html";
	}

	/**
	 * 删除购物车商品
	 * 
	 * @param itemId
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId, HttpServletResponse response, HttpServletRequest request) {
		logger.info("删除购物车内容.....");
		TbUser user = userService.getUserByToken(request, response);
		MSResult result =new MSResult();
		if(user==null){
			logger.info("用户未登录，删除cookie中数据");
			result=cartService.deleteCartItem(itemId, request, response);
		}else{
			logger.info("用户已经登录，删除数据库中数据");
			result=cartService.deleteEnCartItem(itemId,user.getId());
		}
		
		return "redirect:/cart/cart.html";
	}
	
}

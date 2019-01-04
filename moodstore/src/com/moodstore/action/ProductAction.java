package com.moodstore.action;

import com.moodstore.domain.Product;
import com.moodstore.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	//妯″瀷椹卞姩浣跨敤鐨勭被
	private Product product=new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	//struts鍜宻pring鏁村悎鏃舵寜鍚嶇О鑷姩娉ㄥ叆
	private ProductService productService;
	
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	/**
	 * 淇濆瓨鐨勬柟娉�
	 */
	public String save(){
		System.out.println("action涓殑save鏂规硶........");
		productService.save(product);
		return NONE;
	}
}

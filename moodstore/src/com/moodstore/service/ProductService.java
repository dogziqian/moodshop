package com.moodstore.service;

import org.springframework.transaction.annotation.Transactional;

import com.moodstore.dao.ProductDao;
import com.moodstore.domain.Product;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 
	 * 业务层的保存
	 */
			
	public void save(Product product) {
		System.out.println("service层的save方法.......");
		productDao.save(product);
	}
	
}

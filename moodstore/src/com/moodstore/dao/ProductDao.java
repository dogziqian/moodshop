package com.moodstore.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.moodstore.domain.Product;

public class ProductDao extends HibernateDaoSupport{
/**
 * 
 * dao灞傜殑淇濆瓨
 */
	public void save(Product product) {
		System.out.println("dao涓殑save鏂规硶........");
		this.getHibernateTemplate().save(product);
	}

}

package com.moodshop.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodshop.mapper.TbItemMapper;
import com.moodshop.pojo.TbItem;
import com.moodshop.pojo.TbItemExample;

public class TestPageHelper {

	@Test
	public void testPageHelper(){
		//获得mappper代理对象
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		TbItemMapper itemMapper=applicationContext.getBean(TbItemMapper.class);
		//设置分页
		PageHelper.startPage(1, 30);
		
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//获取分页后的结果(将list进行包装)
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		
		long total=pageInfo.getTotal();//总记录数
		int pages=pageInfo.getPages();//总页数
		int pageSize=pageInfo.getPageSize();//每页记录数
		
		System.out.println("total:"+total+"   pages:"+pages+"    pageSize:"+pageSize);
	}
}

package com.moodshop.jdis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.moodshop.rest.component.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class testJedis {
	@Test
	public void testJedisSingle() throws Exception {
		// 单机版测试
		// 创建jdis对象
		Jedis jedis = new Jedis("192.168.206.128", 6379);
		jedis.set("test", "hello jedis");
		String str = jedis.get("test");
		System.out.println(str);
		jedis.close();
	}

	// 使用连接池
	@Test
	public void testJedisPool() throws Exception {
		// 创建一个连接池对象
		// 系统中应该是单例的。
		JedisPool jedisPool = new JedisPool("192.168.206.128", 6379);
		// 从连接池中获得一个连接
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get("test");
		System.out.println(result);
		// jedis必须关闭
		jedis.close();

		// 系统关闭时关闭连接池
		jedisPool.close();

	}

	// 连接redis集群
	@Test
	public void testJedisCluster() throws Exception {
		// 创建一个JedisCluster对象
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.206.128",7001));
		nodes.add(new HostAndPort("192.168.206.128",7002));
		nodes.add(new HostAndPort("192.168.206.128",7003));
		nodes.add(new HostAndPort("192.168.206.128",7004));
		nodes.add(new HostAndPort("192.168.206.128",7005));
		nodes.add(new HostAndPort("192.168.206.128",7006));
		// 在nodes中指定每个节点的地址
		// jedisCluster在系统中是单例的。
		//System.out.println(nodes);
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("name", "zhangsan");
		jedisCluster.set("value", "100");
		String name = jedisCluster.get("name");
		String value = jedisCluster.get("value");
		System.out.println(name);
		System.out.println(value);

		// 系统关闭时关闭jedisCluster
		jedisCluster.close();
	}
	@Test
	public void testJedisSpring(){
		//创建容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从容器中获取jedis
		JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
		//jedis操作redis
		jedisClient.set("client1", "1000");
		String str=jedisClient.get("client1");
		System.out.println(str);
	}
}

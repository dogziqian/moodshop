package com.moodshop.sso.component;

/**
 * jedis接口
 * @author Administrator
 *
 */
public interface JedisClient {
	public String set(String key,String value);
	public String get(String key);
	public Long del(String key);
	//哈希
	public Long hset(String key,String item,String value);
	public String hget(String key,String item);
	public Long hdel(String key,String item);
	//加减
	public Long incr(String key);
	public Long decr(String key);
	//过期时间
	public Long expire(String key,int second);
	//看是否过期
	public Long ttl(String key);
}

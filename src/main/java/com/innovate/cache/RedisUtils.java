package com.innovate.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.Assert;

/**
 * 客户端操作redis 结合spring-data-redis 使用
 * @author IvanHsu
 * @2018年9月3日 下午3:54:22
 */
public class RedisUtils {

	// redisTemplate 这个变量名称必须和 spring-redis.xml中name="redisTemplate"中的名称一致：
	// <bean name="testRedisTemplate" class="com.founder.utils.RedisUtil">
	// <property name="redisTemplate" ref="redisTemplate"/>
	// </bean>
	private static RedisTemplate<String, Object> redisTemplate;

	public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		RedisUtils.redisTemplate = redisTemplate;
	}
	
	/**
	 * 返回 redisTemplate 调用自定义方法
	 * @return
	 */
	public static RedisTemplate<String, Object> getRedisTemplate()
	{
		return redisTemplate;
	}
	
	/*###################对字符串的操作的api的方法的定义##############################################*/
	
	/**
	 * 设置
	 * @param key
	 * @param obj
	 */
	public static void set(String key, Object obj)
	{
		redisTemplate.opsForValue().set(key, obj);
	}
	
	/**
	 * @param key
	 * @param obj
	 * @param timeout
	 * @param unit
	 */
	public static void set(String key, Object object ,Long timeout)
	{
		if (StringUtils.isNotBlank(key)) {
			redisTemplate.opsForValue().set(key, object, timeout);
		}
	}
	
	/**
	 * @param key
	 * @param obj
	 * @param timeout
	 * @param unit
	 */
	public static void set(String key, Object object ,Long timeout, TimeUnit unit)
	{
		if (StringUtils.isNotBlank(key)) {
			redisTemplate.opsForValue().set(key, object, timeout, unit);
		}
	}
	
	/**
	 * @param key
	 * @param obj
	 * @param timeout
	 * @param unit
	 */
	public static void setExpire(String key, Long timeout, TimeUnit unit)
	{
		if (StringUtils.isNotBlank(key)) {
			redisTemplate.expire(key, timeout, unit);
		}
	}
	
	/**
	 * 获取
	 * @param key
	 * @return
	 */
	public static Object get(String key)
	{
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 删除
	 * @param key
	 */
	public static void delete(String key)
	{
		if(StringUtils.isNotBlank(key)){
			redisTemplate.delete(key);
		}
	}
	
	/**
	 * 查询返回所有key值
	 * @param pattern
	 * @return Set<String> 
	 */
	public static Set<String> getAllKeys(String pattern)
	{
		return redisTemplate.keys(pattern);
	}
	
	/*###################对list的操作的api的方法的定义##############################################*/
	
	/**
	 * @param key
	 * @param index
	 * @param value
	 */
	public static void addListEntity(String key, long index, Object value)
	{
		redisTemplate.opsForList().set(key, index, value);
	}
	
	/**
	 * 获取list的大小
	 * @param key
	 * @return
	 */
	public static long getListsize(String key)
	{
		Assert.hasLength(key, "@RedisUtils.getListsize  key值不能为空...");
		return redisTemplate.opsForList().size(key);
	}
	
	/**
	 * 按区间取值取值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Object> getListRange(String key , int start , int end)
	{
		Assert.hasLength(key, "@RedisUtils.getListRange 值不能为空...");
		return redisTemplate.opsForList().range(key, start, end);
	}
	
	/**
	 * 返回全部的结合的列表
	 * @param key
	 * @return
	 */
	public static List<Object> getListAll(String key)
	{
		Assert.hasLength(key,"@RedisUtils.getListAll key值不能为空...");
		return getListRange(key, 0, -1);
	}
	
	/*###################对Map的操作的api的方法的定义##############################################*/
	
	/**
	 * 存储单个值
	 * @param key
	 * @param mapKey
	 * @param mapValue
	 */
	public static void put(String key , String mapKey , String mapValue)
	{
		Assert.hasLength(key,"@RedisUtils.put key值不能为空...");
		redisTemplate.opsForHash().put(key, mapKey, mapValue);
	}
	
	/**
	 * 按集合存储map
	 * @param key
	 * @param valueMap
	 */
	public static void putAllMap(String key , Map<Object,Object> valueMap)
	{
		Assert.hasLength(key,"@RedisUtils.putAllMap key值不能为空...");
		redisTemplate.opsForHash().putAll(key, valueMap);
	}
	
	/**
	 * 单个键的值获取方法
	 * @param key
	 * @param mapKey
	 * @return
	 */
	public static Object getValueByKey(String key, Object mapKey)
	{
		Assert.hasLength(key,"@RedisUtils.getValueByKey key值不能为空...");
		return redisTemplate.opsForHash().get(key, mapKey);
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static Map<Object,Object> getMapAll(String key)
	{
		Assert.hasLength(key,"@RedisUtils.getAll key值不能为空...");
		return redisTemplate.opsForHash().entries(key);
	}
	
	/**
	 * 存储集合的所有的值的集合 返回list
	 * @param key
	 * @return
	 */
	public static List<Object> getAllMapValues(String key)
	{
		Assert.hasLength(key,"@RedisUtils.getAllMapValues key值不能为空...");
		return redisTemplate.opsForHash().values(key);
	}
	
	/**
	 * map对应的所有的可以的集合
	 * @param key
	 * @return
	 */
	public static Set<Object> getAllMapKeys(String key)
	{
		Assert.hasLength(key,"@RedisUtils.getAllMapValues key值不能为空...");
		return redisTemplate.opsForHash().keys(key);
	}
	
	/*###################对soreted set 及 set 的操作的api的方法的定义##############################################*/
	
	/**
	 * set集合的添加
	 * @param key
	 * @param values
	 * @return
	 */
	public static Long addSet(String key ,Object ... values)
	{
		Assert.hasLength(key,"@RedisUtils.addSet key值不能为空...");
		return redisTemplate.opsForSet().add(key, values);
	}
	
	/**
	 * 有序集合添加
	 * @param key
	 * @param value
	 * @param score
	 * @return
	 */
	public static Boolean addZSet(String key,Object value,double score)
	{
		Assert.hasLength(key,"@RedisUtils.addZSet key值不能为空...");
		return redisTemplate.opsForZSet().add(key, value, score);
	}
	
	/**
	 * 按照区间获取值
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<Object> getZSetRange(String key, long start , long end)
	{
		Assert.hasLength(key,"@RedisUtils.getZSetRange key值不能为空...");
		return redisTemplate.opsForZSet().range(key, start, end);
	}
	
	/**
	 * 连带分数的值一起获取
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public static Set<ZSetOperations.TypedTuple<Object>> getZSetRangeWithScore(String key, long start , long end,boolean isResver)
	{
		Assert.hasLength(key,"@RedisUtils.getZSetRangeWithScore key值不能为空...");
		if(isResver){
			return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
		}
		return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
	}
	
}

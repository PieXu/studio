package com.innovate.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import com.innovate.util.LoggerUtils;

/**
 * Redis实现分布式锁
 * @author IvanHsu
 * @2018年9月4日 下午4:05:37
 */
public class RedisDistributeLockUtils {

	private static String REDIS_LOCK_PREFIX = "redis_lock:";
	/**
	 * 分布式添加锁
	 * @param requestId 请求锁的唯一表示
	 * @param acquireTimeout 获取超时时间 单位毫秒
	 * @param timeout 锁超时时间 单位毫秒
	 * @return
	 */
	public static boolean tryLock(String requestId,final String userId,long acquireTimeout, long timeout)
	{
		boolean isGetLock = false;
        //锁名，即key值,拼接一下，尽量防止和其他的重复
        final String lockKey = REDIS_LOCK_PREFIX + requestId;
        // 超时时间，上锁后超过此时间则自动释放锁
        final int lockExpire = (int) (timeout / 1000);
        // 获取锁的超时时间，超过这个时间则放弃获取锁
        long end = System.currentTimeMillis() + acquireTimeout;
        //尝试加锁并返回值，获取时间获取
        while (System.currentTimeMillis() < end) 
        {
        	isGetLock = RedisUtils.getRedisTemplate().execute(new RedisCallback<Boolean>() {
	            @Override
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	            	boolean bol =  connection.setNX(lockKey.getBytes(),userId.toString().getBytes());
	            	if(bol) { 
	            		// 设置超时间
	            		//RedisUtils.getRedisTemplate().expire(lockKey, timeout, TimeUnit.MILLISECONDS);
	            		connection.expire(lockKey.getBytes(), lockExpire);
	            		return true;
	                }else{ 
	                	// 返回-1代表key没有设置超时时间，为key设置一个超时时间
	                    if (connection.ttl(lockKey.getBytes()) == -1) {
	                    	connection.expire(lockKey.getBytes(), lockExpire);
	                    }
	                    try {
	                        Thread.sleep(10);// 线程暂停10毫秒
	                    } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                    }
	                    return false;
	                }
	            }
	        });
            if(isGetLock){
            	break;
            }
        }
		return isGetLock;
	}
	
	/**
	 * 获取当前占有锁的值信息
	 * @param requestId 解锁某个请求
	 */
	public static String getLockUser(String requestId)
	{
        final String lockKey = REDIS_LOCK_PREFIX + requestId;
        try {
        	byte[] bytes = RedisUtils.getRedisTemplate().execute(new RedisCallback<byte[]>() {
				@Override
				public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.get(lockKey.getBytes());
				}
			});
        if( null != bytes && bytes.length > 0){
        	return new String(bytes);
        }
        } catch (Exception e) {
            LoggerUtils.error(RedisDistributeLockUtils.class,"获取锁对应值异常...",e);
        }
        return null;
	}
	
	/**
	 * 释放锁
	 * @param requestId 解锁某个请求
	 */
	public static void releaseLock(String requestId,String value)
	{
		//锁名
        String lockKey = REDIS_LOCK_PREFIX + requestId;
        try {
        	String lockValue = (String) RedisUtils.get(lockKey);
        	if(StringUtils.isNotBlank(lockValue) && lockValue.equals(value) ){
        		RedisUtils.delete(lockKey);
        	}else{
        		LoggerUtils.error(RedisDistributeLockUtils.class,"不是锁持有者尝试解锁，解锁失败！");
        	}
        } catch (Exception e) {
            LoggerUtils.error(RedisDistributeLockUtils.class,"分布式解锁异常...");
        }
	}
}

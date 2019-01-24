package com.innovate.core.session;

import org.apache.shiro.cache.Cache;

/**
 * shiro cache manager 接口
 * @author IvanHsu
 * @2018年3月23日 下午2:00:20
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}

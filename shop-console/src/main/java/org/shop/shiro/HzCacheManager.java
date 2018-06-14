package org.shop.shiro;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("cacheManager")
public class HzCacheManager implements CacheManager {

	private static final Logger logger = LoggerFactory.getLogger(HzCacheManager.class);

	@SuppressWarnings("rawtypes")
	private static ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

	@SuppressWarnings("unchecked")
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.info("getCache {}.", name);
		Cache<K, V> cache = caches.get(name);
		if (null == cache) {
			cache = new HzCache<K, V>();
			caches.put(name, cache);
		}
		return cache;
	}

}

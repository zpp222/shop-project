package org.shop.shiro;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HzCache<K, V> implements Cache<K, V> {

	private static final Logger logger = LoggerFactory.getLogger(HzCacheManager.class);

	/** TODO hazelcast */
	private Map<K, V> cache = new HashMap<K, V>();

	public void clear() throws CacheException {
		logger.info("clear ..");
		cache.clear();
	}

	public V get(K key) throws CacheException {
		V value = cache.get(key);
		logger.info("get {} : {}!", key, value);
		return value;
	}

	public Set<K> keys() {
		logger.info("keys ..");
		Set<K> result = cache.keySet();
		return result;
	}

	public V put(K key, V value) throws CacheException {
		logger.info("put {} : {}", key, value);
		return cache.put(key, value);
	}

	public V remove(K key) throws CacheException {
		logger.info("remove {}!", key);
		return cache.remove(key);
	}

	public int size() {
		logger.info("size ..");
		return cache.size();
	}

	public Collection<V> values() {
		logger.info("values ..");
		return cache.values();
	}

}

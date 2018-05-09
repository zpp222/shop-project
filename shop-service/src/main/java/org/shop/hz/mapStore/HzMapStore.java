package org.shop.hz.mapStore;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.MapStore;

public class HzMapStore implements MapStore<String, Object> {

	private static Logger logger = LoggerFactory.getLogger(HzMapStore.class);

	public Object load(String key) {
		logger.info("load :" + key);
		return null;
	}

	public Map<String, Object> loadAll(Collection<String> keys) {
		logger.info("loadAll :" + keys);
		return null;
	}

	public Iterable<String> loadAllKeys() {
		logger.info("loadAllKeys ...");
		return null;
	}

	public void delete(String key) {
		logger.info("delete :" + key);

	}

	public void deleteAll(Collection<String> keys) {
		logger.info("deleteAll :" + keys);

	}

	public void store(String key, Object value) {
		logger.info("store :" + key + ":" + value);

	}

	public void storeAll(Map<String, Object> map) {
		logger.info("storeAll :" + map);

	}

}

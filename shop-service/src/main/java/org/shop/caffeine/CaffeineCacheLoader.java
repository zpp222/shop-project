package org.shop.caffeine;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.shop.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.benmanes.caffeine.cache.CacheLoader;

public class CaffeineCacheLoader implements CacheLoader<Object, Object> {

	private static Logger logger = LoggerFactory.getLogger(CaffeineCacheLoader.class);

	@Autowired
	private UserDao userDao;

	@Override
	public @Nullable Object load(@NonNull Object key) throws Exception {
		logger.info("load info from {}", key);
		Object result = null;
		switch (key.toString()) {
		case "getUserCount":
			result = userDao.getUserCount();
			break;
		default:
			break;
		}
		return result;
	}

}

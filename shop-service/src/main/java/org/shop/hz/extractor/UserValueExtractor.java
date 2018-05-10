package org.shop.hz.extractor;

import org.shop.serviceI.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.query.extractor.ValueCollector;
import com.hazelcast.query.extractor.ValueExtractor;

public class UserValueExtractor extends ValueExtractor<User, String> {

	private static Logger logger = LoggerFactory.getLogger(UserValueExtractor.class);

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void extract(User user, String id, ValueCollector collector) {
		logger.info("Extractor begin with user'id {}, collector {} !", id, collector);
		if (null == user || null == user.getId()) {
		} else {
			collector.addObject(user.getId());
		}
		logger.info("Extractor end with user'id {}, collector {} !", user.getId(), collector);
	}

}

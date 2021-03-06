package org.shop.hz.extractor;

import org.shop.serviceI.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.hazelcast.query.extractor.ValueCollector;
import com.hazelcast.query.extractor.ValueExtractor;

public class UserValueExtractor extends ValueExtractor<User, String> {

	private static Logger logger = LoggerFactory.getLogger(UserValueExtractor.class);

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void extract(User user, String userId, ValueCollector collector) {
		logger.info("Extractor begin with user {} ,id {}, collector {} !", JSON.toJSONString(user), user.getId(), JSON.toJSONString(collector));

		collector.addObject(user.getId());

		logger.info("Extractor end with user {} ,id {}, collector {} !", JSON.toJSONString(user), user.getId(), JSON.toJSONString(collector));
	}

}

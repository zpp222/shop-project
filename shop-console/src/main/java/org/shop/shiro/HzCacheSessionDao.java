package org.shop.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("sessionDAO")
public class HzCacheSessionDao extends AbstractSessionDAO {

	private static final Logger logger = LoggerFactory.getLogger(HzCacheSessionDao.class);

	/** TODO hazelcast */
	private static Map<Serializable, Session> cache = new HashMap<Serializable, Session>();

	public void delete(Session session) {
		logger.info("delete session {}!", session.getId());
		cache.remove(session.getId());

	}

	public Collection<Session> getActiveSessions() {
		logger.info("getActiveSessions .. ");
		return cache.values();
	}

	public void update(Session session) throws UnknownSessionException {
		logger.info("update session {}!", session.getId());
		cache.remove(session.getId());
		cache.put(session.getId(), session);
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		logger.info("doCreate session {}!", session.getId());
		cache.put(sessionId, session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.info("doReadSession session {}!", sessionId);
		return cache.get(sessionId);
	}

}

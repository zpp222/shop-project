package org.shop.service;

import org.shop.aop.ServiceLog;
import org.shop.dao.UserDao;
import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("org.shop.service.UserLoginServiceImpl")
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserDao userDao;

	private Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Cacheable(value = "hzMap", key = "#user.name")
	public User login(User user) {
		String userName = user.getName();
		logger.info("login user'name {} !", userName);
		User local = userDao.getStudent(userName);
		if (null == local)
			local = user;
		logger.info("login user is {} !", local);
		return local;
	}

	@ServiceLog
	@Transactional
	public void register(User user) {
		logger.info("register ...");
		userDao.save(user);
	}

	@Cacheable(value = "hzMap", key = "#id")
	public User getUserById(String id) {
		logger.info("getUserById {}", id);
		User local = userDao.getUserById(id);
		if (null == local) {
			local = new User();
		}
		return local;
	}

}

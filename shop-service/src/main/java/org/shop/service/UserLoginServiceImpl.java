package org.shop.service;

import org.shop.dao.UserDao;
import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("org.shop.service.UserLoginServiceImpl")
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserDao userDao;

	private Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	public User login(User user) {
		logger.info("login ...");
		user = userDao.getStudent(user.getId());
		return user;
	}

	public void register(User user) {
		logger.info("register ...");
		userDao.save(user);
	}

}

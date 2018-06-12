package org.shop.service;

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

	
	public String login(User user) {
		String errorCode = "SVR-999";
		String userName = user.getName();
		logger.info("login user'name {} !", userName);
		User local = userDao.getStudent(userName);
		if (null == local) {
			errorCode = "SVR-001";
		}else{
			if(local.getPasswd().equals(user.getPasswd())){
				errorCode ="0000000";
			}
		}
		logger.info("login user is {} !", local);
		return errorCode;
	}

	@Transactional
	public void register(User user) {
		logger.info("register ...");
		userDao.save(user);
	}
	
	@Cacheable(value = "hzMap", key = "#user.id")
	public User getUserById(String id){
		return null;
	}

}

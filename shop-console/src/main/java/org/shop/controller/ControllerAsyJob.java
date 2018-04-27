package org.shop.controller;

import java.util.Date;
import java.util.UUID;

import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service("ControllerAsyJob")
public class ControllerAsyJob implements Runnable, BeanFactoryAware {
	BeanFactory beanFactory = null;

	@Autowired
	private UserLoginService userLoginService;

	public void run() {
		long begin = new Date().getTime();
		System.out.println(Thread.currentThread() + "================begin============" + begin);
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user = userLoginService.login(user);
		long end = new Date().getTime();
		System.out.println(Thread.currentThread() + "==============end==============" + end);
		System.out.println("====>> used " + (end - begin) + " ms");
	}

	public void setBeanFactory(BeanFactory context) throws BeansException {
		beanFactory = context;
	}

}

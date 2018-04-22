package org.shop.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceRunMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		context.getStartupDate();

		com.alibaba.dubbo.container.Main.main(args);
	}
}

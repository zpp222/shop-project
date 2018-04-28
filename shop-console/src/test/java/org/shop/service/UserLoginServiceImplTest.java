package org.shop.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.controller.ControllerAsyJob;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class UserLoginServiceImplTest {

	@Resource
	ControllerAsyJob controllerAsyJob;
	@Test
	public void testLogin() {
		for (int i = 0; i < 1; i++) {
			Thread th = new Thread(controllerAsyJob);
			th.start();
		}

		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

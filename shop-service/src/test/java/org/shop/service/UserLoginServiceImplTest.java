package org.shop.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class UserLoginServiceImplTest {

	@Autowired
	private UserLoginService userLoginService;

	@Test
	@Transactional
	public void testLogin() {
		User user = new User();
		user.setId("1");
		user = userLoginService.login(user);
		assertEquals(user.getName(), "zpp");
	}

	@Test
	@Transactional
	public void testSave() {
		User user = new User();
		user.setId("1");
		user.setName("zpp");
		user.setPhone("18729211089");
		user.setSex("男");
		userLoginService.register(user);
		assertEquals("1", "1");
	}

}

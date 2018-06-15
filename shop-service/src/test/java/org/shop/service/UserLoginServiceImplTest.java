package org.shop.service;

import org.junit.Test;
import org.shop.serviceI.dto.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit test for simple App.
 */
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class UserLoginServiceImplTest {

	@Autowired
	private UserLoginService userLoginService;

	@Test
	@Transactional
	public void testLogin() {
	}

	@Test
	@Transactional
	public void testSave() {
	}

}

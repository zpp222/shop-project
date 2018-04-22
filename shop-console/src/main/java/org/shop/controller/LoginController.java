package org.shop.controller;

import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Autowired
	UserLoginService userLoginService;

	@RequestMapping("/login/{id}")
	@ResponseBody
	public User login(@PathVariable String id) {
		User user = new User();
		user.setId(id);
		user = userLoginService.login(user);
		return user;
	}
}

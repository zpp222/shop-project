package org.shop.controller;

import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserLoginService userLoginService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody User user) {
		String rtcode = userLoginService.login(user);
		JSONObject json = new JSONObject();
		json.put("rtcode", rtcode);
		return json.toJSONString();
	}

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public User register(@RequestBody User user) {
		logger.info("register user'name :" + user.getName());
		userLoginService.register(user);
		return user;
	}
}

package org.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.shop.serviceI.dto.User;
import org.shop.serviceI.dto.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

//@CrossOrigin
@Controller
public class LoginController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserLoginService userLoginService;

	private static String LOGIN_SUCC = "LG_0000";
	private static String LOGIN_EXCP = "LG_9999";
	private static String LOGIN_FAIL = "LG_1000";

	private static final String SALT = "shop";

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public User login(HttpServletRequest request,
	 * HttpServletResponse resp, @RequestBody User user) { HttpSession session =
	 * request.getSession(); String sid = session.getId();
	 * logger.info("session id :" + sid); session.setAttribute("username",
	 * user.getName()); User result = userLoginService.login(user); return
	 * result; }
	 */

	@RequiresRoles(value = { "system", "user" }, logical = Logical.OR)
	@RequiresPermissions(value = { "update" })
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	@ResponseBody
	public String register(@RequestBody User user) {
		JSONObject json = new JSONObject();
		logger.info("register user'name :" + user.getName());

		String pw = user.getPasswd();
		String name = user.getName();
		if (null == pw || "".equals(pw) || null == name || "".equals(name)) {
			json.put("rtcode", LOGIN_FAIL);
		} else {
			pw = new Md5Hash(pw, SALT).toString();// .toBase64()
			user.setPasswd(pw);
			user.setSalt(SALT);
			userLoginService.register(user);
			json.put("rtcode", LOGIN_SUCC);
		}
		return json.toJSONString();
	}

	@RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
	@ResponseBody
	public String unauthorized(HttpServletRequest request) {
		logger.info("unauthorized user ..");
		JSONObject json = new JSONObject();
		json.put("rtcode", "999-未登录");
		return json.toJSONString();
	}

	@RequestMapping(value = "/login2", method = RequestMethod.POST)
	@ResponseBody
	public String login2(HttpServletRequest request, HttpServletResponse resp, @RequestBody User user) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPasswd());
		Subject subject = SecurityUtils.getSubject();
		logger.info("加密paawd:{} to:{}", user.getPasswd(), new Md5Hash(user.getPasswd(), SALT));
		JSONObject json = new JSONObject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				json.put("rtcode", LOGIN_SUCC);
				Session session = subject.getSession();
				json.put("token", session.getId());
				session.setAttribute("username", user.getName());
				logger.info("login succ!" + session.getId());
			} else {
				json.put("rtcode", LOGIN_FAIL);
				logger.info("login fail!");
			}
		} catch (AuthenticationException e) {
			logger.info("login excp : " + e);
			json.put("rtcode", LOGIN_EXCP);
		}
		return json.toJSONString();
	}

	@RequiresRoles(value = { "system", "user" }, logical = Logical.AND)
	@RequiresPermissions(value = { "TEST" })
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse resp) {
		return "no permissions !";
	}

	@RequiresRoles(value = { "system", "user" }, logical = Logical.OR)
	@RequiresPermissions(value = { "update" })
	@RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable String id) {
		logger.info("getUserById {}.", id);
		User u = userLoginService.getUserById(id);
		return u;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout() {
		JSONObject json = new JSONObject();
		try {
			SecurityUtils.getSubject().logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("rtcode", "OUT_0000");
		return json.toJSONString();
	}
}

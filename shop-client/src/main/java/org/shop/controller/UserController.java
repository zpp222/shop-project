package org.shop.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.shop.serviceI.dto.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserLoginService userLoginService;
	
	@RequiresRoles(value = { "system", "user" }, logical = Logical.OR)
	@RequiresPermissions(value = { "update" })
	@RequestMapping(value = "/user/count", method = RequestMethod.GET)
	@ResponseBody
	public String getUserCount(){
		JSONObject json = new JSONObject();
		Map<String,String> num = userLoginService.getUserCount();
		json.put("num", num.get("num"));
		return json.toJSONString();
	}
}

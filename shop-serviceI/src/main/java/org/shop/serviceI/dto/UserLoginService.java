package org.shop.serviceI.dto;

import java.util.Map;

public interface UserLoginService {

	public User login(User user);

	public void register(User user);

	public User getUserById(String userId);
	
	public Map<String,String> getUserCount();
}

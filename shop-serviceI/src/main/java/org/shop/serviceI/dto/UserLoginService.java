package org.shop.serviceI.dto;

public interface UserLoginService {

	public String login(User user);

	public void register(User user);

	public User getUserById(String userId);
}

package org.shop.serviceI.dto;

public interface UserLoginService {

	public User login(User user);

	public void register(User user);

	public User getUserById(String userId);
}

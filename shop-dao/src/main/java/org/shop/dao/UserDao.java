package org.shop.dao;

import java.util.Map;

import org.shop.serviceI.dto.User;

public interface UserDao {

	public void save(User user);

	public User getStudent(String id);

	public User getUserById(String userId);
	
	public Map<String,String> getUserCount();
}

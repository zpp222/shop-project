package org.shop.dao;

import org.shop.serviceI.dto.User;

public interface UserDao {

	public void save(User user);

	public User getStudent(String id);
}

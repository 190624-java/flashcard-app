package com.revature.daos;

import com.revature.beans.User;

public interface UserDao {

	public User createUser(User user);
	public User getUserByUsername(String username);
	public void updateUser(User user);
	public void deleteUser(User user);
	
}

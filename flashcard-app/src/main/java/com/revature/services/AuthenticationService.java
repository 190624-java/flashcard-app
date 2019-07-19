package com.revature.services;

import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.daos.UserDaoImpl;

public class AuthenticationService {

	public static final UserDao userDao = new UserDaoImpl();
	
	public static User isValidUser(String username, String password) {
		if(username != null && password != null) {
			User user = userDao.getUserByUsername(username);
			return (user != null && user.getPassword() == password)? user : null;
		}
		return null;
	}
	
}

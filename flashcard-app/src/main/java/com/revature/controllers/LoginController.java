package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.User;
import com.revature.services.AuthenticationService;

public class LoginController {

	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = AuthenticationService.isValidUser(username, password);
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("/flashcard/home");
		} else {
			resp.sendRedirect("/flashcard/login");
			//TODO: invalid login page
		}
	}
	
}

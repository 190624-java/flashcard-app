package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.LoginController;

public class DispatcherServlet extends HttpServlet {

	protected void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		System.out.println(url);
		
		switch (url) {
		case "/flashcard-app/flashcard/login":
			new LoginController().login(req, resp);
			break;
		case "/flashcard-app/flashcard/home":
			resp.sendRedirect("/flashcard-app/flashcard/home");
			break;
		default:
			resp.setStatus(405);// throw Method Not Supported
			break;
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.dispatch(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.dispatch(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.dispatch(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.dispatch(req, resp);
	}
	
}

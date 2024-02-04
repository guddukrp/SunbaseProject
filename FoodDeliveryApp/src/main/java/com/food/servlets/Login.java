package com.food.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.daoimpl.StringEncryptor;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;


@WebServlet("/login")
public class Login extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String un = request.getParameter("username");
		String pwd = request.getParameter("password");
		
		String encryptPwd = StringEncryptor.encrypt(pwd);
		
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		User user = userDAOImpl.getUserNPassword(un, encryptPwd);
		
		if(user!=null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			session.setAttribute("username", un);
			
			response.sendRedirect("/FoodDeliveryApp/home");

		}else {
			response.sendRedirect("JSP/login.jsp?error=Invalid username or password! Try Again!");
			
		}
		
	}

}

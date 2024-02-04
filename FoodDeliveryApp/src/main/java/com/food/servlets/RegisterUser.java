package com.food.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.daoimpl.StringEncryptor;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;


@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		 
		 String un = request.getParameter("username");
		 String pwd = request.getParameter("password");
		 String email = request.getParameter("email");
		 String address = request.getParameter("address");
		 String role = request.getParameter("role");
		 
		 
		 String encryptPwd = StringEncryptor.encrypt(pwd);
		 
		 User user = new User(un,encryptPwd,email,address,role);
		 UserDAOImpl userDAOImpl = new UserDAOImpl();
		 
		 userDAOImpl.addUser(user);
		 
		 request.getRequestDispatcher("restaurant.jsp").forward(request, response);
		 
		 
		 
		 
		
	}

	

}

package com.food.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.model.User;


@WebServlet("/isValid")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user!=null) {
			session.setAttribute("user",user);
			response.sendRedirect("JSP/checkout.jsp");
		}else{
			response.sendRedirect("JSP/login.jsp");
		}
	}

}

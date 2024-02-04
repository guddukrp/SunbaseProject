package com.food.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher rd = request.getRequestDispatcher("JSP/login.jsp");
		rd.forward(request, response);
		
		
	}



}

package com.food.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.daoimpl.MenuDAOImpl;

@WebServlet("/menu")
public class Menu extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String restaurantId = request.getParameter("restaurantId");
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		
		
		
		List<com.food.model.Menu> allMenus = menuDAOImpl.getAllMenus(Integer.parseInt(restaurantId));
		
		request.setAttribute("menus", allMenus);
		
		RequestDispatcher rd = request.getRequestDispatcher("JSP/menu.jsp");
		rd.forward(request, response);
	
	}


}

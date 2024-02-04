package com.food.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Restaurant;


@WebServlet("/home")
public class Home extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RestaurantDAOImpl restaurantDAOImpl = new RestaurantDAOImpl();
		
		List<Restaurant> restaurants = restaurantDAOImpl.getAllRestaurant();
		
		request.setAttribute("restaurantList", restaurants);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("JSP/home.jsp");
		rd.include(request, response);
		
	}


}

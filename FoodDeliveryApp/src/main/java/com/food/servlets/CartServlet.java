package com.food.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.CartItem;
import com.food.daoimpl.MenuDAOImpl;
import com.food.daoimpl.OrderItemDAOImpl;
import com.food.model.Cart;
import com.food.model.Menu;
import com.food.model.OrderItem;


@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
		if(cart==null) {
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");
		
		if(action.equals("add")) {
			addItemToCart(request,cart);
		}else if(action.equals("update")) {
			updateCartItem(request,cart);
		}else if(action.equals("remove")) {
			removeItemFromCart(request,cart);
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("JSP/cart.jsp");
		
	}
	
	private void addItemToCart(HttpServletRequest request, Cart cart) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		MenuDAOImpl menuDAOImpl = new MenuDAOImpl();
		Menu menuItem = menuDAOImpl.getMenu(itemId);
		
		HttpSession session = request.getSession();
		session.setAttribute("restaurantId", menuItem.getRestaurantId());
		
		if(menuItem!=null) {
			CartItem item = new CartItem(
					menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice()
					
					);
			cart.addItem(item);
		}
		
	}

	private void removeItemFromCart(HttpServletRequest request, Cart cart) {
		int itemId= Integer.parseInt(request.getParameter("itemId"));
		cart.removeItem(itemId);
		
	}

	private void updateCartItem(HttpServletRequest request, Cart cart) {
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		cart.updateItem(itemId, quantity);
		
	}

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
		if(cart==null) {
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		String action = request.getParameter("action");
		if(action.equals("add")) {
			addItemToCart(request,cart);
		}else if(action.equals("update")) {
			updateCartItem(request,cart);
		}else if(action.equals("remove")) {
			removeItemFromCart(request,cart);
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("JSP/cart.jsp");
	
	}

}

package com.food.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.CartItem;
import com.food.daoimpl.OrderDAOImpl;
import com.food.daoimpl.OrderHistoryDAOImpl;
import com.food.daoimpl.OrderItemDAOImpl;
import com.food.model.Cart;
import com.food.model.Order;
import com.food.model.OrderHistory;
import com.food.model.OrderItem;
import com.food.model.User;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private OrderDAOImpl orderDAOImpl;
	@Override
	public void init() throws ServletException {
		orderDAOImpl = new OrderDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		
		
		
		if(cart!=null && user!=null && !cart.getItem().isEmpty()) {
			String paymentMethod = request.getParameter("PaymentMethod");
			
			Order order = new Order();
			order.setOrderId(new Random().nextInt(100000) + 900000);       //generate 6 digit orderid
			order.setUserId(user.getUserId());
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setOrderDate(new Date());
			order.setPaymentMethod(paymentMethod);
			order.setStatus("Pending");
			
			double totalAmount=0;
			for(CartItem item : cart.getItem().values()) {
				
				totalAmount += item.getPrice() * item.getQuantity();
			}
			order.setTotalAmount(totalAmount);
			
			orderDAOImpl.addOrder(order);
			session.setAttribute("orders",order);
			
			
			
//orderItem table			
	        HashMap <Integer,CartItem> items = cart.getItem();
	        OrderItemDAOImpl orderItemDAOImpl = new OrderItemDAOImpl();
	        if(!items.isEmpty()){
	        	for(CartItem item : items.values()){
	        		
	        		OrderItem orderItem = new OrderItem(order.getOrderId(),item.getItemId(),item.getQuantity(),item.getPrice());
	        		
	        		orderItemDAOImpl.addOrderItem(orderItem);
		        }
	        }
	        
//orderHistory table
	        OrderHistoryDAOImpl historyDAOImpl = new OrderHistoryDAOImpl();
	        
	        OrderHistory orderHistory = new OrderHistory(order.getUserId(),order.getOrderId(),order.getOrderDate(),
	        		order.getTotalAmount(),"Delivered");
	        
	        
	        historyDAOImpl.addOrderHistory(orderHistory);
        
	        
//	

			cart.clear();
			session.setAttribute("cart", cart);
			response.sendRedirect("JSP/confirmation.jsp");
		}else {
			response.sendRedirect("JSP/cart.jsp");
		}
	}
	

}

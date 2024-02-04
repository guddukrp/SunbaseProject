package com.food.dao;

import java.util.List;

import com.food.model.Order;

public interface OrderDAO {

	void addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	Order getOrder(int orderId);
	List<Order> getAllOrders();
}

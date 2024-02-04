package com.food.dao;

import java.util.List;

import com.food.model.OrderItem;

public interface OrderItemDAO {

	void addOrderItem(OrderItem orderItem);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	OrderItem getOrderItem(int orderItemId);
	List<OrderItem> getAllOrderItems();
}

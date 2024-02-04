package com.food.dao;

import java.util.List;

import com.food.model.OrderHistory;

public interface OrderHistoryDAO {

	void addOrderHistory(OrderHistory orderHistory);
	void updateOrderHistory(OrderHistory orderHistory);
	void deleteOrderHistory(int orderHistoryId);
	OrderHistory getOrderHistory(int orderHistoryId);
	List<OrderHistory> getAllOrderHistorys();
}

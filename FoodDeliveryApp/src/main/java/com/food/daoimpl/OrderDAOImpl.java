//private int orderId;
//private int userId;
//private int restaurantId;
//private Date orderDate;
//private double totalAmount;
//private String status;
//private String paymentMethod;

//OrderID
//UserID
//RestaurantID
//OrderDate
//TotalAmount
//Status
//PaymentMethod

package com.food.daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.model.Order;
import com.food.model.User;

public class OrderDAOImpl implements OrderDAO {

	
	PreparedStatement prepareStatement =null;
	Statement createStatement = null;
	ResultSet res = null;
	private final String ADD_ORDER_QUERY="insert into `ordertable`(`OrderID`,`userID`,`RestaurantID`,`TotalAmount`,`Status`,`PaymentMethod`)"
			+ " values (?,?,?,?,?,?)";
	
	private final String UPDATE_ORDER_QUERY="update `ordertable` set `UserID`=?,`RestaurantID`=?,`TotalAmount`=?,`Status`=?"
			+ ",`PaymentMethod` =? where  `OrderID`=?";
	
	private final String DELETE_ORDER_QUERY="delete from `ordertable` where `OrderID`=?";
	
	private final String GET_ORDER_QUERY= "Select `OrderID`,`userID`,`RestaurantID`,`OrderDate`,`TotalAmount`,`Status`,`PaymentMethod`"
			+ " from `ordertable` where `OrderID`=?";
	private final String GETALL_ORDER_QUERY= "Select `OrderID`,`userID`,`RestaurantID`,`OrderDate`,`TotalAmount`,`Status`,`PaymentMethod` from `ordertable`";
	@Override
	public void addOrder(Order order) {

		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(ADD_ORDER_QUERY);

			prepareStatement.setInt(1,order.getOrderId());
			prepareStatement.setInt(2,order.getUserId());
			prepareStatement.setInt(3,order.getRestaurantId());
			prepareStatement.setDouble(4,order.getTotalAmount());
			prepareStatement.setString(5,order.getStatus());
			prepareStatement.setString(6,order.getPaymentMethod());
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrder(Order order) {

		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(UPDATE_ORDER_QUERY);
			prepareStatement.setInt(1,order.getUserId());
			prepareStatement.setInt(2,order.getRestaurantId());
			prepareStatement.setDouble(3,order.getTotalAmount());
			prepareStatement.setString(4,order.getStatus());
			prepareStatement.setString(5,order.getPaymentMethod());
			prepareStatement.setInt(6,order.getOrderId());
			
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int orderId) {

		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(DELETE_ORDER_QUERY);
			prepareStatement.setInt(1, orderId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Order getOrder(int orderId) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(GET_ORDER_QUERY);
			prepareStatement.setInt(1, orderId);
			
			res = prepareStatement.executeQuery();
			if(res.next()) {
				
				int orderId1=res.getInt("orderId");
				int userID=res.getInt("userID");
				int RestaurantID=res.getInt("RestaurantID");
				Date OrderDate=res.getDate("OrderDate");
				Double TotalAmount=res.getDouble("TotalAmount");
				String Status=res.getString("Status");
				String PaymentMethod=res.getString("PaymentMethod");
				
				return new Order(orderId1,userID,RestaurantID,OrderDate,TotalAmount,Status,PaymentMethod);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<Order>();
		try {
			Connection con = CP.createC();
			createStatement = con.createStatement();
			res = createStatement.executeQuery(GETALL_ORDER_QUERY);
			
			while(res.next()) {
				
				int orderId1=res.getInt("orderId");
				int userID=res.getInt("userID");
				int RestaurantID=res.getInt("RestaurantID");
				Date OrderDate=res.getDate("OrderDate");
				Double TotalAmount=res.getDouble("TotalAmount");
				String Status=res.getString("Status");
				String PaymentMethod=res.getString("PaymentMethod");
				
				orderList.add( new Order(orderId1,userID,RestaurantID,OrderDate,TotalAmount,Status,PaymentMethod));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

}

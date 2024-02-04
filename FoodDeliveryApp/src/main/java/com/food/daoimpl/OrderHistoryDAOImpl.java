//private int orderHistoryId;
//	private int userId;
//	private int orderId;
//	private Date orderDate;
//	private double totalAmount;
//	private String status;

//OrderHistoryID
//UserID
//OrderID
//OrderDate
//TotalAmount
//Status



package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.food.dao.OrderHistoryDAO;
import com.food.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO {

    PreparedStatement prepareStatement = null;
    Statement createStatement = null;
    ResultSet res = null;

    private final String ADD_ORDER_HISTORY_QUERY = "INSERT INTO `orderhistory`(`UserID`, `OrderID`, `OrderDate`, `TotalAmount`, `Status`)" +
            " VALUES (?, ?, ?, ?, ?)";

    private final String UPDATE_ORDER_HISTORY_QUERY = "UPDATE `orderhistory` SET `UserID`=?, `OrderID`=?, `OrderDate`=?, `TotalAmount`=?, `Status`=? " +
            "WHERE `OrderHistoryID`=?";

    private final String DELETE_ORDER_HISTORY_QUERY = "DELETE FROM `orderhistory` WHERE `OrderHistoryID`=?";

    private final String GET_ORDER_HISTORY_QUERY = "SELECT `OrderHistoryID`, `UserID`, `OrderID`, `OrderDate`, `TotalAmount`, `Status`" +
            " FROM `orderhistory` WHERE `OrderHistoryID`=?";

    private final String GET_ALL_ORDER_HISTORY_QUERY = "SELECT `OrderHistoryID`, `UserID`, `OrderID`, `OrderDate`, `TotalAmount`, `Status`" +
            " FROM `orderhistory`";

    @Override
    public void addOrderHistory(OrderHistory orderHistory) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(ADD_ORDER_HISTORY_QUERY);

            prepareStatement.setInt(1, orderHistory.getUserId());
            prepareStatement.setInt(2, orderHistory.getOrderId());
            prepareStatement.setDate(3, new java.sql.Date(orderHistory.getOrderDate().getTime()));
            prepareStatement.setDouble(4, orderHistory.getTotalAmount());
            prepareStatement.setString(5, orderHistory.getStatus());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderHistory(OrderHistory orderHistory) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(UPDATE_ORDER_HISTORY_QUERY);
            prepareStatement.setInt(1, orderHistory.getUserId());
            prepareStatement.setInt(2, orderHistory.getOrderId());
            prepareStatement.setDate(3, new java.sql.Date(orderHistory.getOrderDate().getTime()));
            prepareStatement.setDouble(4, orderHistory.getTotalAmount());
            prepareStatement.setString(5, orderHistory.getStatus());
            prepareStatement.setInt(6, orderHistory.getOrderHistoryId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderHistory(int orderHistoryId) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(DELETE_ORDER_HISTORY_QUERY);
            prepareStatement.setInt(1, orderHistoryId);

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(GET_ORDER_HISTORY_QUERY);
            prepareStatement.setInt(1, orderHistoryId);

            res = prepareStatement.executeQuery();
            if (res.next()) {
                int orderHistoryId1 = res.getInt("OrderHistoryID");
                int userId = res.getInt("UserID");
                int orderId = res.getInt("OrderID");
                Date orderDate = res.getDate("OrderDate");
                double totalAmount = res.getDouble("TotalAmount");
                String status = res.getString("Status");

                return new OrderHistory(orderHistoryId1, userId, orderId, orderDate, totalAmount, status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderHistory> getAllOrderHistorys() {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            Connection con = CP.createC();
            createStatement = con.createStatement();
            res = createStatement.executeQuery(GET_ALL_ORDER_HISTORY_QUERY);

            while (res.next()) {
                int orderHistoryId1 = res.getInt("OrderHistoryID");
                int userId = res.getInt("UserID");
                int orderId = res.getInt("OrderID");
                Date orderDate = res.getDate("OrderDate");
                double totalAmount = res.getDouble("TotalAmount");
                String status = res.getString("Status");

                orderHistoryList.add(new OrderHistory(orderHistoryId1, userId, orderId, orderDate, totalAmount, status));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderHistoryList;
    }
}


//private int orderItemId;
//	private int orderId;
//	private int menuId;
//	private int quantity;
//	private double itemTotal;


//OrderItemID
//OrderID
//MenuID
//Quantity
//ItemTotal

package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

    PreparedStatement prepareStatement = null;
    Statement createStatement = null;
    ResultSet res = null;

    private final String ADD_ORDER_ITEM_QUERY = "INSERT INTO `orderitem`(`OrderID`, `MenuID`, `Quantity`, `ItemTotal`)" +
            " VALUES (?, ?, ?, ?)";

    private final String UPDATE_ORDER_ITEM_QUERY = "UPDATE `orderitem` SET `OrderID`=?, `MenuID`=?, `Quantity`=?, `ItemTotal`=? " +
            "WHERE `OrderItemID`=?";

    private final String DELETE_ORDER_ITEM_QUERY = "DELETE FROM `orderitem` WHERE `OrderItemID`=?";

    private final String GET_ORDER_ITEM_QUERY = "SELECT `OrderItemID`, `OrderID`, `MenuID`, `Quantity`, `ItemTotal`" +
            " FROM `orderitem` WHERE `OrderItemID`=?";

    private final String GET_ALL_ORDER_ITEMS_QUERY = "SELECT `OrderItemID`, `OrderID`, `MenuID`, `Quantity`, `ItemTotal`" +
            " FROM `orderitem`";

    @Override
    public void addOrderItem(OrderItem orderItem) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(ADD_ORDER_ITEM_QUERY);

            prepareStatement.setInt(1, orderItem.getOrderId());
            prepareStatement.setInt(2, orderItem.getMenuId());
            prepareStatement.setInt(3, orderItem.getQuantity());
            prepareStatement.setDouble(4, orderItem.getItemTotal());

            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(UPDATE_ORDER_ITEM_QUERY);
            prepareStatement.setInt(1, orderItem.getOrderId());
            prepareStatement.setInt(2, orderItem.getMenuId());
            prepareStatement.setInt(3, orderItem.getQuantity());
            prepareStatement.setDouble(4, orderItem.getItemTotal());
            prepareStatement.setInt(5, orderItem.getOrderItemId());

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(DELETE_ORDER_ITEM_QUERY);
            prepareStatement.setInt(1, orderItemId);

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        try {
            Connection con = CP.createC();
            prepareStatement = con.prepareStatement(GET_ORDER_ITEM_QUERY);
            prepareStatement.setInt(1, orderItemId);

            res = prepareStatement.executeQuery();
            if (res.next()) {
                int orderItemId1 = res.getInt("OrderItemID");
                int orderId = res.getInt("OrderID");
                int menuId = res.getInt("MenuID");
                int quantity = res.getInt("Quantity");
                double itemTotal = res.getDouble("ItemTotal");

                return new OrderItem(orderItemId1, orderId, menuId, quantity, itemTotal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            Connection con = CP.createC();
            createStatement = con.createStatement();
            res = createStatement.executeQuery(GET_ALL_ORDER_ITEMS_QUERY);

            while (res.next()) {
                int orderItemId1 = res.getInt("OrderItemID");
                int orderId = res.getInt("OrderID");
                int menuId = res.getInt("MenuID");
                int quantity = res.getInt("Quantity");
                double itemTotal = res.getDouble("ItemTotal");

                orderItemList.add(new OrderItem(orderItemId1, orderId, menuId, quantity, itemTotal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItemList;
    }
}


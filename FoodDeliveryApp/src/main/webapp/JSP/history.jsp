<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.food.model.OrderHistory" %> 
<%@ page import="java.util.List" %>
<%@ page import="com.food.daoimpl.OrderHistoryDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);
        }

        .container {
            width: 80%;
            margin: 20px auto;
            text-align: center;
            
        }

        h1 {
            margin-bottom: 20px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
            background-color: rgba(255, 255, 255, 0.5);
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f9f9f9;
        }

        .status {
            text-transform: capitalize;
            color: #007bff;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 30px;
            text-align: center;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s, transform 0.2s;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        .btn:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Order History</h1>
        <table>
            <thead>
                <tr>
                	<th>Order History ID</th>
                	<th>User ID</th>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
            <%
            OrderHistoryDAOImpl history= new OrderHistoryDAOImpl();
            
            List<OrderHistory> histories = history.getAllOrderHistorys();
            
            if (histories != null && !histories.isEmpty()) {
                for (OrderHistory his : histories) {
                    %>
                    <tr>
                        <td><%= his.getOrderHistoryId() %></td>
                        <td><%= his.getUserId() %></td>
                        <td><%= his.getOrderId() %></td>
                        <td><%= his.getOrderDate() %></td>
                        <td>&#8377;<%= his.getTotalAmount() %></td>
                        <td class="status"><%= his.getStatus() %></td>
                    </tr>
                    <%
                }
            } else {
                %>
                <tr>
                    <td colspan="4">No History available</td>
                </tr>
                <%
            }
            %>
            </tbody>
        </table>
        <a href="/FoodDeliveryApp/home" class="btn">Go to Home Page</a>
    </div>
</body>
</html>

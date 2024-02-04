<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="com.food.model.Cart" %>
<%@ page import ="com.food.model.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Order is Placed</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        .icon-checkmark {
            color: #4CAF50;
            font-size: 60px;
            margin-bottom: 20px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
        }

        p {
            color: #666;
            margin-bottom: 30px;
        }

        .order-details {
            text-align: left;
            margin-bottom: 20px;
        }

        .order-details p {
            margin: 8px 0;
        }

        a {
            text-decoration: none;
            color: #fff;
            background-color: #4CAF50;
            padding: 10px 20px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="icon-checkmark">&#10003;</div>
        <h1>Your Order is Placed!</h1>
        <p>Thank you for shopping with us. Your order has been successfully placed.</p>
        
		<% Order order = (Order)session.getAttribute("orders"); %>
		
        <div class="order-details">
        	
            <p><strong>UserName:</strong> <%=session.getAttribute("username") %></p>
            <p><strong>Order ID:</strong> <%=order.getOrderId() %></p>
            <p><strong>Total Amount:</strong>&#8377;<%= String.format("%.3f", order.getTotalAmount()) %></p>
            <p><strong>Status:</strong> <%=order.getStatus() %></p>
            <p><strong>Payment Method:</strong> <%=order.getPaymentMethod() %></p>
        </div>

        <a href="/FoodDeliveryApp/home" onclick="returnToHome()">Continue Shopping</a>
    </div>

    <script>
        function returnToHome() {
            // Add logic to redirect to the home page or any other desired action
            alert('Redirecting to the home page...');
        }
    </script>
</body>
</html>

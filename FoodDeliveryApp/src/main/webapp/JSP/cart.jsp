<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import= "com.food.dao.*" %> 
<%@ page import= "com.food.model.*" %> 
<%@ page import= "java.util.HashMap" %> 
<%@ page import ="java.io.PrintWriter" %>
<%@ page import= "java.util.List" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Cart</title>
		<link rel="stylesheet" type="text/css" href="../CSS/cart.css">
	</head>
<body>
	<div class="dashboard-order">
        <h3>Order Menu</h3>
        <div class="order-address">
            <p>Address Delivery</p>
            <h4>221 B Baker Street, Delhi</h4>
        </div>
    
        <div class="order-wrapper">
        
        <% 
        Cart cart =(Cart) session.getAttribute("cart");
        HashMap<Integer,CartItem> items = cart.getItem();
        double subtotal = 0;
        
        if(!items.isEmpty()){
        
        for(CartItem item : items.values()){
        	
        	
        %>
        
            <div class="order-card">
                <div class="order-detail">
                    <p class="item-name"><%= item.getName()%></p>
                    <form action="/FoodDeliveryApp/cart" method="post">
                    	<input type="hidden" name="itemId" value="<%=item.getItemId() %>">
	                    <div class="order-input-actions">
	                        <input type="number" name="quantity" value=<%= item.getQuantity()%> min="1">
	                        <button type="submit" name="action" value="update" class="update-button" >Update</button>
	                        <button type="submit" name="action" value="remove" class="remove-button" >Remove</button>
	                        
	                    </div>
                    </form>
                </div>
                <h4 class="order-price">&#8377;<%= String.format("%.3f", item.getPrice()) %> </h4>
                
            </div>
    			
            <% subtotal += item.getQuantity()*item.getPrice();
            } 
        	}else{
		    	%>
		    	<h3 style="color:red">Your Cart is empty </h3>
		    	<%	
			    }
	            %>
  
        </div>
        <a href="/FoodDeliveryApp/menu?restaurantId=<%=session.getAttribute("restaurantId") %>"
    	class= "addmore">
    		<button >Add More Items</button>
    	</a>
        <hr class="divider">
        <div class="order-total">
            <p>
                Subtotal <span>&#8377;<%= subtotal %></span>
            </p>
            
            <p>
                Delivery Fee <span><del>&#8377;60</del></span>
            </p>
    
            <div class="order-promo">
                <input class="input-promo" type="text" placeholder="Apply Voucher">
                <button class="button-promo">Find Promo</button>
            </div>
            <hr class="divider">
            <p>
                Total<span>&#8377;<%=String.format("%.3f", subtotal) %></span>
            </p>
        </div>
        <%
        	if(session.getAttribute("cart")!=null){
        %>
        <form action="/FoodDeliveryApp/isValid" method="post">
        	<button type="submit" class="checkout">Proceed to Checkout</button>
        </form>
        
        <%} %>
    
    </div>
</body>
</html>
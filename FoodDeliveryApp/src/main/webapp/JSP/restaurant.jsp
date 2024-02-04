<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "com.food.model.Restaurant" %> 
<%@ page import= "java.util.List" %>
 
 
<!DOCTYPE html>
<html>
<head>
  <title>Restaurant</title>
  <link rel="stylesheet" href="CSS/restaurant.css">
</head>
<body>



	<header>
        <h1>Food Delivery App</h1>
        <nav>
            <a href="#">Home</a>
            <a href="#">Menu</a>
            <a href="#">Contact</a>
            <img src="#" alt="Profile Icon">
        </nav>
    </header>


  <main>

    <div class="product-container">

    <%
    
    List<Restaurant> restaurantList = (List<Restaurant>)request.getAttribute("restaurantList");
    
    for(Restaurant res:restaurantList){

    
    %>
      <div class="product">
        <img src=<%=res.getImagePath() %> alt="restautant-image">
        <h2><%= res.getName() %></h2>
        <p><%= res.getCuisineType() %></p>
        <button class="orderbtn">
       		 <a href="/FoodDeliveryApp/menu?restaurantId=<%=res.getRestaurantId() %>" style="text-decoration: none">Menu</a>	
        </button>
      </div>
      
      <%} %>
      
      
      
      
      
      
      
      
      
      
      
      
 <%-- 
      <div class="product">
        <img src="../IMAGES/img.jpg" alt="Shoe">
        <h2>Resturant</h2>
        <p>Indian restaurant</p>
        <button class="orderbtn">Order</button>
      </div>
      
      href = <%= orderItem.deleteOrderItem(item.getOrderItemId())
--%>
      
      
    </div>
  </main>


</body>
</html>
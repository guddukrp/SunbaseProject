<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "com.food.model.Restaurant" %> 
<%@ page import= "java.util.List" %>
<%@ page import ="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="CSS/home.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Delivery Dashboard</title>
</head>
<body>


	<h3 class="logo">Food Delivery Service</h3>
	<div class="welcome-message">
	    Welcome <%if( session.getAttribute("username")!=null){
	    	out.println(session.getAttribute("username"));
	    }
	    	%>
	    
	</div>

	<div class="sidebar">
		<div class="sidebar-menu">
			<span class="fas fa-search"></span> <a href="#">Search</a>
		</div>

		<div class="sidebar-menu">
			<span class="fas fa-home"></span> <a href="#">Home</a>
		</div>

		<div class="sidebar-menu">
			<span class="fas fa-heart"></span> <a href="#">Favs</a>
		</div>
		<%if( session.getAttribute("username")!=null){
	    	
	    	%>
	    
			<div class="sidebar-menu">
				<span class="fas fa-user"></span> <a href="JSP/profile.jsp">Profile</a>
			</div>
	
			<div class="sidebar-menu">
				<span class="fas fa-shopping-cart"></span> <a href="JSP/cart.jsp">Cart</a>
			</div>
			
			<div class="sidebar-menu">
				<span class="fas fa-history"></span> <a href="JSP/history.jsp">history</a>
			</div>
		<% 
		}else{
		%>
			<div class="sidebar-menu">
				<span class="fas fa-user"></span> <a href="JSP/login.jsp">Login</a>
			</div>
		<% 
		}
    	%>
		
		<div class="sidebar-menu">
			<span class="fas fa-sliders-h"></span> <a href="#">Setting</a>
		</div>
	</div>

	<!-- main dashboard -->
	<div class="dashboard">
		<div class="dashboard-banner">
			<img
				src="https://images.unsplash.com/photo-1615719413546-198b25453f85?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D">
			<div class="banner-promo">
				<h1>
					<span>50% OFF</span><br> Tasty Food <br> On Your Hand
				</h1>
			</div>
		</div>

		<h3 class="dashboard-title">Recommended Food For You</h3>
		<div class="dashboard-menu">
			<a href="#">Favourites</a> <a href="#">Best Seller</a> <a href="#">Near
				Me</a> <a href="#">Promotion</a> <a href="#">Top Rated</a> <a href="#">All</a>
		</div>

		<!--dashboard_content-->

		<div class="dashboard-content">
		
		
		<%
    
	    List<Restaurant> restaurantList = (List<Restaurant>)request.getAttribute("restaurantList");
		
		if(restaurantList!=null){
	    
	    for(Restaurant res:restaurantList){
	
	    
	    %>
		
		<div class="dashboard-card" >
			<a href="/FoodDeliveryApp/menu?restaurantId=<%=res.getRestaurantId() %>">
				<img class="card-image" src=<%=res.getImagePath() %> >
			</a>
			<div class="card-details">
				<h4>
					<%= res.getName() %><span class="fas fa-star"><%=res.getRating() %></span>
				</h4>
				<p><%= res.getCuisineType() %></p>
				<p class="card-time">
					<span class="fas fa-clock"></span>15-30 mins
				</p>
			</div>
		</div>

		<%}
	    }else{
	    	%>
	    	<p>No Restaurant available</p>
	    	<%
	    } %>
		
		
	    %>

			
			
		</div>
	</div>

</body>
</html>
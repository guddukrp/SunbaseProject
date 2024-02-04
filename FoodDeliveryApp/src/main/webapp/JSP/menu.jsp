<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "com.food.model.Menu" %> 
<%@ page import= "java.util.List" %>
<%@ page import ="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="CSS/menu.css">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<title>Menu Dashboard</title>
</head>
<body>

	
	<h3 class="logo">Menu</h3>
	
	<div class="welcome-message">
	    Welcome <%if( session.getAttribute("username")!=null){
	    	out.println(session.getAttribute("username"));
	    }
	    	%>
	</div>
	

	<!---sidebar--->
	<div class="sidebar">
		<div class="sidebar-menu">
			<span class="fas fa-search"></span> <a href="#">Search</a>
		</div>

		<div class="sidebar-menu">
			<span class="fas fa-home"></span> <a href="/FoodDeliveryApp/home">Home</a>
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
			<span class="fas fa-shopping-cart"></span> <a href="JSP/cart.jsp">Cart</a>
		</div>
		
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
		    List<Menu> menuList =(List<Menu>)request.getAttribute("menus");
		    
			if(menuList!=null){
				
			
		    for(Menu res:menuList){
	
	    
	    %>
		
		<div class="dashboard-card">
		    <div class="card-details">
		        <h4><%= res.getItemName() %></h4>
		        <p><%= res.getDescription() %></p>
		        <p>&#8377;<%= res.getPrice() %></p>
		        
		        <form action="/FoodDeliveryApp/cart" method="post">
			        <div class="center-content cart-controls">
			            <label for="quantity">Quantity:</label>
			            <input type="number" id="quantity" name="quantity" value="1" min="1">
			        </div>
			        <div class="center-content">
			        	<input type ="hidden" name="itemId" value="<%=res.getMenuId()%>">
			        	<input type ="hidden" name ="action" value="add">
			        	
			            <button type="submit"  class="add-to-cart-btn">Add to Cart</button>
			        </div>
		        </form>
		    </div>
		</div>

		<%
		}
		 %>
			
		</div>
		<%}else{
	    	%>
	    	<p>No menu items available</p>
	    	<%
	    } %>
	</div>

</body>
</html>
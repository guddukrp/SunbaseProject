<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import= "com.food.model.User" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>User Profile</title>
    <style>
body {

    font-family: Arial, sans-serif;
    background: url(../IMAGES/img.jpg) no-repeat;
    margin: 0;
    padding: 0;
}

.profile-container {
    max-width: 600px;
    margin: 20px auto;
    background-color: #fff;
    background-color: rgba(255, 255,255, .5);
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    position: relative;
}

h2 {
    color: #333;
}

.logout-button {
    position: absolute;
    top: 30px;
    right: 30px;
    background-color: #FF4136; 
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.profile-table {
    width: 100%;
    margin-top: 20px;
    border-collapse: collapse;
}

.profile-table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: left;
}

.button-container {
    margin-top: 20px;
}

.profile-button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    margin-right: 10px;
}

.profile-button:hover {
    background-color: #45a049;
}

    </style>
</head>
<body>
	<%if( session.getAttribute("user")!=null){
		User user = (User)session.getAttribute("user");
	
	   %>
	<div class="profile-container">
	<form action="/FoodDeliveryApp/logout">
		<button type="submit" class="logout-button" >Logout</button>	
	</form>
	
	    <h2>User Profile</h2>
	    <table class="profile-table">
	        <tr>
	            <td>Name:</td>
	            <td><%=user.getName() %></td>
	        </tr>
	        <tr>
	            <td>Username:</td>
	            <td><%=user.getUsername() %></td>
	        </tr>
	        <tr>
	            <td>Email:</td>
	            <td><%=user.getEmail() %></td>
	        </tr>
	        <tr>
	            <td>Address:</td>
	            <td><%=user.getAddress() %></td>
	        </tr>
	        <tr>
	            <td>Role:</td>
	            <td><%=user.getRole()%></td>
	        </tr>
	    </table>
	    <%} %>
	
	    <div class="button-container">
	        <button class="profile-button" onclick="window.location.href='/FoodDeliveryApp/home'">Go to Homepage</button>
	        <button class="profile-button" onclick="window.location.href='#'">Add Restaurant</button>
	    </div>
	</div>

</body>
</html>

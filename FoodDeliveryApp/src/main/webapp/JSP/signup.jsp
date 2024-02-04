<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign Up</title>
		<link rel="stylesheet" href="../CSS/signup.css">
	</head>
<body>
	
	<div class="main">
       <div class="register">
        <h1>Sign Up</h1>
        <form action="/FoodDeliveryApp/RegisterUser" id = "register" method="get">

            <label>Username :</label>
            <br>
            <input type="text" name="username" placeholder="Enter Username" class ="inputs" required>
            <br><br>
            <label>Password :</label>
            <br>
            <input type="text" name="password" placeholder="Enter Password" class ="inputs" required>
            <br><br>

            <label>Email :</label>
            <br>
            <input type="text" name="email" placeholder="Enter your Email" class ="inputs" required>
            <br><br>
            <label>Address :</label>
            <br>
            <input type="text" name="address" placeholder="Enter your Address" class ="inputs" required >
            <br><br>          
            <label >Role:</label>
            <br>
			  <select name="role" class="inputs">
			    <option value="Customer">Customer</option>
			    <option value="RestaurantAdmin">RestaurantAdmin</option>
			    <option value="SystemAdmin">SystemAdmin</option>
			  </select>

            <br><br>
            <input type="submit" id="submit">

        </form>
       </div>
    </div>
</body>
</html>
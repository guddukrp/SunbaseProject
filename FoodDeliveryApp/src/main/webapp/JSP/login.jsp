<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login </title>
		<link rel="stylesheet" type="text/css" href="../CSS/login.css">
	</head>
<body>

	<div class="wrapper">
        <form action="/FoodDeliveryApp/login" method ="post">
            <h1>Login</h1>
            
            <%
			    String error = request.getParameter("error");
			    if (error != null && !error.isEmpty()) {
			%>
			        <h4 style="color: red"><%= error %></h4>
			<%
			    }
			%>
            <div class="input-box">
                <input type="text" name="username" placeholder="username" required>
                <i class='bx bxs-user' ></i>
                
            </div>
            <div class="input-box">
                <input type="password" name="password" placeholder="password" required>
                <i class='bx bxs-lock' ></i>
            </div>
        
            
            <div class = "remember">
                <label ><input type="checkbox">Remember me</label>

                <a href="#">Forgot password?</a>
            </div>
            <div>
                <button type="submit" class="btn">Login</button>
            </div>
             
            <div class = "register">
                <p>Don't have an account?  <a href="signup.jsp">SignUp</a></p>
                
            </div>
            <div class = "skip">
            	<p ><a href="/FoodDeliveryApp/home" >Skip for Now >></a></p>
            </div>
            
            
        </form>

    </div>
</body>
</html>
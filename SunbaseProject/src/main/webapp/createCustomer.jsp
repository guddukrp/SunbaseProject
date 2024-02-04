<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Vendor</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: linear-gradient(45deg, #85FFBD 0%, #FFFB7D 100%);
            margin: 0;
            padding: 0;
        }

        .form-container {
            max-width: 500px;
            margin: 50px auto;
            background-color: rgba(255, 255, 255, 0.5); 
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px; 
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
            transition: background-color 0.3s;
        }

        h2 {
            color: #333;
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 16px;
            color: #555;
        }

        input {
            width: 90%;
            padding: 12px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 15px;
            transition: border-color 0.3s;
        }

        input[type="submit"] {
        	margin-left:10px;
            background-color: #4caf50;
            color: #fff;
            padding: 12px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        @media (max-width: 768px) {
            .form-container {
                margin: 20px auto;
            }
        }
        
        
        
    </style>
</head>
<body>
<%
       if(session.getAttribute("token")!=null){
    	   
     %>
    <div class="form-container">
        <h2>Create Customer</h2>
        <form action="task" method="post">
        	<label for="id">Unique Id:</label>
            <input type="text" id="id" name="id" required>
            
            <label for="fname">First Name:</label>
            <input type="text" id="fname" name="fname" required>

            <label for="lname">Last Name:</label>
            <input type="text" id="lname" name="lname" required>

            <label for="street">Street:</label>
            <input type="text" id="street" name="street" required>

            <label for="address">Address :</label>
            <input type="text" id="address" name="address">
            
            <label for="city">City:</label>
            <input type="text" id="city" name="city" required>

            <label for="state">State:</label>
            <input type="text" id="state" name="state" required>


            <label for="email">Email :</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" required>

            <input type="submit" name="action" value="Create Customer">
        </form>
    </div>
   <%
      }else{
   	   
  	   %>
   	   <h3>You are not Logged in. <a href = "index.jsp">login here</a></h3>
  	   <%
      }
   %>
</body>
</html>


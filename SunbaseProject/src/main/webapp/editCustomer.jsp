<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sunbase.model.Customer" %>
<%@ page import="com.sunbase.daoimpl.ServiceImpl" %>
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

		String cid = request.getParameter("cid");
	
		Customer customer = new ServiceImpl().getCustomer(cid);
		
	%>
    <div class="form-container">
        <h2>Edit Customer</h2>
        <form action="task" method="post">
        	<input type="hidden" name="cid" value="<%=cid%>">
        
            <label for="fname">First Name:</label>
            <input type="text" id="fname" name="fname" value="<%=customer.getFname() %>" required>

            <label for="lname">Last Name:</label>
            <input type="text" id="lname" name="lname" value="<%=customer.getLname() %>" required>

            <label for="street">Street:</label>
            <input type="text" id="street" name="street" value="<%=customer.getStreet() %>" required>

            <label for="address">Address :</label>
            <input type="text" id="address" name="address" value="<%=customer.getAddress() %>" required>
            
            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="<%=customer.getCity() %>" required>

            <label for="state">State:</label>
            <input type="text" id="state" name="state" value="<%=customer.getState() %>" required>


            <label for="email">Email :</label>
            <input type="text" id="email" name="email" value="<%=customer.getEmail() %>" required>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="<%=customer.getPhone() %>" required>

            <input type="submit" name="action" value="update">
            
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
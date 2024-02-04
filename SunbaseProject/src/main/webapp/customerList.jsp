<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.sunbase.model.Customer" %>
<%@ page import="com.sunbase.daoimpl.ServiceImpl" %>
<%@ page import="java.util.List, javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer List</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
    }

    .container {
        max-width: 95%;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        color: #333;
    }

    .container-row {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .button-container {
        margin-right: 10px;
    }

    .button-container button {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .button-container button:hover {
        background-color: #0056b3;
    }

    #search-container {
        display: flex;
        align-items: center;
    }

    #search-container label {
        margin-right: 10px;
        color: #555;
    }

    #search-by {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-right: 10px;
    width: 100px; /* Adjust the width as needed */
    font-weight: bold;
    }

    #search-input {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-right: 10px;
    }

    #search-button {
        padding: 8px 20px;
        background-color: #28a745;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    #search-button:hover {
        background-color: #218838;
    }

    .table-container {
        margin: 0 auto;
    }

    table {
        width: 100%;
        border-collapse: collapse;
    }

    table th, table td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }

    table th {
        background-color: #f2f2f2;
        color: #333;
    }

    tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tbody tr:hover {
        background-color: #f0f0f0;
    }

    .action-links {
        text-align: right;
        margin-top: 20px;
    }

    .action-links a {
        text-decoration: none;
        padding: 8px 20px;
        background-color: #dc3545;
        color: #fff;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }

    .action-links a:hover {
        background-color: #c82333;
    }
    .pagination {
    margin-top: 20px;
    text-align: center;
	}
	
	.pagination a {
	    display: inline-block;
	    padding: 5px 10px;
	    margin: 0 5px;
	    background-color: #007bff;
	    color: #fff;
	    text-decoration: none;
	    border-radius: 5px;
	}
	
	.pagination a:hover {
	    background-color: #0056b3;
	}
	
	.pagination span {
	    display: inline-block;
	    padding: 5px 10px;
	    margin: 0 5px;
	    background-color: #f2f2f2;
	    color: #333;
	    border-radius: 5px;
	}
    
    
</style>
</head>
<body>
	<%
       if(session.getAttribute("token")!=null){
    	   
     %>
	<div class="container">
		<!-- Logout Button -->
	    <div class="button-container" style="text-align: right;">
	        <a href="logout"><button style="background-color: #dc3545;">Logout</button></a>
	    </div>
	    <h1>Customer List</h1>
	    
	    
	    <div class="container-row">
	        <!-- Add Customer Button -->
	        <div class="button-container">
	            <a href="createCustomer.jsp"><button>Add Customer</button></a>
	             <a href="Sycn"><button style="background-color: #28a745;">Sync</button></a>
	        </div>
	        
	        
	        <!-- Search Form -->
	        <div id="search-container">
	            <form action="search" method="get">
	                <label for="search-by">Search by:</label>
	                <select id="search-by" name="search-by">
	                    <option value="first-name">First Name</option>
	                    <option value="city">City</option>
	                    <option value="email">Email</option>
	                    <option value="phone">Phone</option>
	                </select>
	                <input type="text" id="search-input" name="search-input" placeholder="Search">
	                <button type="submit" id="search-button">Search</button>
	            </form>
	        </div>
	    </div>
	    
	    <div class="table-container">
	        <table>
	            <thead>
	                <tr>
	                    <th>First Name</th>
	                    <th>Last Name</th>
	                    <th>Street</th>
	                    <th>City</th>
	                    <th>State</th>
	                    <th>Email</th>
	                    <th>Phone</th>
	                    <th>Action</th>
	                </tr>
	            </thead>
	            <tbody>
	                <%
	                
	                    int pageNumber = 1; // Default page number
	                    int pageSize = 10; // Number of customers per page
	                    String pageNumberStr = request.getParameter("page");
	                    if (pageNumberStr != null) {
	                        pageNumber = Integer.parseInt(pageNumberStr);
	                    }
	                    
	                    List<Customer> customers;
	                    if(request.getAttribute("searchResults")==null){
	                    	 customers = new ServiceImpl().getAllCustomer(pageNumber, pageSize);
	                    }else{
	                    	 customers =( List<Customer>)request.getAttribute("searchResults");
	                    }
	                    
						
	                    if (!customers.isEmpty()) {
	                        for (Customer customer : customers) {
	                %>
	                <tr>
	                    <!-- Display customer information -->
	                    <td><%=customer.getFname() %></td>
	                    <td><%=customer.getLname() %></td>
	                    <td><%=customer.getStreet() %></td>
	                    <td><%=customer.getCity() %></td>
	                    <td><%=customer.getState() %></td>
	                    <td><%=customer.getEmail() %></td>
	                    <td><%=customer.getPhone() %></td>
	
	                    <td>
	                        <!-- Action links -->
	                        <a href="editCustomer.jsp?cid=<%= customer.getCid() %>">Edit</a> /
	                        <a href="task?cid=<%=customer.getCid()%>&action=delete">Delete</a>
	                    </td>
	                </tr>
	                <% 
	                        }
	                    } else {
	                %> 
	                <!-- Display message if no customers available -->
	                <tr>
	                    <td colspan="8"><h3>No Customer Available!</h3></td>
	                </tr>
	                <%
	                    }
	                
	                %>
	            </tbody>
	        </table>
	        
	        <!-- Pagination controls -->
	        <div class="pagination">
	            <% if (pageNumber > 1) { %>
	            <a href="?page=<%= pageNumber - 1 %>">Previous</a>
	            <% } %>
	            
	            <% if (!customers.isEmpty()) { %>
	            <span>Page <%= pageNumber %></span>
	            <% } %>
	            
	            <a href="?page=<%= pageNumber + 1 %>">Next</a>
	        </div>
	        
	    </div>
	    
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
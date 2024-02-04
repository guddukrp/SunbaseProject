package com.sunbase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbase.daoimpl.ServiceImpl;
import com.sunbase.model.Customer;


@WebServlet("/task")
public class CustomerServlet extends HttpServlet {

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");

	        if (action != null && !action.isEmpty()) {
	            if (action.equals("delete")) {
	            	
	                String cid = request.getParameter("cid");
	                new ServiceImpl().deleteCustomer(cid);
	                
	                response.sendRedirect("customerList.jsp");
	            }
	        }
	   
	    }
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String action = request.getParameter("action");
	        if (action != null && !action.isEmpty()) {
	            if (action.equals("update")) {
	            	
	            	String cid = request.getParameter("cid");

	            	String fname = request.getParameter("fname");
	            	String lname = request.getParameter("lname");
	            	String street = request.getParameter("street");
	            	String address = request.getParameter("address");
	            	String city = request.getParameter("city");
	            	String state = request.getParameter("state");
	            	String email = request.getParameter("email");
	            	String phone = request.getParameter("phone");


	            	Customer customer = new ServiceImpl().getCustomer(cid);
	            	
	            	customer.setFname(fname);
	            	customer.setLname(lname);
	            	customer.setStreet(street);
	            	customer.setAddress(address);
	            	customer.setCity(city);
	            	customer.setState(state);
	            	customer.setEmail(email);
	            	customer.setPhone(phone);
	            	
	            	new ServiceImpl().updateCustomer(customer);
	                response.sendRedirect("customerList.jsp");
	               
	                
	                 
	            }else if (action.equals("Create Customer")) {
	                
	            	
	            	String id = request.getParameter("id");
	            	String fname = request.getParameter("fname");
	            	String lname = request.getParameter("lname");
	            	String street = request.getParameter("street");
	            	String address = request.getParameter("address");
	            	String city = request.getParameter("city");
	            	String state = request.getParameter("state");
	            	String email = request.getParameter("email");
	            	String phone = request.getParameter("phone");

	                Customer customer = new Customer(id, fname, lname, street, address, city, state, email, phone);
	                
	                new ServiceImpl().addCustomer(customer);
	                
	                response.sendRedirect("customerList.jsp");
	               
	            }else {
	            	response.sendRedirect("customerList.jsp");
	            }
	        }
	}
}


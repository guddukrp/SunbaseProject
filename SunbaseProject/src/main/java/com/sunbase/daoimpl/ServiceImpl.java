package com.sunbase.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunbase.dao.Service;
import com.sunbase.model.Customer;

public class ServiceImpl implements Service {

	private static final String INSERT_CUSTOMER = "INSERT INTO customer (id,fname, lname, street, address, city, state, email, phone) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id = ?";
	private static final String UPDATE_CUSTOMER = "UPDATE customer SET fname=?, lname=?, street=?, address=?, city=?, state=?, email=?, phone=? WHERE id=?";
	private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";
	private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
	private static final String LOGIN = "SELECT COUNT(*) FROM user WHERE username = ? AND password = ?";


	public void addCustomer(Customer c) {

		if(checkCustomerExist(c.getCid())){
			
			new ServiceImpl().updateCustomer(c);
			
		}else {

			try (Connection con = CP.createC(); PreparedStatement stmt = con.prepareStatement(INSERT_CUSTOMER)) {
				
				stmt.setString(1, c.getCid());
				stmt.setString(2, c.getFname());
				stmt.setString(3, c.getLname());
				stmt.setString(4, c.getStreet());
				stmt.setString(5, c.getAddress());
				stmt.setString(6, c.getCity());
				stmt.setString(7, c.getState());
				stmt.setString(8, c.getEmail());
				stmt.setString(9, c.getPhone());
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean checkCustomerExist(String cid) {
		
		Customer customer = new ServiceImpl().getCustomer(cid);
		if(customer!=null) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void deleteCustomer(String id) {

		try {
			Connection con = CP.createC();
			PreparedStatement stmt = con.prepareStatement(DELETE_CUSTOMER);

			stmt.setString(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustomer(Customer c) {

		try {
				Connection con = CP.createC();
	            PreparedStatement stmt = con.prepareStatement(UPDATE_CUSTOMER);
	            stmt.setString(1, c.getFname());
	            stmt.setString(2, c.getLname());
	            stmt.setString(3, c.getStreet());
	            stmt.setString(4, c.getAddress());
	            stmt.setString(5, c.getCity());
	            stmt.setString(6, c.getState());
	            stmt.setString(7, c.getEmail());
	            stmt.setString(8, c.getPhone());
	            stmt.setString(9, c.getCid());
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public Customer getCustomer(String id) {
		
			
		 try {
			 	Connection con = CP.createC();
		 
			 	PreparedStatement stmt = con.prepareStatement(SELECT_CUSTOMER_BY_ID);
	            stmt.setString(1, id);
	            ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                	Customer customer = new Customer();
                    customer.setCid(rs.getString("id"));
                    customer.setFname(rs.getString("fname"));
                    customer.setLname(rs.getString("lname"));
                    customer.setStreet(rs.getString("street"));
                    customer.setAddress(rs.getString("address"));
                    customer.setCity(rs.getString("city"));
                    customer.setState(rs.getString("state"));
                    customer.setEmail(rs.getString("email"));
                    customer.setPhone(rs.getString("phone"));
                    return customer;
                	
                }
	                
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
		
	}

	@Override
	public List<Customer> getAllCustomer(int pageNumber, int pageSize) {
	    List<Customer> customers = new ArrayList<>();
	    try {
	        Connection con = CP.createC();
	        int offset = (pageNumber - 1) * pageSize; 
	        String SQL_SELECT_PAGE_CUSTOMERS = "SELECT * FROM customer LIMIT ? OFFSET ?";
	        PreparedStatement stmt = con.prepareStatement(SQL_SELECT_PAGE_CUSTOMERS);
	        stmt.setInt(1, pageSize); 
	        stmt.setInt(2, offset);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Customer customer = new Customer();
	            customer.setCid(rs.getString("id"));
	            customer.setFname(rs.getString("fname"));
	            customer.setLname(rs.getString("lname"));
	            customer.setStreet(rs.getString("street"));
	            customer.setAddress(rs.getString("address"));
	            customer.setCity(rs.getString("city"));
	            customer.setState(rs.getString("state"));
	            customer.setEmail(rs.getString("email"));
	            customer.setPhone(rs.getString("phone"));
	            customers.add(customer);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return customers;
	}


	@Override
	public String login(String username, String password) {
		
		try {
			Connection con = CP.createC();
			PreparedStatement stmt = con.prepareStatement(LOGIN);
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				
			
				String username1=res.getString("username");
				
				
				return username1;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> searchCustomers(String searchTerm, String searchBy) {
	    String SELECT_BY_CUSTOMERS = null;
	    
	    if (searchTerm == null || searchTerm.trim().isEmpty()) {
	    	return new ServiceImpl().getAllCustomer(1, 10);
	    }
	    
	    if ("first-name".equals(searchBy)) {
	        SELECT_BY_CUSTOMERS = "SELECT * FROM `customer` WHERE `fname` LIKE ?";
	    } else if ("city".equals(searchBy)) {
	        SELECT_BY_CUSTOMERS = "SELECT * FROM `customer` WHERE `city` LIKE ?";
	    } else if ("email".equals(searchBy)) {
	        SELECT_BY_CUSTOMERS = "SELECT * FROM `customer` WHERE `email` LIKE ?";
	    } else if ("phone".equals(searchBy)) {
	        SELECT_BY_CUSTOMERS = "SELECT * FROM `customer` WHERE `phone` LIKE ?";
	    }
	    else {
	        return null; // Invalid searchBy parameter
	    }

	    List<Customer> customers = new ArrayList<>();
	    try {
	        Connection con = CP.createC();
	        PreparedStatement stmt = con.prepareStatement(SELECT_BY_CUSTOMERS);
	        stmt.setString(1, "%" + searchTerm + "%"); // Set the search term with wildcard characters
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            Customer customer = new Customer();
	            customer.setCid(rs.getString("id"));
	            customer.setFname(rs.getString("fname"));
	            customer.setLname(rs.getString("lname"));
	            customer.setStreet(rs.getString("street"));
	            customer.setAddress(rs.getString("address"));
	            customer.setCity(rs.getString("city"));
	            customer.setState(rs.getString("state"));
	            customer.setEmail(rs.getString("email"));
	            customer.setPhone(rs.getString("phone"));
	            customers.add(customer);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return customers;
	}


}

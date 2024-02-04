package com.sunbase.dao;

import java.util.List;

import com.sunbase.model.Customer;

public interface Service {

	public void addCustomer(Customer c) ;
	public void deleteCustomer(String id);
	public void updateCustomer(Customer c);
	public Customer getCustomer(String id);
	public List<Customer> getAllCustomer(int pageNumber, int pageSize);
	public String login(String username,String password);
	public List<Customer> searchCustomers(String searchTerm, String searchBy);
	
}

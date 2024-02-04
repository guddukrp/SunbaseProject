package com.sunbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbase.daoimpl.ServiceImpl;
import com.sunbase.model.Customer;
import com.sunbase.utility.SunbaseUtility;


@WebServlet("/Sycn")
public class SycnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String token =(String)session.getAttribute("token");
		System.out.println(token);
		List<Customer> customerList = SunbaseUtility.fetchCustomerList(token);
		
		for(Customer customer:customerList) {
			new ServiceImpl().addCustomer(customer);
			System.out.println(customer);
		}
		
		response.sendRedirect("customerList.jsp");
	}

}

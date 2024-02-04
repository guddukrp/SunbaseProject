package com.sunbase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbase.daoimpl.ServiceImpl;
import com.sunbase.model.Customer;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String searchWord = request.getParameter("search-input");
        String searchBy = request.getParameter("search-by");
        
        // Perform validation on search input if necessary
        
        ServiceImpl service = new ServiceImpl();
        List<Customer> searchResults = service.searchCustomers(searchWord, searchBy);
        
        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("/customerList.jsp").forward(request, response);
    }
}


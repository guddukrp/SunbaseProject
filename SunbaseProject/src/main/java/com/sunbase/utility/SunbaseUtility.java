package com.sunbase.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sunbase.model.Customer;

public class SunbaseUtility {

	//it's return token in string format
    public static String authenticate( String loginId, String password) {
        String bearerToken = null;
        String authUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        try {
            URL url = new URL(authUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String requestBody = "{\"login_id\":\"" + loginId + "\",\"password\":\"" + password + "\"}";
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes());
            outputStream.flush();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String tokenString = response.toString();
                
                int startIndex = tokenString.indexOf(":") + 2; 

                int endIndex = tokenString.lastIndexOf("\"");

                // Extract the token substring
                bearerToken = tokenString.substring(startIndex, endIndex);
                System.out.println("Authentication successful. Bearer token: " + bearerToken);
            } else {
                System.out.println("Authentication failed. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bearerToken;
    }

    public static List<Customer> fetchCustomerList( String bearerToken) {
    	List<Customer> customers=new ArrayList<Customer>();
    	String customerListUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
    	
    	try {
            // Create a URL object from the customer list endpoint URL
            URL url = new URL(customerListUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

            
            int responseCode = connection.getResponseCode();

            // Check if the response code indicates success (200 OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                
                JSONArray jsonArray = new JSONArray(response.toString());
                

                // Print user details for debug purpose
                System.out.println("Customer details:");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id=  jsonObject.getString("uuid");
                    String fname=  jsonObject.getString("first_name");
                    String lname=  jsonObject.getString("last_name");
                    String street=  jsonObject.getString("street");
                    String address=  jsonObject.getString("address");
                    String city=  jsonObject.getString("city");
                    String state=  jsonObject.getString("state");
                    String email=  jsonObject.getString("email");
                    String phone=  jsonObject.getString("phone");
                    
                    customers.add(new Customer(id,fname,lname,street,address,city,state,email,phone));
                    System.out.println(id);
                    
                }
                
            } else {
                // Print an error message if fetching the customer list fails
                System.out.println("Failed to fetch customer list. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return customers;
    }
    
    
	//debug purpose
//  public static void main(String[] args) {
//      // Authentication
//      
//      String loginId = "test@sunbasedata.com";
//      String password = "Test@123";
//      String bearerToken = authenticate( loginId, password);
//
//      // Fetch Customer List
//      if (bearerToken != null) {
//          List<Customer> list = fetchCustomerList(bearerToken);
//          
//          for(Customer c :list) {
//          	System.out.println(c);
//          }
//      } else {
//          System.out.println("Authentication failed. Cannot fetch customer list.");
//      }
//  }
}

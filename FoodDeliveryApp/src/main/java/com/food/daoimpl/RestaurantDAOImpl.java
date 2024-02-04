//RestaurantID
//Name
//CuisineType
//DeliveryTime
//Address
//AdminUserID
//Rating
//IsActive
//ImagePath

//private int restaurantId;
//	private String name;
//	private String cuisineType;
//	private int deliveryTime;
//	private String address;
//	private int adminUserId;
//	private double rating;
//	private boolean isActive;
//	private String imagePath;

package com.food.daoimpl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.model.User;

public class RestaurantDAOImpl implements RestaurantDAO {
	PreparedStatement prepareStatement =null;
	Statement statement = null;
	ResultSet res = null;
	private final String ADD_RESTAURANT_QUERY  ="insert into `Restaurant`(`RestaurantID`,`Name`,`CuisineType`,"
			+ "`DeliveryTime`,`AdminUserID`,`Rating`,`Address`,`IsActive`) values (?,?,?,?,?,?,?,?)";
	
	private final String UPDATE_RESTAURANT_QUERY = "update `Restaurant` set `Name`=?,`CuisineType`=?,`DeliveryTime`=?,"
			+ "`AdminUserID`=?,`Rating`=?,`Address`=?,`IsActive`=? where  `RestaurantID`=?";
	
	private final String DELETE_RESTAURANT_QUERY="delete from `Restaurant` where `RestaurantID`=?";
	
	private final String GET_RESTAURANT_QUERY= "Select `RestaurantID`,`Name`,`CuisineType`,`DeliveryTime`,`AdminUserID`,"
			+ "`Rating`,`Address`,`IsActive` from `Restaurant` where `RestaurantID`=?";
	private final String GETALL_RESTAURANT_QUERY= "Select `RestaurantID`,`Name`,`CuisineType`,`DeliveryTime`,`AdminUserID`,"
			+ "`Rating`,`Address`,`IsActive` ,`ImagePath` from `Restaurant`";

	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(ADD_RESTAURANT_QUERY);
			
//			File input Stream fis = new FileStream("image.img");
//			stmt.setBinaryStream(1,fis);
			
			
			prepareStatement.setInt(1, restaurant.getRestaurantId());
			prepareStatement.setString(2,restaurant.getName());
			prepareStatement.setString(3,restaurant.getCuisineType());
			prepareStatement.setInt(4,restaurant.getDeliveryTime());
			prepareStatement.setInt(5,restaurant.getAdminUserId());
			prepareStatement.setDouble(6, restaurant.getRating());
			prepareStatement.setString(7,restaurant.getAddress());
			prepareStatement.setBoolean(8,restaurant.getIsActive());
			
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	


	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(UPDATE_RESTAURANT_QUERY);
			
			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2,restaurant.getCuisineType());
			prepareStatement.setInt(3,restaurant.getDeliveryTime());
			prepareStatement.setInt(4,restaurant.getAdminUserId());
			prepareStatement.setDouble(5, restaurant.getRating());
			prepareStatement.setString(6,restaurant.getAddress());
			prepareStatement.setBoolean(7,restaurant.getIsActive());
			prepareStatement.setInt(8, restaurant.getRestaurantId());
			
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(DELETE_RESTAURANT_QUERY);
			prepareStatement.setInt(1, restaurantId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(GET_RESTAURANT_QUERY);
			prepareStatement.setInt(1, restaurantId);
			
			res = prepareStatement.executeQuery();
			if(res.next()) {
				
				 int restaurantId1=res.getInt("restaurantId");
				 String name= res.getString("Name");
				 String cuisineType= res.getString("CuisineType");
				 int deliveryTime= res.getInt("DeliveryTime");
				 String address= res.getString("Address");
				 int adminUserId = res.getInt("AdminUserID");
				 double rating = res.getDouble("Rating");
				 boolean isActive = res.getBoolean("IsActive");
//				 Blob imagePath= res.getBlob("ImagePath");
				 
				 return new Restaurant(restaurantId1,name,cuisineType, deliveryTime,address ,
						 adminUserId , rating, isActive);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		
		List<Restaurant> restaurantList= new ArrayList<Restaurant>();
		
		try {
			Connection con = CP.createC();
			statement = con.createStatement();
			res = statement.executeQuery(GETALL_RESTAURANT_QUERY);
			
			while(res.next()) {
				
				 int restaurantId1=res.getInt("restaurantId");
				 String name= res.getString("Name");
				 String cuisineType= res.getString("CuisineType");
				 int deliveryTime= res.getInt("DeliveryTime");
				 String address= res.getString("Address");
				 int adminUserId = res.getInt("AdminUserID");
				 double rating = res.getDouble("Rating");
				 boolean isActive = res.getBoolean("IsActive");
				 String imagePath= res.getString("ImagePath");
				 
				 restaurantList.add( new Restaurant(restaurantId1,name,cuisineType, deliveryTime,address ,
						 adminUserId , rating, isActive,imagePath));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurantList;
	}

}

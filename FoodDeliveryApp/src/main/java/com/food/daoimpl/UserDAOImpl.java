//UserID
//Username
//Password
//Email
//Address
//Role
//CreatedDate
//LastLoginDate

//private int userId;
//	private String name;
//	private String username;
//	private String password;
//	private String email;
//	private String address;
//	private String role;

package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.UserDAO;
import com.food.model.User;

public class UserDAOImpl implements UserDAO {
	PreparedStatement prepareStatement =null;
	Statement createStatement = null;
	ResultSet res = null;
	private final String ADD_USER_QUERY="insert into `user`(`username`,`password`,`email`,`address`,`role`)"
			+ " values (?,?,?,?,?)";
	
	private final String UPDATE_USER_QUERY="update `user` set `username`=?,`password`=?,`email`=?,`address`=?,`role`=?"
			+ " where  `userID`=?";
	
	private final String DELETE_USER_QUERY="delete from `user` where `userId`=?";
	
	private final String GET_USER_QUERY= "Select `userID`,`username`,`password`,`email`,`address`,`role`"
			+ " from `user` where `userId`=?";
	private final String GETALL_USER_QUERY= "Select `userID`,`username`,`password`,`email`,`address`,`role` from `user`";

	@Override
	public void addUser(User user) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(ADD_USER_QUERY);
//			prepareStatement.setInt(1, user.getUserId());
			prepareStatement.setString(1,user.getUsername());
			prepareStatement.setString(2,user.getPassword());
			prepareStatement.setString(3,user.getEmail());
			prepareStatement.setString(4,user.getAddress());
			prepareStatement.setString(5,user.getRole());
			
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateUser(User user) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(UPDATE_USER_QUERY);
			prepareStatement.setString(1,user.getUsername());
			prepareStatement.setString(2,user.getPassword());
			prepareStatement.setString(3,user.getEmail());
			prepareStatement.setString(4,user.getAddress());
			prepareStatement.setString(5,user.getRole());
			prepareStatement.setInt(6, user.getUserId());
			
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(int userId) {

		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(DELETE_USER_QUERY);
			prepareStatement.setInt(1, userId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUserNPassword(String username, String password) {
		String sql= "select * from `user` where `username` = ? and `password` =?";
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(sql);
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			
			res = prepareStatement.executeQuery();
			if(res.next()) {
				
				int userId1=res.getInt("userId");
				String username1=res.getString("username");
				String password1=res.getString("password");
				String email=res.getString("email");
				String address=res.getString("address");
				String role=res.getString("role");
				
				return new User(userId1,username1,password1,email,address,role);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public User getUser(int userId) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(GET_USER_QUERY);
			prepareStatement.setInt(1, userId);
			
			res = prepareStatement.executeQuery();
			if(res.next()) {
				
				int userId1=res.getInt("userId");
				String username=res.getString("username");
				String password=res.getString("password");
				String email=res.getString("email");
				String address=res.getString("address");
				String role=res.getString("role");
				
				return new User(userId1,username,password,email,address,role);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			Connection con = CP.createC();
			createStatement = con.createStatement();
			res = createStatement.executeQuery(GETALL_USER_QUERY);
			
			while(res.next()) {
				
				int userId1=res.getInt("userId");
				String username=res.getString("username");
				String password=res.getString("password");
				String email=res.getString("email");
				String address=res.getString("address");
				String role=res.getString("role");
				
				userList.add( new User(userId1,username,password,email,address,role));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	

}

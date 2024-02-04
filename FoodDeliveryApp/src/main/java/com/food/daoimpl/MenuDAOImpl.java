package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;
import com.food.model.User;

public class MenuDAOImpl implements MenuDAO {



	PreparedStatement prepareStatement =null;
	ResultSet res = null;
	private final String ADD_MENU_QUERY="insert into `menu`(`MenuID`,`RestaurantID`,`ItemName`,`Description`,`Price`,`IsAvailable`)"
			+ " values (?,?,?,?,?,?)";
	
	private final String UPDATE_MENU_QUERY="update `menu` set `RestaurantID`=?,`ItemName`=?,`Description`=?,`Price`=?,`IsAvailable`=?"
			+ " where  `MenuID`=?";
	
	private final String DELETE_MENU_QUERY="delete from `menu` where `MenuID`=?";
	
	private final String GET_MENU_QUERY= "Select `MenuID`,`RestaurantID`,`ItemName`,`Description`,`Price`,`IsAvailable`"
			+ " from `menu` where `MenuID`=?";
	private final String GETALL_MENU_QUERY= "Select `MenuID`,`RestaurantID`,`ItemName`,`Description`,`Price`,`IsAvailable` from `menu` where RestaurantID=? ";
	
	
	@Override
	public void addMenu(Menu menu) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(ADD_MENU_QUERY);
			prepareStatement.setInt(1, menu.getMenuId());
			prepareStatement.setInt(2,menu.getRestaurantId());
			prepareStatement.setString(3,menu.getItemName());
			prepareStatement.setString(4,menu.getDescription());
			prepareStatement.setDouble(5,menu.getPrice());
			prepareStatement.setBoolean(6,menu.getIsAvailable());
			
			
			prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateMenu(Menu menu) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(UPDATE_MENU_QUERY);
			prepareStatement.setInt(1,menu.getRestaurantId());
			prepareStatement.setString(2,menu.getItemName());
			prepareStatement.setString(3,menu.getDescription());
			prepareStatement.setDouble(4,menu.getPrice());
			prepareStatement.setBoolean(5,menu.getIsAvailable());
			prepareStatement.setInt(6, menu.getMenuId());
			
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMenu(int menuId) {

		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(DELETE_MENU_QUERY);
			prepareStatement.setInt(1, menuId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenu(int MenuID) {
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(GET_MENU_QUERY);
			prepareStatement.setInt(1, MenuID);
			
			res = prepareStatement.executeQuery();
			if(res.next()) {
				
				 int menuId1=res.getInt("MenuID");
				 int restaurantId=res.getInt("RestaurantID");
				 String itemName=res.getString("ItemName");
				 String description=res.getString("Description");
				 double price=res.getDouble("Price");
				 boolean isAvailable=res.getBoolean("IsAvailable");
				
				return new Menu(menuId1,restaurantId,itemName,description,price,isAvailable);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Menu> getAllMenus(int RestaurantID) {
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			Connection con = CP.createC();
			prepareStatement = con.prepareStatement(GETALL_MENU_QUERY);
			prepareStatement.setInt(1, RestaurantID);
			
			res = prepareStatement.executeQuery();
			
			while(res.next()) {
				
				int menuId1=res.getInt("MenuID");
				 int restaurantId=res.getInt("RestaurantID");
				 String itemName=res.getString("ItemName");
				 String description=res.getString("Description");
				 double price=res.getDouble("Price");
				 boolean isAvailable=res.getBoolean("IsAvailable");
				
				 menuList.add(new Menu(menuId1,restaurantId,itemName,description,price,isAvailable));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

}

package com.food.dao;

import java.util.List;

import com.food.model.Menu;

public interface MenuDAO {

	void addMenu(Menu menu);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	Menu getMenu(int RestaurantID);
	List<Menu> getAllMenus(int RestaurantID);
}

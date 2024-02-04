//UserID
//Username
//Password
//Email
//Address
//Role

package com.food.daoimpl;

import java.util.List;

import com.food.model.Restaurant;
import com.food.model.User;

public class Program1 {

	public static void main(String[] args) {
//		UserDAOImpl u = new UserDAOImpl();
		
//		User p1 = new User(2,"karan","123","karan@g.com","siwan","Customer");
//		u.addUser(p1);
		
//		u.updateUser(p1);
//		System.out.println(u.getAllUsers());
//		u.deleteUser(2);
		
		
		RestaurantDAOImpl r = new RestaurantDAOImpl();
		
//		Restaurant restaurant = new Restaurant(1,"Res1","Indian",10,"HSR layout",1,4,true);
//		Restaurant restaurant = new Restaurant(2,"Res1","Indian",10,"BTM layout",1,4,true);
//		Restaurant restaurant = new Restaurant(3,"Res3","Indian",10,"BTM layout",1,4,true);
		
//		r.addRestaurant(restaurant);
		
		List<Restaurant> all = r.getAllRestaurant();
		
		
		
		
		Restaurant rs = r.getRestaurant(2);
		rs.setName("Res2");
		
		r.updateRestaurant(rs);
		
		System.out.println(all);
		
		
//		System.out.println(rs);

	}

}

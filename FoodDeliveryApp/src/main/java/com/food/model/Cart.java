package com.food.model;
import java.util.HashMap;
import java.util.Map;

import com.food.dao.*;

public class Cart {
	HashMap<Integer,CartItem> items= null;
	


	public Cart() {
		items= new HashMap<Integer,CartItem>();
		
	}
	
	public void addItem(CartItem newItem) {
		int itemId= newItem.getItemId();
		
		if(items.containsKey(itemId)) {
			
			CartItem exItem = items.get(itemId);
			exItem.setQuantity(exItem.getQuantity()+newItem.getQuantity());
		}else {
			items.put(itemId, newItem);
		}
	}
	
	public void updateItem(int itemId,int quantity) {
		
		if(quantity<=0) {
			items.remove(itemId);
		}else {
		
			CartItem cartItem = items.get(itemId);
			cartItem.setQuantity(quantity);
			items.put(itemId, cartItem);
		}
	}
	
	public void removeItem(int itemId) {
		items.remove(itemId);
	}
	
	public HashMap<Integer,CartItem> getItem(){
		
		return items;
	}
	
	public void clear() {
		items.clear();
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + "]";
	}
	
	
	
	
}

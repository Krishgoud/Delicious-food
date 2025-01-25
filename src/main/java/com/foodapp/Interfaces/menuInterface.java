package com.foodapp.Interfaces;

import java.util.ArrayList;
import java.util.List;

import com.foodapp.model.Restaurant;
import com.foodapp.model.menu;

public interface menuInterface
{
	
	    int insert(menu menu); 
	    
	    ArrayList<menu> fetchAll(); 
	    
	    menu fetchOne(int menuID); 
	    
	    List<menu> fetchmenubyRestaurantID(int RestaurantID); 
	    List<menu> searchFoodItemsByName(String name);
	     List<menu> searchFoodItemsByRestaurantAndName(int restaurantId, String name);



	    
	    int update(int menuID, String name, String description, int price, String isAvailable, String imagepath); 
	    
	    int delete(int menuID); 
	}



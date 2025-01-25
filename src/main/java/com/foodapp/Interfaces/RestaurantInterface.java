package com.foodapp.Interfaces;

import java.util.List;
import com.foodapp.model.Restaurant;

public interface RestaurantInterface {

    // Method to insert a new restaurant record
    int insert(Restaurant restaurant);

    // Method to fetch all restaurants
    List<Restaurant> fetchAll();

    // Method to fetch a restaurant by ID
    Restaurant fetchOne(int id);

    // Method to delete a restaurant by ID
    int delete(int id);
    
    // Method to fetch all restaurants (alternative method if needed)
    List<Restaurant> getAllRestaurants();

 

	int update(int id, String name, String cuisineType, int deliveryTime, String address, double ratings,
			String isActive, byte[] imageBytes, String description);

	List<Restaurant> searchRestaurantsByName(String name);
	String getRestaurantNameById(int restaurantId);
}

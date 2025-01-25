package com.foodapp.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.foodapp.daoimpl.RestaurantDaoImpl;
import com.foodapp.model.Restaurant;

public class RestaurantLanch {

	public static void main(String[] args)
	{
		 Scanner sc = new Scanner(System.in);

	        // Establish connection
	        try {
				RestaurantDaoImpl.connect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        RestaurantDaoImpl rdao = new RestaurantDaoImpl();

	        // Menu for operations
	        System.out.println("Welcome to the Restaurant Management App\nEnter your choice:\n"
	                + "1. Insert Restaurant\n"
	                + "2. View All Restaurants\n"
	                + "3. View Specific Restaurant\n"
	                + "4. Update Restaurant\n"
	                + "5. Delete Restaurant");

	        int choice = sc.nextInt();
	        sc.nextLine(); // Consume newline

	        switch (choice) {
	            case 1:
	                System.out.println("Enter Restaurant Details:");

	                System.out.print("Enter Restaurant ID: ");
	                int id = sc.nextInt();
	                sc.nextLine(); // Consume newline

	                System.out.print("Enter Name: ");
	                String name = sc.nextLine();

	                System.out.print("Enter Cuisine Type: ");
	                String cuisineType = sc.nextLine();

	                System.out.print("Enter Delivery Time (in minutes): ");
	                int deliveryTime = sc.nextInt();
	                sc.nextLine(); // Consume newline

	                System.out.print("Enter Address: ");
	                String address = sc.nextLine();

	                System.out.print("Enter Ratings: ");
	                double ratings = sc.nextDouble();
	                sc.nextLine(); // Consume newline

	                System.out.print("Is the restaurant active? (yes/no): ");
	                String isActive = sc.nextLine();

	                System.out.print("Enter Image Path: ");
	                String imagePath = sc.nextLine();

	                Restaurant newRestaurant = new Restaurant(id, name, cuisineType, deliveryTime, address, ratings, isActive, imagePath);
	                System.out.println(rdao.insert(newRestaurant) == 1 ? "Restaurant inserted successfully." : "Failed to insert restaurant.");
	                break;

	            case 2:
	                System.out.println("List of all restaurants:");
	                ArrayList<Restaurant> restaurantList = rdao.fetchAll();
	                for (Restaurant r : restaurantList) {
	                    System.out.println(r);
	                }
	                break;

	            case 3:
	                System.out.print("Enter Restaurant ID to view: ");
	                id = sc.nextInt();
	                Restaurant fetchedRestaurant = rdao.fetchOne(id);
	                if (fetchedRestaurant != null) {
	                    System.out.println("Restaurant Details: " + fetchedRestaurant);
	                } else {
	                    System.out.println("Restaurant not found.");
	                }
	                break;

	            case 4: // Update case
	                System.out.print("Enter Restaurant ID to update: ");
	                id = sc.nextInt();
	                sc.nextLine(); // Consume newline

	                System.out.print("Enter New Name: ");
	                name = sc.nextLine();

	                System.out.print("Enter New Cuisine Type: ");
	                cuisineType = sc.nextLine();

	                System.out.print("Enter New Delivery Time (in minutes): ");
	                deliveryTime = sc.nextInt();
	                sc.nextLine(); // Consume newline

	                System.out.print("Enter New Address: ");
	                address = sc.nextLine();

	                System.out.print("Enter New Ratings: ");
	                ratings = sc.nextDouble();
	                sc.nextLine(); // Consume newline

	                System.out.print("Is the restaurant active? (Yes/No): ");
	                isActive = sc.nextLine();
	                sc.nextLine(); // Consume newline

	                System.out.print("Enter New Image Path: ");
	                imagePath = sc.nextLine();

	                int updateResult = rdao.update(id, name, cuisineType, deliveryTime, address, ratings, isActive, imagePath);
	                System.out.println(updateResult == 1 ? "Restaurant updated successfully." : "Failed to update restaurant.");
	                break;

	            case 5:
	                System.out.print("Enter Restaurant ID to delete: ");
	                id = sc.nextInt();
	                int deleteResult = rdao.delete(id);
	                System.out.println(deleteResult == 1 ? "Restaurant deleted successfully." : "Failed to delete restaurant.");
	                break;

	            default:
	                System.out.println("Invalid choice! Please enter a valid option.");
	                break;
	        }

	      
	}
}

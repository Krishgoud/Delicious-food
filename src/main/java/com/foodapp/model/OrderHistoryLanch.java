package com.foodapp.model;

import java.util.ArrayList;
import java.util.Scanner;

import com.foodapp.daoimpl.OrderHistoryDaiImpl;
import com.foodapp.main.OrderHistory;

public class OrderHistoryLanch {

	public static void main(String[] args) 
	{
				Scanner sc = new Scanner(System.in);
		        OrderHistoryDaiImpl.connect();

		        OrderHistoryDaiImpl orderHistoryDao = new OrderHistoryDaiImpl();

		        // Menu and user choice
		        System.out.println("Welcome to the OrderHistory Management App\nEnter your choice:\n"
		                + "1. Insert OrderHistory\n"
		                + "2. View All OrderHistories\n"
		                + "3. View Specific OrderHistory\n"
		                + "4. Update OrderHistory\n"
		                + "5. Delete OrderHistory");

		        int choice = sc.nextInt();
		        sc.nextLine(); // Consume newline

		        switch (choice) {
		            case 1: // Insert OrderHistory
		                System.out.println("Enter OrderHistory Details:");

		                System.out.print("Enter OrderHistory ID: ");
		                int orderHistoryID = sc.nextInt();
		                sc.nextLine(); // Consume newline
		                
		                System.out.print("Enter Order ID: ");
		                int orderID = sc.nextInt();
		                sc.nextLine(); 

		                System.out.print("Enter User ID: ");
		                int userID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		    
		                System.out.print("Enter Status: ");
		                String status = sc.nextLine();
		                
		                System.out.print("Enter Total Amount: ");
		                double totalAmount = sc.nextDouble();

		         
		                // Create the OrderHistory object
		                OrderHistory newOrderHistory = new OrderHistory(orderHistoryID, orderID, userID, status ,totalAmount);

		                // Insert the order history
		                try {
		                    int insertResult = orderHistoryDao.insert(newOrderHistory);
		                    System.out.println(insertResult == 1 ? "OrderHistory inserted successfully."
		                            : "Failed to insert OrderHistory.");
		                } catch (Exception e) {
		                    System.out.println("Error inserting OrderHistory: " + e.getMessage());
		                }
		                break;

		            case 2: // View All OrderHistories
		                System.out.println("List of all OrderHistories:");
		                try {
		                    ArrayList<OrderHistory> orderHistoryList = orderHistoryDao.fetchAll();
		                    if (orderHistoryList.isEmpty()) {
		                        System.out.println("No OrderHistories found.");
		                    } else {
		                        for (OrderHistory orderHistory : orderHistoryList) {
		                            System.out.println(orderHistory);
		                        }
		                    }
		                } catch (Exception e) {
		                    System.out.println("Error fetching OrderHistories: " + e.getMessage());
		                }
		                break;

		            case 3: // View Specific OrderHistory
		                System.out.print("Enter OrderHistory ID to view: ");
		                orderHistoryID = sc.nextInt();
		                try {
		                    OrderHistory fetchedOrderHistory = orderHistoryDao.fetchOne(orderHistoryID);
		                    if (fetchedOrderHistory != null) {
		                        System.out.println("OrderHistory Details: " + fetchedOrderHistory);
		                    } else {
		                        System.out.println("OrderHistory not found.");
		                    }
		                } catch (Exception e) {
		                    System.out.println("Error fetching OrderHistory: " + e.getMessage());
		                }
		                break;

		            case 4: // Update OrderHistory
		                System.out.print("Enter OrderHistory ID to update: ");
		                orderHistoryID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                System.out.print("Enter New Status: ");
		                status = sc.nextLine();
		                
		                System.out.print("Enter Total Amount: ");
		                double totalAmount1 = sc.nextDouble();
		                
		                

		                // Update the OrderHistory
		                try {
		                    int updateResult = orderHistoryDao.update(orderHistoryID,status, totalAmount1
		                    		);
		                    System.out.println(updateResult == 1 ? "OrderHistory updated successfully."
		                            : "Failed to update OrderHistory.");
		                } catch (Exception e) {
		                    System.out.println("Error updating OrderHistory: " + e.getMessage());
		                }
		                break;

		            case 5: // Delete OrderHistory
		                System.out.print("Enter OrderHistory ID to delete: ");
		                orderHistoryID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                // Delete the OrderHistory
		                try {
		                    int deleteResult = orderHistoryDao.delete(orderHistoryID);
		                    System.out.println(deleteResult == 1 ? "OrderHistory deleted successfully."
		                            : "Failed to delete OrderHistory.");
		                } catch (Exception e) {
		                    System.out.println("Error deleting OrderHistory: " + e.getMessage());
		                }
		                break;

		            default:
		                System.out.println("Invalid choice! Please enter a valid option.");
		                break;
		        }
			}
		


		
}

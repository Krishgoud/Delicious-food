package com.foodapp.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.foodapp.daoimpl.OrderDaoImpl;
import com.foodapp.model.Order;

public class OrderLanch {

	public static void main(String[] args)
	{
		
		        Scanner sc = new Scanner(System.in);

		        // Establish connection
		        OrderDaoImpl.connect();

		        OrderDaoImpl orderDao = new OrderDaoImpl();

		        // Menu for operations
		        System.out.println("Welcome to the Order Management App\nEnter your choice:\n"
		                + "1. Insert Order\n"
		                + "2. View All Orders\n"
		                + "3. View Specific Order\n"
		                + "4. Update Order\n"
		                + "5. Delete Order");

		        int choice = sc.nextInt();
		        sc.nextLine(); // Consume newline

		        switch (choice) {
		            case 1:
		                System.out.println("Enter Order Details:");

		                System.out.print("Enter Order ID: ");
		                int orderID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                System.out.print("Enter User ID: ");
		                int userID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                System.out.print("Enter Restaurant ID: ");
		                int restaurantID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                System.out.print("Enter Total Amount: ");
		                int totalAmount = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                System.out.print("Enter Status: ");
		                String status = sc.nextLine();

		                System.out.print("Enter Payment Mode: ");
		                String paymentMode = sc.nextLine();

		                // Create the Order object
		                Order newOrder = new Order(orderID, userID, restaurantID, totalAmount, status, paymentMode);
		                // Insert the order
		                System.out.println(orderDao.insert(newOrder) == 1 ? "Order inserted successfully." : "Failed to insert order.");
		                break;

		            case 2:
		                System.out.println("List of all orders:");
		                ArrayList<Order> orderList = orderDao.fetchAll();
		                for (Order order : orderList) {
		                    System.out.println(order);
		                }
		                break;

		            case 3:
		                System.out.print("Enter Order ID to view: ");
		                orderID = sc.nextInt();
		                Order fetchedOrder = orderDao.fetchOne(orderID);
		                if (fetchedOrder != null) {
		                    System.out.println("Order Details: " + fetchedOrder);
		                } else {
		                    System.out.println("Order not found.");
		                }
		                break;

		            case 4: // Update case
		                System.out.print("Enter Order ID to update: ");
		                orderID = sc.nextInt();
		                sc.nextLine(); // Consume newline

		                System.out.print("Enter New Status: ");
		                status = sc.nextLine();

		                System.out.print("Enter New Payment Mode: ");
		                paymentMode = sc.nextLine();

		                int updateResult = orderDao.update(orderID, status, paymentMode);
		                System.out.println(updateResult == 1 ? "Order updated successfully." : "Failed to update order.");
		                break;

		            case 5:
		                System.out.print("Enter Order ID to delete: ");
		                orderID = sc.nextInt();

		                int deleteResult = orderDao.delete(orderID);
		                System.out.println(deleteResult == 1 ? "Order deleted successfully." : "Failed to delete order.");
		                break;

		            default:
		                System.out.println("Invalid choice! Please enter a valid option.");
		                break;
		        }

		        sc.close(); // Close the scanner
		    }
		


	}



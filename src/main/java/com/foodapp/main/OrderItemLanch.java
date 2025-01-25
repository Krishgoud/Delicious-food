package com.foodapp.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.foodapp.daoimpl.OrderDaoImpl;
import com.foodapp.daoimpl.OrderItemDaoImpl;
import com.foodapp.model.OrderItem;
 

public class OrderItemLanch
{
	public static void main(String[] args) 
	{
	Scanner sc = new Scanner(System.in);

  OrderItemDaoImpl.connect();

    OrderItemDaoImpl orderDao = new OrderItemDaoImpl();
 // Menu and user choice
    System.out.println("Welcome to the OrderItem Management App\nEnter your choice:\n"
            + "1. Insert OrderItem\n"
            + "2. View All OrderItems\n"
            + "3. View Specific OrderItem\n"
            + "4. Update OrderItem\n"
            + "5. Delete OrderItem");

    int choice = sc.nextInt();
    sc.nextLine(); // Consume newline

    switch (choice) {
        case 1: // Insert OrderItem
            System.out.println("Enter OrderItem Details:");
            
            System.out.print("Enter OrderItem ID: ");
            int orderItemID = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter Order ID: ");
            int orderID = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter Menu ID: ");
            int menuID = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter Item Total: ");
            int itemTotal = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Create the OrderItem object
            OrderItem newOrderItem = new OrderItem(orderItemID, orderID, menuID, quantity, itemTotal);
            
            // Insert the order item
            try {
                int insertResult = orderDao.insert(newOrderItem);
                System.out.println(insertResult == 1 ? "OrderItem inserted successfully." : "Failed to insert OrderItem.");
            } catch (Exception e) {
                System.out.println("Error inserting OrderItem: " + e.getMessage());
            }
            break;

        case 2: // View All OrderItems
            System.out.println("List of all OrderItems:");
            try {
                ArrayList<OrderItem> orderItemList = orderDao.fetchAll();
                if (orderItemList.isEmpty()) {
                    System.out.println("No OrderItems found.");
                } else {
                    for (OrderItem orderItem : orderItemList) {
                        System.out.println(orderItem);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching OrderItems: " + e.getMessage());
            }
            break;

        case 3: // View Specific OrderItem
            System.out.print("Enter OrderItem ID to view: ");
            orderItemID = sc.nextInt();
            try {
                OrderItem fetchedOrderItem = orderDao.fetchOne(orderItemID);
                if (fetchedOrderItem != null) {
                    System.out.println("OrderItem Details: " + fetchedOrderItem);
                } else {
                    System.out.println("OrderItem not found.");
                }
            } catch (Exception e) {
                System.out.println("Error fetching OrderItem: " + e.getMessage());
            }
            break;

        case 4: // Update OrderItem
            System.out.print("Enter OrderItem ID to update: ");
            orderItemID = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter New Quantity: ");
            quantity = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            System.out.print("Enter New Item Total: ");
            itemTotal = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Update the OrderItem
            try {
                int updateResult = orderDao.update(orderItemID, quantity, itemTotal);
                System.out.println(updateResult == 1 ? "OrderItem updated successfully." : "Failed to update OrderItem.");
            } catch (Exception e) {
                System.out.println("Error updating OrderItem: " + e.getMessage());
            }
            break;

        case 5: // Delete OrderItem
            System.out.print("Enter OrderItem ID to delete: ");
            orderItemID = sc.nextInt();
            sc.nextLine(); // Consume newline

            // Delete the OrderItem
            try {
                int deleteResult = orderDao.delete(orderItemID);
                System.out.println(deleteResult == 1 ? "OrderItem deleted successfully." : "Failed to delete OrderItem.");
            } catch (Exception e) {
                System.out.println("Error deleting OrderItem: " + e.getMessage());
            }
            break;

        default:
            System.out.println("Invalid choice! Please enter a valid option.");
            break;
    }

    sc.close(); // Close the scanner1
    
   
          
    

}
}


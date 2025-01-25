package com.foodapp.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.foodapp.daoimpl.MenuDaoImpl;
import com.foodapp.model.menu;

public class MenuLanch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Establish connection
        MenuDaoImpl.connect();

        MenuDaoImpl mdao = new MenuDaoImpl();

        // Menu for operations
        System.out.println("Welcome to the Menu Management App\nEnter your choice:\n"
                + "1. Insert Menu\n"
                + "2. View All Menus\n"
                + "3. View Specific Menu\n"
                + "4. Update Menu\n"
                + "5. Delete Menu\n"
                + "6. View Menus by Restaurant ID");

        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Enter Menu Details:");

                System.out.print("Enter Menu ID: ");
                int menuID = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("Enter Restaurant ID: ");
                int restaurantID = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Description: ");
                String description = sc.nextLine();

                System.out.print("Enter Price: ");
                int price = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("isAvailable? (yes/no): ");
                String isAvailable = sc.nextLine();

                System.out.print("Enter Image Path: ");
                String imagePath = sc.nextLine();

                menu newMenu = new menu(menuID, restaurantID, name, description, price, isAvailable, imagePath, menuID);
                System.out.println(mdao.insert(newMenu) == 1 ? "Menu inserted successfully." : "Failed to insert menu.");
                break;

            case 2:
                System.out.println("List of all menus:");
                ArrayList<menu> menuList = mdao.fetchAll();
                for (menu m : menuList) {
                    System.out.println(m);
                }
                break;

            case 3:
                System.out.print("Enter Menu ID to view: ");
                menuID = sc.nextInt();
                menu fetchedMenu = mdao.fetchOne(menuID);
                if (fetchedMenu != null) {
                    System.out.println("Menu Details: " + fetchedMenu);
                } else {
                    System.out.println("Menu not found.");
                }
                break;

            case 4: // Update case
                System.out.print("Enter Menu ID to update: ");
                menuID = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("Enter New Name: ");
                name = sc.nextLine();

                System.out.print("Enter New Description: ");
                description = sc.nextLine();

                System.out.print("Enter New Price: ");
                price = sc.nextInt();
                sc.nextLine(); // Consume newline

                System.out.print("Is Available? (yes/no): ");
                isAvailable = sc.nextLine();

                System.out.print("Enter New Image Path: ");
                imagePath = sc.nextLine();

                int updateResult = mdao.update(menuID, name, description, price, isAvailable, imagePath);
                System.out.println(updateResult == 1 ? "Menu updated successfully." : "Failed to update menu.");
                break;

            case 5:
                System.out.print("Enter Menu ID to delete: ");
                menuID = sc.nextInt();

                int deleteResult = mdao.delete(menuID);
                System.out.println(deleteResult == 1 ? "Menu deleted successfully." : "Failed to delete menu.");
                break;

            case 6: // Fetch menus by restaurant ID
                System.out.print("Enter Restaurant ID to fetch menus: ");
                int fetchRestaurantID = sc.nextInt();

                ArrayList<menu> restaurantMenuList = (ArrayList<menu>) mdao.fetchmenubyRestaurantID(fetchRestaurantID);
                if (!restaurantMenuList.isEmpty()) {
                    System.out.println("Menus for Restaurant ID " + fetchRestaurantID + ":");
                    for (menu m : restaurantMenuList) {
                        System.out.println(m);
                    }
                } else {
                    System.out.println("No menus found for this restaurant ID.");
                }
                break;

            default:
                System.out.println("Invalid choice! Please enter a valid option.");
                break;
        }
    }
}

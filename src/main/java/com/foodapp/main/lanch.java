package com.foodapp.main;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Scanner;

import com.foodapp.daoimpl.UserDaoImpl;
import com.foodapp.model.User;

public class lanch {

	public static void main(String[] args) throws SQLException {
	
		 Scanner sc = new Scanner(System.in);

		       // UserDaoImpl.connect();

		        UserDaoImpl udao = new UserDaoImpl();

		        System.out.println("Welcome to the User Management App\nEnter your choice:\n"
		                + "1. Insert User\n"
		                + "2. View User List\n"
		                + "3. View Specific User\n"
		                + "4. Update User\n"
		                + "5. Delete User"
		                );

		        int choice = sc.nextInt();
		        sc.nextLine();  

		        switch (choice) {
		            case 1:
		                System.out.println("Enter User Details:");

		                System.out.print("Enter ID: ");
		                int id = sc.nextInt();
		                sc.nextLine();  // Consume newline

		                System.out.print("Enter Name: ");
		                String name = sc.nextLine();

		                System.out.print("Enter Password: ");
		                String password = sc.nextLine();

		                System.out.print("Enter Email: ");
		                String email = sc.nextLine();

		                System.out.print("Enter Address: ");
		                String address = sc.nextLine();

		                User user = new User( name, password, email, address);
		                System.out.println(udao.insert(new User( name, password, email, address)) == 1 ? "User inserted successfully." : "Failed to insert user.");

		                break;

		            case 2:
		                System.out.println("List of all users:");
		                ArrayList<User> userList = udao.fetchAll();
		                for (User u : userList) {
		                    System.out.println(u);
		                }
		                break;

		            case 3:
		                System.out.print("Enter User ID to view: ");
		                id = sc.nextInt();
		                User fetchedUser = udao.fetchOne(id);
		                if (fetchedUser != null) {
		                    System.out.println("User Details: " + fetchedUser);
		                } else {
		                    System.out.println("User not found.");
		                }
		                break;

		            case 4:
		                System.out.print("Enter User ID to update: ");
		                id = sc.nextInt();
		                sc.nextLine();  
		                System.out.print("Enter New Name: ");
		                name = sc.nextLine();

		                System.out.print("Enter New Password: ");
		                password = sc.nextLine();

		                System.out.print("Enter New Email: ");
		                email = sc.nextLine();

		                System.out.print("Enter New Address: ");
		                address = sc.nextLine();

		                int result = udao.update(id, name, password, email, address);
		                System.out.println(result == 1 ? "User updated successfully." : "Failed to update user.");
		                break;

		            case 5:
		                System.out.print("Enter User ID to delete: ");
		                id = sc.nextInt();
		                result = udao.delete(id);
		                System.out.println(result == 1 ? "User deleted successfully." : "Failed to delete user.");
		                break;

		            default:
		                System.out.println("Invalid choice! Please enter a valid option.");
		                break;
		        }

		        sc.close(); 
		    }

	}



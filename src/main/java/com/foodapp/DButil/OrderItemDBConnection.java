package com.foodapp.DButil;

import java.sql.Connection;
import java.sql.DriverManager;

public class OrderItemDBConnection
{
	private static Connection con;

	public static Connection connect() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodapp","root","root");
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
		return con;
	
		
	}
}

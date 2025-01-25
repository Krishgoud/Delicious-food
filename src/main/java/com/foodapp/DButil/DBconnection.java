package com.foodapp.DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection 
{ 
	private static final String url ="jdbc:mysql://localhost:3306/foodapp";
	private static final String dbUn ="root";
	private static final String dbPwd ="root";

	private static Connection con;

	public static Connection connect() 
	throws SQLException
	{
	
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, dbUn , dbPwd);
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
		return con;
	
		
	}

}

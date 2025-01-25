package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.DButil.DBconnection;
import com.foodapp.Interfaces.menuInterface;
import com.foodapp.model.menu;

public class MenuDaoImpl implements menuInterface {

    private static Connection con;
    private PreparedStatement pstmt;

    // SQL Queries
    private static final String INSERTQUERY = "INSERT INTO menu (MenuID, RestaurantID, Name, Description, Price, IsAvailable, Imagepath, Ratings) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FETCHALL = "SELECT * FROM menu";
    private static final String FETCHONE = "SELECT * FROM menu WHERE MenuID = ?";
    private static final String UPDATE = "UPDATE menu SET Name = ?, Description = ?, Price = ?, IsAvailable = ?, Imagepath = ?, Ratings = ? WHERE MenuID = ?";
    private static final String DELETE = "DELETE FROM menu WHERE MenuID = ?";
    private static final String FETCHRESBY = "SELECT * FROM menu WHERE RestaurantID = ?";
    private static final String SEARCHBYNAME = "SELECT * FROM menu WHERE Name LIKE ?";



    // Static block to initialize connection
    static {
        try {
			connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Call connect method to initialize the connection
    }

    public MenuDaoImpl(Connection connect) {
		// TODO Auto-generated constructor stub
	}

	public MenuDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	// Establish database connection
    public static void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DBconnection.connect();
        }
    }

    // Close database connection
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(menu menu) {
        try {
            pstmt = con.prepareStatement(INSERTQUERY);
            pstmt.setInt(1, menu.getMenuID());
            pstmt.setInt(2, menu.getRestaurantID());
            pstmt.setString(3, menu.getName());
            pstmt.setString(4, menu.getDescription());
            pstmt.setInt(5, menu.getPrice());
            pstmt.setString(6, menu.getIsAvailable());
            pstmt.setString(7, menu.getImagepath());
            pstmt.setDouble(8, menu.getRatings());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pstmt, null);
        }
    }

    @Override
    public ArrayList<menu> fetchAll() {
        ArrayList<menu> menuList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(FETCHALL);

            while (rs.next()) {
                menuList.add(new menu(
                        rs.getInt("MenuID"),
                        rs.getInt("RestaurantID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getInt("Price"),
                        rs.getString("IsAvailable"),
                        rs.getString("Imagepath"),
                        rs.getDouble("Ratings")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(stmt, null, rs);
        }
        return menuList;
    }

    @Override
    public menu fetchOne(int menuID) {
    	menu Menu = null;
        try {
            pstmt = con.prepareStatement(FETCHONE);
            pstmt.setInt(1, menuID);
           ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Menu = new menu(
                        rs.getInt("MenuID"),
                        rs.getInt("RestaurantID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getInt("Price"),
                        rs.getString("IsAvailable"),
                        rs.getString("Imagepath"),
                        rs.getDouble("Ratings")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return Menu;
    }

    @Override
    public int update(int menuID, String name, String description, int price, String isAvailable, String imagepath) {
        try {
            pstmt = con.prepareStatement(UPDATE);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, price);
            pstmt.setString(4, isAvailable);
            pstmt.setString(5, imagepath);
            pstmt.setInt(6, menuID);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pstmt, null);
        }
    }

    @Override
    public int delete(int menuID) {
        try {
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, menuID);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(null, pstmt, null);
        }
    }
    

    @Override
    public List<menu> fetchmenubyRestaurantID(int RestaurantID) {
        List<menu> menuList = new ArrayList<>();
        ResultSet rs = null;
        

        try {
        	System.out.println("Executing query for restaurant ID: " + RestaurantID);

            pstmt = con.prepareStatement(FETCHRESBY);
            pstmt.setInt(1, RestaurantID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                menu Menu = new menu();
                       Menu.setMenuID(rs.getInt("MenuID"));
                        Menu.setName(rs.getString("Name"));
                        Menu.setPrice(rs.getInt("Price"));
                        Menu.setDescription(rs.getString("Description"));
                        Menu.setImagepath(rs.getString("Imagepath"));
                        Menu.setRestaurantID(rs.getInt("RestaurantID"));
                        menuList.add(Menu);
                   
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
            closeResources(null, pstmt, rs);
        }
        return menuList;
    }

    // New method to search food items by name
    public List<menu> searchFoodItemsByName(String name) {
        List<menu> menuList = new ArrayList<>();
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(SEARCHBYNAME);
            pstmt.setString(1, "%" + name + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                menuList.add(new menu(
                        rs.getInt("MenuID"),
                        rs.getInt("RestaurantID"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getInt("Price"),
                        rs.getString("IsAvailable"),
                        rs.getString("Imagepath"),
                        rs.getDouble("Ratings")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(null, pstmt, rs);
        }
        return menuList;
    }
 


    // Helper method to close resources
    private void closeResources(Statement stmt, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (pstmt != null) pstmt.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<menu> searchFoodItemsByRestaurantAndName(int restaurantId, String name) {
	    String SEARCHBYNAME = "SELECT * FROM menu WHERE restaurantid = ? AND name LIKE ?";
	  
	    List<menu> menuList = new ArrayList<>();
	    ResultSet rs = null;

	    try {
	        pstmt = con.prepareStatement(SEARCHBYNAME);
	        
	        // Set the restaurant ID parameter
	        pstmt.setInt(1, restaurantId);  
	        
	        // Set the search query for the name parameter
	        pstmt.setString(2, "%" + name + "%");  
	        
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            menuList.add(new menu(
	                    rs.getInt("MenuID"),
	                    rs.getInt("RestaurantID"),
	                    rs.getString("Name"),
	                    rs.getString("Description"),
	                    rs.getInt("Price"),
	                    rs.getString("IsAvailable"),
	                    rs.getString("Imagepath"),
	                    rs.getDouble("Ratings")
	            ));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        closeResources(null, pstmt, rs);
	    }
	    return menuList;
	}

  
    }


	


    


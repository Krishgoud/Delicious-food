package com.foodapp.daoimpl;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.DButil.DBconnection;
import com.foodapp.Interfaces.RestaurantInterface;
import com.foodapp.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantInterface {
    private static final String INSERTQUERY = "INSERT INTO restaurant (RestaurantID, Name, CuisineType, DeliveryTime, Address, Ratings, isActive, Imagepath, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FETCHALL = "SELECT * FROM restaurant";
    private static final String FETCHONE = "SELECT * FROM restaurant WHERE RestaurantID = ?";
    private static final String UPDATE = "UPDATE restaurant SET Name = ?, CuisineType = ?, DeliveryTime = ?, Address = ?, Ratings = ?, isActive = ?, Imagepath = ?, Description = ? WHERE RestaurantID = ?";
    private static final String DELETE = "DELETE FROM restaurant WHERE RestaurantID = ?";
    private static final String query = "SELECT * FROM restaurants WHERE name LIKE ? OR cuisine_type LIKE ?";



    private Connection con;

    // Establish database connection
    public void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            this.con = DBconnection.connect();
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
    public int insert(Restaurant restaurant) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERTQUERY)) {
            pstmt.setInt(1, restaurant.getRestaurantID());
            pstmt.setString(2, restaurant.getName());
            pstmt.setString(3, restaurant.getCuisineType());
            pstmt.setInt(4, restaurant.getDeliveryTime());
            pstmt.setString(5, restaurant.getAddress());
            pstmt.setDouble(6, restaurant.getRatings());
            pstmt.setString(7, restaurant.getIsActive());
            pstmt.setString(8, restaurant.getImagepath());
            pstmt.setString(9, restaurant.getDescription());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Restaurant> fetchAll() {
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            // Assuming con is your database connection
            if (con == null) {
                throw new SQLException("Database connection is null");
            }
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(FETCHALL);

            while (rs.next()) {
                Restaurant restaurant = mapResultSetToRestaurant(rs);
                restaurantList.add(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }



    @Override
    public Restaurant fetchOne( int restaurantID) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCHONE)) {
            pstmt.setInt(1,   restaurantID);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToRestaurant(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(int id, String name, String cuisineType, int deliveryTime, String address, double ratings, String isActive, byte[] imageBytes, String description) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
            pstmt.setString(1, name);
            pstmt.setString(2, cuisineType);
            pstmt.setInt(3, deliveryTime);
            pstmt.setString(4, address);
            pstmt.setDouble(5, ratings);
            pstmt.setString(6, isActive);
            pstmt.setString(7, description);

            if (imageBytes != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                pstmt.setBinaryStream(8, bais, imageBytes.length);
            } else {
                pstmt.setNull(8, Types.BLOB);
            }

            pstmt.setInt(9, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Helper method to map ResultSet to Restaurant object
    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
        return new Restaurant(
                resultSet.getInt("RestaurantID"),
            resultSet.getInt("RestaurantID"),
            resultSet.getString("Name"),
            resultSet.getString("CuisineType"),
            resultSet.getInt("DeliveryTime"),
            resultSet.getString("Address"),
            resultSet.getDouble("Ratings"),
            resultSet.getString("isActive"),
            resultSet.getString("Imagepath"),
            resultSet.getString("Description")
        );
    }

	@Override
	public List<Restaurant> getAllRestaurants() {
	    List<Restaurant> restaurantList = new ArrayList<>();
	    try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(FETCHALL)) {
	        while (resultSet.next()) {
	            restaurantList.add(mapResultSetToRestaurant(resultSet));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return restaurantList;
	}
	


	@Override
	public List<Restaurant> searchRestaurantsByName(String name) {
	     List<Restaurant> restaurants = new ArrayList<>();	        
	      try (PreparedStatement stmt = con.prepareStatement(query)) {
	            stmt.setString(1, "%" + name.toLowerCase() + "%");

	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
		            restaurants.add(mapResultSetToRestaurant(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		    return restaurants;

	    }
	@Override
	public String getRestaurantNameById(int restaurantId) {
	    String restaurantName = null;
	    String query = "SELECT name FROM restaurants WHERE id = ?";  // Adjust table name and column names as per your schema

	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, restaurantId);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            restaurantName = rs.getString("name");  // Assuming the column name is 'name'
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return restaurantName;  // Will return null if no restaurant is found with the given ID
	}
	public boolean addFavorite(int userId, int restaurantId) {
	    String query = "INSERT INTO favorites (UserId, RestaurantID) VALUES (?, ?)";
	    try (PreparedStatement stmt = con.prepareStatement(query)) {
	        stmt.setInt(1, userId);
	        stmt.setInt(2, restaurantId);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}

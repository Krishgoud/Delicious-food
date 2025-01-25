package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.DButil.DBconnection;
import com.foodapp.Interfaces.userInterface;
import com.foodapp.model.Restaurant;
import com.foodapp.model.User;

public class UserDaoImpl implements userInterface {

    private static final String INSERTQUERY = "INSERT INTO user (UserName, Password, Email, Address) VALUES (?, ?, ?, ?)";
    private static final String FETCHALL = "SELECT * FROM user";
    private static final String FETCHONE = "SELECT * FROM user WHERE UserId = ?";
    private static final String UPDATE = "UPDATE user SET UserName = ?, Password = ?, Email = ?, Address = ? WHERE UserId = ?";
    private static final String DELETE = "DELETE FROM user WHERE UserId = ?";
    private static final String FETCH_FAVORITES = "SELECT r.* FROM favorites f JOIN restaurant r ON f.restaurant_id = r.RestaurantID WHERE f.user_id = ?";

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
    public int insert(User user) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERTQUERY)) {
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getAddress());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<User> fetchAll() {
        ArrayList<User> userList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(FETCHALL)) {
            while (resultSet.next()) {
                userList.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User fetchOne(int userId) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCHONE)) {
            pstmt.setInt(1, userId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int update(int userId, String userName, String password, String email, String address) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setInt(5, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int userId) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addFavorite(int userId, int restaurantId) {
        String query = "INSERT INTO favorites (user_id, restaurant_id) VALUES (?, ?)";
        try (Connection con = DBconnection.connect();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, restaurantId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Returns true if insert is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false in case of any error
    }

    public List<Restaurant> getFavoritesByUserId(int userId) {
        List<Restaurant> favorites = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_FAVORITES)) {
            pstmt.setInt(1, userId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    favorites.add(mapResultSetToRestaurant(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favorites;
    }
    public boolean saveUser(User user) {
        boolean isSaved = false;
        String sql = "INSERT INTO user (username, password, email, address) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBconnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword()); // Encrypted password
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                isSaved = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();  // For debugging, log the error in production
        }
        return isSaved;
    }


    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getInt("UserId"),
                resultSet.getString("UserName"),
                resultSet.getString("Password"),
                resultSet.getString("Email"),
                resultSet.getString("Address")
        );
    }

    // Helper method to map ResultSet to Restaurant object
    private Restaurant mapResultSetToRestaurant(ResultSet resultSet) throws SQLException {
        return new Restaurant(
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
    
}

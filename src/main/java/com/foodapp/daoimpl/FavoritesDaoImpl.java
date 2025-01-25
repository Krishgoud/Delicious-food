package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.DButil.DBconnection;
import com.foodapp.model.Favorite;
import com.foodapp.model.Restaurant;
import com.foodapp.model.menu;

public class FavoritesDaoImpl {

    public Restaurant getRestaurantById(int restaurantId) {
        try (Connection conn = DBconnection.connect()) {
            String query = "SELECT * FROM restaurant WHERE RestaurantID = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantID(resultSet.getInt("RestaurantID"));
                restaurant.setName(resultSet.getString("Name"));
                restaurant.setAddress(resultSet.getString("Address"));
                restaurant.setRatings(resultSet.getInt("Ratings"));
                return restaurant;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addFavorite(Favorite favorite) {
        try (Connection conn = DBconnection.connect()) {
            String query = "INSERT INTO favorites (username, restaurantid) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, favorite.getUsername());
            statement.setInt(2, favorite.getRestaurantId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Restaurant> getFavoritesByUsername(String username) {
        List<Restaurant> favoriteRestaurants = new ArrayList<>();
        try (Connection conn = DBconnection.connect()) {
            String query = "SELECT r.* FROM favorites f INNER JOIN restaurant r ON f.restaurantid = r.RestaurantID WHERE f.username = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantID(resultSet.getInt("RestaurantID"));
                restaurant.setName(resultSet.getString("Name"));
                restaurant.setAddress(resultSet.getString("Address"));
                restaurant.setCuisineType(resultSet.getString("CuisineType"));
                restaurant.setRatings(resultSet.getInt("Ratings"));
                favoriteRestaurants.add(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favoriteRestaurants;
    }
    public boolean removeFavorite(String username, int restaurantId) {
        try (Connection conn = DBconnection.connect()) {
            String query = "DELETE FROM favorites WHERE username = ? AND restaurantid = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setInt(2, restaurantId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addMenuItem(menu Menu) {
        String query = "INSERT INTO menu (name, description, price, restaurantid) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBconnection.connect()) {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, Menu.getName());
            statement.setString(2, Menu.getDescription());
            statement.setDouble(3, Menu.getPrice());
            statement.setInt(4, Menu.getRestaurantID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    // Other methods for managing favorites, like retrieving user favorites, can be added here.
}

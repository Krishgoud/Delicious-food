package com.foodapp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.foodapp.daoimpl.FavoritesDaoImpl;
import com.foodapp.daoimpl.RestaurantDaoImpl;
import com.foodapp.model.Favorite;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/AddToFavoritesServlet")
public class AddToFavoritesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the restaurantId from the request
        String restaurantIdStr = request.getParameter("restaurantId");
        String username = (String) request.getSession().getAttribute("username");

        // Debugging the values
        System.out.println("restaurantIdStr: " + restaurantIdStr); // Check the restaurantId parameter
        System.out.println("username: " + username); // Check the username from session
        
        if (restaurantIdStr != null && !restaurantIdStr.trim().isEmpty() && username != null) {
            try {
                int restaurantId = Integer.parseInt(restaurantIdStr);
                FavoritesDaoImpl favoritesDao = new FavoritesDaoImpl();
                Restaurant restaurant = favoritesDao.getRestaurantById(restaurantId);

                if (restaurant != null) {
                    // Create a new Favorite object
                    Favorite favorite = new Favorite();
                    favorite.setUsername(username);
                    favorite.setRestaurantId(restaurantId);

                    // Add the restaurant to favorites
                    favoritesDao.addFavorite(favorite);

                    response.sendRedirect("addToFavorites.jsp?message=Added to Favorites!");
                } else {
                    response.sendRedirect("home.jsp?error=Restaurant not found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("home.jsp?error=Error occurred while adding to favorites.");
            }
        } else {
            response.sendRedirect("home.jsp?error=Invalid restaurant ID or user not logged in.");
        }
    }
}

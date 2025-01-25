package com.foodapp;

import java.io.IOException;
import com.foodapp.daoimpl.FavoritesDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RemoveFromFavoritesServlet")
public class RemoveFromFavoritesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String restaurantIdStr = request.getParameter("restaurantId");
        String username = (String) request.getSession().getAttribute("username");

        if (restaurantIdStr != null && !restaurantIdStr.trim().isEmpty() && username != null) {
            try {
                int restaurantId = Integer.parseInt(restaurantIdStr);
                FavoritesDaoImpl favoritesDao = new FavoritesDaoImpl();

                // Remove favorite
                boolean removed = favoritesDao.removeFavorite(username, restaurantId);

                if (removed) {
                    response.sendRedirect("addToFavorites.jsp?message=Removed from Favorites!");
                } else {
                    response.sendRedirect("addToFavorites.jsp?error=Failed to remove from Favorites.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("addToFavorites.jsp?error=Error occurred while removing from Favorites.");
            }
        } else {
            response.sendRedirect("addToFavorites.jsp?error=Invalid restaurant ID or user not logged in.");
        }
    }
}

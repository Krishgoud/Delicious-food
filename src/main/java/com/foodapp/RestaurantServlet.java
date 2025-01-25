package com.foodapp;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.foodapp.daoimpl.RestaurantDaoImpl;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RestaurantServlet")
public class RestaurantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RestaurantServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");  // Correct parameter name
        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
        List<Restaurant> restaurants = null;

        try {
            restaurantDao.connect();
            // Get all restaurants
            restaurants = restaurantDao.getAllRestaurants();

            // If search query exists, filter restaurants based on name or cuisine
            if (searchQuery != null && !searchQuery.isEmpty()) {
                restaurants = restaurants.stream()
                        .filter(r -> r.getName().toLowerCase().contains(searchQuery.toLowerCase()) || 
                                     r.getCuisineType().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());
            }

            // Set the filtered restaurant list to the request attribute
            request.setAttribute("restaurants", restaurants);

            // Forward to the JSP page to display the list of restaurants
           request.getRequestDispatcher("/home.jsp").forward(request, response);


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching restaurants.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            restaurantDao.closeConnection();
        }
    }

    
}

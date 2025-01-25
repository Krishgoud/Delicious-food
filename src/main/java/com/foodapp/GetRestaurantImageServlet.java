package com.foodapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



    @WebServlet("/GetRestaurantImage")
    public class GetRestaurantImageServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Get the restaurant ID from the request
            String restaurantId = request.getParameter("RestaurantId");

            // Make sure the restaurantId is not null or empty
            if (restaurantId == null || restaurantId.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required");
                return;
            }

            // Retrieve the image data based on the restaurantId
            byte[] imageData = getRestaurantImageById(restaurantId);

            // If no image was found, return a 404 error
            if (imageData == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
                return;
            }

            // Set the content type (for example, JPEG image)
            response.setContentType("image/jpeg");  // Change to "image/png" if your images are PNG

            // Write the image data to the response output stream
            response.getOutputStream().write(imageData);
        }

        private byte[] getRestaurantImageById(String restaurantId) {
            byte[] imageData = null;

            // Database connection parameters (adjust these according to your database setup)
            String url = "jdbc:mysql://localhost:3306/foodapp";
            String user = "root";
            String password = "password";

            // SQL query to fetch the image based on restaurantId
            String query = "SELECT image FROM restaurants WHERE restaurant_id = ?";

            // Establish database connection and fetch the image
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, restaurantId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    imageData = rs.getBytes("image");  // Assuming the image column is named "image"
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return imageData;
        }
    }

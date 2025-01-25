package com.foodapp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.foodapp.DButil.DBconnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/UploadRestaurantImage")
public class ImageUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get restaurantId from request
        String restaurantIdParam = request.getParameter("RestaurantId");


        if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
            // Handle missing restaurantId (e.g., return an error or set a default value)
            request.setAttribute("errorMessage", "Restaurant ID is required");
            request.getRequestDispatcher("imguplaad.jsp").forward(request, response);
            return;
        }

        try {
            int RestaurantId = Integer.parseInt(restaurantIdParam);  // Parse to int
            Part imagePart = request.getPart("image");

            if (imagePart != null && imagePart.getSize() > 0) {
                String fileName = imagePart.getSubmittedFileName();
                InputStream imageInputStream = imagePart.getInputStream();

                // Save the image to the database
                try (Connection connection = DBconnection.connect()) {
                    String sql = "UPDATE restaurant SET Imagepath = ? WHERE RestaurantID = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setBlob(1, imageInputStream);
                        stmt.setInt(2, RestaurantId);
                        stmt.executeUpdate();
                    }
                }

                // Set success message and forward to the JSP
                request.setAttribute("successMessage", "Image uploaded successfully for restaurant ID: " + RestaurantId);
            } else {
                request.setAttribute("errorMessage", "No image uploaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error uploading image.");
        }
        
        request.getRequestDispatcher("uploadRestaurantImage.jsp").forward(request, response);
    }
}
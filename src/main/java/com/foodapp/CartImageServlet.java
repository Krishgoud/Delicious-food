package com.foodapp;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foodapp.DButil.DBconnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetCartImage")
public class CartImageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the menu ID from the request parameters
        String menuId = request.getParameter("menuId");

        if (menuId == null || menuId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Menu ID is required.");
            return;
        }

        try (Connection con = DBconnection.connect();
             PreparedStatement pstmt = con.prepareStatement("SELECT Imagepath FROM menu WHERE MenuID = ?")) {

            // Set the menu ID in the query
            pstmt.setInt(1, Integer.parseInt(menuId));

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    byte[] imageData = rs.getBytes("Imagepath");

                    if (imageData != null) {
                        // Set the content type for the image response
                        response.setContentType("image/jpeg"); // Adjust if your images are PNG or other formats
                        
                        // Write the image data to the response output stream
                        try (OutputStream out = response.getOutputStream()) {
                            out.write(imageData);
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image data not found.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Menu item not found.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving the image.");
        }
    }
}

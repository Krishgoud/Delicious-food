package com.foodapp;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.foodapp.DButil.DBconnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50   // 50MB
)
@WebServlet("/UploadImageServlet")
public class UploadImageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuId = request.getParameter("menuId");
        Part filePart = request.getPart("image");

        if (filePart != null && menuId != null) {
            try (InputStream inputStream = filePart.getInputStream();
                 Connection connection = DBconnection.connect();
                 PreparedStatement pstmt = connection.prepareStatement("UPDATE menu SET Imagepath = ? WHERE MenuID = ?")) {
                
                pstmt.setBlob(1, inputStream);
                pstmt.setString(2, menuId);
                pstmt.executeUpdate();
                response.getWriter().write("Image uploaded successfully!");
                System.out.println("Menu ID: " + menuId);
                System.out.println("File Name: " + filePart.getSubmittedFileName());
                System.out.println("File Size: " + filePart.getSize());

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving image.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input.");
        }
    }
}

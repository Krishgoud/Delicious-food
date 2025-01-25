<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.daoimpl.UserDaoImpl" %>
<%@ page import="com.foodapp.model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Favorites</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .favorites-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 10px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .favorite-card {
            padding: 15px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .favorite-card h2 {
            margin: 0 0 5px;
        }
        .favorite-card p {
            margin: 0;
        }
    </style>
</head>
<body>
    <h1 style="text-align: center;">My Favorite Restaurants</h1>
    <div class="favorites-container">
        <%
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId == null) {
        %>
            <p style="color: red; text-align: center;">User is not logged in. Please <a href="login.jsp">log in</a>.</p>
        <%
            } else {
                // User is logged in, fetch favorites
                UserDaoImpl dao = new UserDaoImpl();
                try {
                    dao.connect(); // Connect to the database

                    // Fetch favorite restaurants for the user
                    List<Restaurant> favorites = dao.getFavoritesByUserId(userId);

                    dao.closeConnection(); // Close the connection

                    if (favorites != null && !favorites.isEmpty()) {
                        for (Restaurant r : favorites) {
        %>
                            <div class="favorite-card">
                                <h2><%= r.getName() %></h2>
                                <p><strong>Cuisine:</strong> <%= r.getCuisineType() %></p>
                                <p><strong>Rating:</strong> <%= r.getRatings() %></p>
                                <p><strong>Address:</strong> <%= r.getAddress() %></p>
                            </div>
        <%
                        }
                    } else {
        %>
                        <p style="text-align: center;">You have no favorite restaurants yet.</p>
        <%
                    }
                } catch (Exception e) {
                    out.println("<p style='color: red; text-align: center;'>Error fetching favorite restaurants: " + e.getMessage() + "</p>");
                } finally {
                    dao.closeConnection(); // Ensure connection is closed in case of an error
                }
            }
        %>
    </div>
</body>
</html>

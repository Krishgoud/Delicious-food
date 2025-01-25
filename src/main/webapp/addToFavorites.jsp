<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Favorite" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<%@ page import="com.foodapp.daoimpl.FavoritesDaoImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Favorite Restaurants</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #333;
            color: white;
            padding: 1rem;
            text-align: center;
        }

        .favorites-container {
            padding: 20px;
        }

        .favorites-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .restaurant-card {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .restaurant-card h2 {
            margin: 0;
        }

        .restaurant-card p {
            margin: 5px 0;
        }

        .cta-buttons a {
            text-decoration: none;
            padding: 5px 10px;
            margin-right: 10px;
            background-color: #ff5722;
            color: white;
            border-radius: 3px;
            transition: 0.3s;
        }

        .cta-buttons a:hover {
            background-color: #e64a19;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h1>FoodApp - My Favorite Restaurants</h1>
    </div>

    <div class="favorites-container">
        <% 
            String username = (String) session.getAttribute("username");

            if (username != null) {
                FavoritesDaoImpl favoritesDao = new FavoritesDaoImpl();
                List<Restaurant> favoriteRestaurants = null;

                try {
                    // Fetch user's favorite restaurants
                    favoriteRestaurants = favoritesDao.getFavoritesByUsername(username);

                    if (favoriteRestaurants != null && !favoriteRestaurants.isEmpty()) {
                        for (Restaurant restaurant : favoriteRestaurants) {
        %>
        <div class="restaurant-card">
            <h2><%= restaurant.getName() %></h2>
            <p>Cuisine: <%= restaurant.getCuisineType() %></p>
            <p>Location: <%= restaurant.getAddress() %></p>
            <p>Rating: <%= restaurant.getRatings() %>/5</p>
            <div class="cta-buttons">
    <a href="showMenu?restaurantId=<%= restaurant.getRestaurantID() %>">View Menu</a>
    <a href="RemoveFromFavoritesServlet?restaurantId=<%= restaurant.getRestaurantID() %>" 
       onclick="return confirm('Are you sure you want to remove this from your Favorites?');">
        Remove from Favorites
    </a>
</div>
            
        </div>
        <% 
                        }
                    } else {
        %>
        <p>No favorite restaurants found.</p>
        <% 
                    }
                } catch (Exception e) {
                    out.println("<p>Error fetching favorites: " + e.getMessage() + "</p>");
                }
            } else {
        %>
        <p>Please log in to view your favorite restaurants.</p>
        <% 
            }
        %>
    </div>
</body>
</html>

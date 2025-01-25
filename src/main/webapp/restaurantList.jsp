<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<%@ page import="com.foodapp.daoimpl.RestaurantDaoImpl" %>
<%@ page import="java.util.stream.Collectors" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp - Restaurant List</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">    
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: #ffffff;
            color: #444;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .navbar {
            background: #4caf50;
            color: white;
            padding: 0.5rem 1.5rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: sticky;
            top: 0;
            z-index: 1000;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-bottom: 3px solid #388e3c;
        }

        .navbar .logo {
            font-size: 1.6rem;
            font-weight: 700;
            letter-spacing: 1px;
        }

        .restaurants-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(260px, 1fr));
            gap: 20px;
            padding: 20px;
            background: #f9f9f9;
        }

        .restaurant-card {
            background: white;
            border: 1px solid #e0e0e0;
            border-radius: 10px;
            overflow: hidden;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .restaurant-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
        }

        .restaurant-image {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }

        .card-content {
            padding: 15px;
        }

        .card-content h2 {
            font-size: 1.5rem;
            margin: 0;
            color: #4caf50;
        }

        .card-content p {
            margin: 8px 0;
            font-size: 0.95rem;
            color: #555;
        }

        .cta-buttons {
            display: flex;
            justify-content: space-between;
            padding: 10px 15px;
            background: #f1f1f1;
        }

        .cta-buttons a {
            text-decoration: none;
            color: white;
            background-color: #8bc34a;
            padding: 8px 12px;
            font-size: 0.9rem;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .cta-buttons a:hover {
            background-color: #7cb342;
        }

        @media (max-width: 768px) {
            .restaurants-container {
                grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            }
        }

        @media (max-width: 480px) {
            .restaurants-container {
                grid-template-columns: 1fr;
            }

            .restaurant-card {
                width: 90%;
                margin: 0 auto;
            }
        }
    </style>
</head>
<body>

<div class="navbar">
    <div class="logo">Delicious Food</div>
    <div class="profile-container" style="display: flex; align-items: center; gap: 15px;">
        <a href="UserLogindetailsjsp.jsp">
            <i class="fas fa-user user-icon" style="font-size: 1.8rem; color: white; cursor: pointer; transition: transform 0.3s ease;"></i>
        </a>
        <% 
            String loggedInUsername = (String) session.getAttribute("username");
            if (loggedInUsername != null) {
        %>
            <%= loggedInUsername %>
        <% 
            } else {
        %>
            <span class="username" style="color: white; font-size: 1rem;">Hello, Guest</span>
        <% 
            }
        %>
    </div>
</div>

<!-- Back Button -->
<div style="position: absolute; top:50px; right: 20px; z-index: 999;">
    <button onclick="window.history.back()" 
            style="padding: 0.5rem 1rem; background-color: #8bc34a; color: white; border: none; border-radius: 5px; 
                   cursor: pointer; transition: background-color 0.3s ease;">
        Back
    </button>
</div>


<div class="restaurants-container">
    <% 
        String selectedCuisine = request.getParameter("cuisine");
        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
        List<Restaurant> restaurants = null;
        try {
            restaurantDao.connect();
            restaurants = restaurantDao.getAllRestaurants(); // Get all restaurants

            // Filter by cuisine type if selected
            if (selectedCuisine != null && !selectedCuisine.isEmpty()) {
                restaurants = restaurants.stream()
                    .filter(r -> r.getCuisineType().equalsIgnoreCase(selectedCuisine))
                    .collect(Collectors.toList());
            }

            // Display restaurants
            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant restaurant : restaurants) {
    %>
                    <div class="restaurant-card">
                    
                        <div class="card-content">
                            <h2><%= restaurant.getName() %></h2>
                            <p><strong>Cuisine:</strong> <%= restaurant.getCuisineType() %></p>
                            <p><strong>Location:</strong> <%= restaurant.getAddress() %></p>
                            <p><strong>Rating:</strong> ⭐ <%= restaurant.getRatings() %>/5</p>
                        </div>
                        <div class="cta-buttons">
                            <a href="AddToFavoritesServlet?restaurantId=<%= restaurant.getRestaurantID() %>">Add to Favorites</a>
                            <a href="showMenu?restaurantId=<%= restaurant.getRestaurantID() %>&restaurantName=<%= restaurant.getName() %>">View Menu</a>
                        </div>
                    </div>
    <% 
                }
            } else {
                out.println("<p>No restaurants available for the selected cuisine.</p>");
            }
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        } finally {
            restaurantDao.closeConnection();
        }
    %>
</div>

</body>
</html>

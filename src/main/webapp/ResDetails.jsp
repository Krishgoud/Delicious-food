<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.daoimpl.RestaurantDaoImpl" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.daoimpl.MenuDaoImpl" %>
<%@ page import="com.foodapp.model.menu" %>

<%
    // Get the restaurant ID from the request parameter
    int restaurantId = Integer.parseInt(request.getParameter("id"));
    
    // Fetch restaurant details
    RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
    Restaurant restaurant = restaurantDao.fetchOne(restaurantId);  // Fetch the restaurant based on ID

    // Fetch menu for the restaurant
    MenuDaoImpl menuDao = new MenuDaoImpl();
    List<menu> menuItems = menuDao.fetchAll();  // Fetch all menu items for the restaurant (you can filter this if needed)
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Details</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            color: #333;
        }
        .restaurant-details {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .restaurant-details h1 {
            font-size: 2.5rem;
            color: #ff5722;
        }
        .restaurant-info {
            display: flex;
            justify-content: space-between;
            margin-bottom: 30px;
        }
        .restaurant-info img {
            max-width: 200px;
            border-radius: 8px;
            object-fit: cover;
        }
        .restaurant-info .details {
            flex: 1;
            margin-left: 20px;
        }
        .menu-items {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .menu-card {
            background-color: #fff;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            padding: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .menu-card img {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 8px;
        }
        .menu-card h3 {
            font-size: 1.2rem;
            margin-top: 10px;
        }
        .menu-card p {
            font-size: 1rem;
            color: #666;
        }
        .menu-card .price {
            font-size: 1.2rem;
            font-weight: bold;
            margin-top: 10px;
            color: #ff5722;
        }
    </style>
</head>
<body>

    <div class="restaurant-details">
        <h1><%= restaurant.getName() %> - Details</h1>
        
        <div class="restaurant-info">
            <img src="data:image/png;base64,<%= restaurant.getImagepath() %>" alt="Restaurant Image">
            <div class="details">
                <h2><%= restaurant.getName() %></h2>
                <p><strong>Cuisine:</strong> <%= restaurant.getCuisineType() %></p>
                <p><strong>Location:</strong> <%= restaurant.getAddress() %></p>
                <p><strong>Rating:</strong> <%= restaurant.getRatings() %>/5</p>
                <p><strong>Description:</strong> <%= restaurant.getDescription() %></p>
            </div>
        </div>

        <h2>Menu</h2>
        <div class="menu-items">
            <%
                for (menu item : menuItems) {
                    if (item.getRestaurantID() == restaurantId) {
            %>
            <div class="menu-card">
                <img src="data:image/png;base64,<%= item.getImagepath() %>" alt="Menu Item Image">
                <h3><%= item.getName() %></h3>
                <p><%= item.getDescription() %></p>
                <p class="price">$<%= item.getPrice() %></p>
            </div>
            <%
                    }
                }
            %>
        </div>

    </div>

</body>
</html>

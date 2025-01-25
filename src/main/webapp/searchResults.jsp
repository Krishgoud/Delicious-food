<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.menu" %>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.daoimpl.MenuDaoImpl" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <title>FoodApp - Search Results</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #333;
        }

        header {
            background-color: #ff5722;
            color: white;
            padding: 1rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .navbar {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .logo {
            font-size: 1.5rem;
            font-weight: bold;
        }

        .profile-container {
            display: flex;
            align-items: center;
            gap: 15px;
        }

        .profile-container a {
            display: inline-block;
            cursor: pointer;
        }

        .profile-container i {
            font-size: 1.8rem;
            color: white;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .profile-container i:hover {
            transform: scale(1.1);
        }

        .search-results {
            padding: 2rem;
        }

        .menu-item-card {
            background: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 1rem;
            margin-bottom: 1rem;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .menu-item-card h2 {
            margin: 0;
            color: #ff5722;
        }

        .menu-item-card p {
            margin: 0.5rem 0;
        }

        .back-button, .go-to-cart-btn {
            padding: 0.5rem 1rem;
            background-color: #ff5722;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .back-button:hover, .go-to-cart-btn:hover {
            background-color: #e64a19;
        }

        .add-to-cart-btn {
            padding: 0.5rem 1rem;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        .add-to-cart-btn:hover {
            background-color: #45a049;
        }

        .menu-item-buttons {
            margin-top: 10px;
        }

        .search-results-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .search-results-header h1 {
            margin: 0;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header>
        <div class="navbar">
            <div class="logo">Delicious Food</div>
        </div>
        <div class="profile-container">
            <a href="UserLogindetailsjsp.jsp">
                <i class="fas fa-user user-icon"></i>
            </a>
            <% 
                String loggedInUsername = (String) session.getAttribute("username");
                if (loggedInUsername != null) {
            %>
                <span style="color: white; font-size: 1rem;"><%= loggedInUsername %></span>
            <% 
                } else { 
            %>
                <span style="color: white; font-size: 1rem;">Hello, Guest</span>
            <% 
                } 
            %>
        </div>
    </header>

    <!-- Search Results Section -->
    <div class="search-results">
        <div class="search-results-header">
            <h1>Search Results</h1>
            <button class="back-button" onclick="window.history.back()">Back</button>
        </div>

        <%
            String searchQuery = request.getParameter("searchQuery");
            String restaurantId = request.getParameter("restaurantId"); // Get the restaurant ID from the request
            
            MenuDaoImpl menuItemDao = new MenuDaoImpl();  // DAO class
            List<menu> menuItems = null;
            Set<Integer> displayedMenuIds = new HashSet<>(); // Set to track displayed menu IDs

            if (searchQuery != null && !searchQuery.trim().isEmpty() && restaurantId != null) {
                try {
                    menuItemDao.connect();
                    // Fetch items from the specific restaurant based on the search query
                    menuItems = menuItemDao.searchFoodItemsByName(searchQuery);
                } catch (Exception e) {
                    out.println("<p>Error fetching search results: " + e.getMessage() + "</p>");
                }
            }

            if (menuItems != null && !menuItems.isEmpty()) {
                for (menu menuItem : menuItems) {
                    // Check if the item has already been displayed
                    if (!displayedMenuIds.contains(menuItem.getMenuID())) {
                        displayedMenuIds.add(menuItem.getMenuID());
        %>
        <div class="menu-item-card">
            <h2><%= menuItem.getName() %></h2>
            <p>Description: <%= menuItem.getDescription() %></p>
            <p>Price: ₹<%= menuItem.getPrice() %></p>

            <!-- Add to Cart Form -->
            <div class="menu-item-buttons">
                <form action="CartServlet" method="post">
                    <input type="hidden" name="menuID" value="<%= menuItem.getMenuID() %>">
                    <button type="submit" class="add-to-cart-btn">
                        <i class="fas fa-shopping-cart"></i> Add to Cart
                    </button>
                </form>
            </div>
        </div>
    <%
                    } // End of duplicate check
                }
            } else {
    %>
        <p>No menu items found for "<%= searchQuery %>" from this restaurant.</p>
    <%
            }
    %>
</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.daoimpl.MenuDaoImpl" %>
<%@ page import="com.foodapp.model.menu" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<%@ page import="com.foodapp.daoimpl.RestaurantDaoImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp - Restaurant Menu</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* General styles */
        :root {
            --primary-color: #ff5722;
            --secondary-color: #f39c12;
            --background-color: #f4f4f9;
            --text-color: #333;
            --card-background: #ffffff;
            --shadow-color: rgba(0, 0, 0, 0.1);
            --transition-speed: 0.3s;
            --font-family: 'Poppins', sans-serif;
        }

        body {
            font-family: var(--font-family);
            background-color: var(--background-color);
            margin: 0;
            padding: 0;
        }

        header {
            background-color: var(--primary-color);
            color: white;
            padding: 1.5rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            margin: 0;
            font-size: 2rem;
            font-weight: 600;
        }

        .search-box {
            display: flex;
            align-items: center;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 0.5rem;
        }

        .search-box input {
            border: none;
            outline: none;
            font-size: 1rem;
            flex: 1;
            padding: 0.4rem;
            border-radius: 5px 0 0 5px;
        }

        .search-box button {
            background-color: var(--primary-color);
            color: white;
            border: none;
            border-radius: 0 5px 5px 0;
            padding: 0.5rem 1rem;
            cursor: pointer;
            transition: background-color var(--transition-speed);
        }

        .search-box button:hover {
            background-color: var(--secondary-color);
        }

        .menu-section {
            padding: 2rem 1.5rem;
        }

        .menu-items {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
            align-items: stretch;
        }

        .menu-item {
            background-color: var(--card-background);
            border-radius: 10px;
            box-shadow: 0 2px 6px var(--shadow-color);
            padding: 1.2rem;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            transition: transform var(--transition-speed);
        }

        .menu-item:hover {
            transform: translateY(-5px);
        }

        .menu-item img {
            width: 100%;
            height: 150px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 1rem;
        }

        .menu-item h3 {
            font-size: 1.3rem;
            color: var(--primary-color);
            margin-bottom: 0.5rem;
        }

        .menu-item-description {
            font-size: 0.95rem;
            color: var(--text-color);
            margin-bottom: 1rem;
        }

        .menu-item-price {
            font-size: 1.1rem;
            font-weight: bold;
            color: var(--secondary-color);
            margin-bottom: 1rem;
        }

        .menu-item-buttons {
            display: flex;
            justify-content: space-between;
            gap: 0.5rem;
        }

        .btn {
            padding: 0.8rem;
            border-radius: 5px;
            color: white;
            background-color: var(--primary-color);
            text-decoration: none;
            border: none;
            cursor: pointer;
            transition: background-color var(--transition-speed);
        }

        .btn:hover {
            background-color: var(--secondary-color);
        }

        .back-button {
            margin: 1rem 0;
            padding: 0.8rem 1.2rem;
            color: white;
            background-color: var(--secondary-color);
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 1rem;
            display: inline-block;
            cursor: pointer;
            transition: background-color var(--transition-speed);
        }

        .back-button:hover {
            background-color: var(--primary-color);
        }

        footer {
            background-color: var(--primary-color);
            color: white;
            text-align: center;
            padding: 1rem;
        }

        /* Responsive design */
        @media (max-width: 768px) {
            .menu-items {
                grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            }
        }
    </style>
</head>
<body>
    <header>
        <h1><%= request.getAttribute("restaurantName") != null ? request.getAttribute("restaurantName") : "FoodApp Restaurant Menu" %></h1>
        
      <div style="display: flex; align-items: center; gap: 1rem;">
    <form action="searchResults.jsp" method="get" class="search-box">
        <input type="text" name="searchQuery" placeholder="Search food items">
        <input type="hidden" name="restaurantId" value="123"> <!-- Replace 123 with actual ID -->
        <button type="submit">Search</button>
    </form>
       <!-- User and Cart icons -->
            <a href="UserLogindetailsjsp.jsp">
                <i class="fas fa-user" style="font-size: 1.5rem; color: #fff;"></i>
            </a>
            <a href="cart.jsp">
                <i class="fas fa-shopping-cart" style="font-size: 1.5rem; color: #fff;"></i>
            </a>
</div>
      
    </header>

   
    <main>
      <section id="menu" class="menu-section">
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <h2>Our Menu</h2>
        <!-- Back Button -->
        <button onclick="window.history.back()" style="padding: 0.5rem 1rem; background-color: #ff5722; color: white; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s;">
            Back
        </button>
    </div> 
            <div class="menu-items">
                <%
                    Integer restaurantId = (Integer) request.getAttribute("RestaurantID");
                    if (restaurantId == null) {
                        out.println("Restaurant ID is missing.");
                        return;
                    }
                    MenuDaoImpl menuDao = new MenuDaoImpl();
                    List<menu> menuList = null;

                    String searchQuery = request.getParameter("searchQuery");

                    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                        menuList = menuDao.searchFoodItemsByName(searchQuery.trim());
                    } else {
                        menuList = menuDao.fetchmenubyRestaurantID(restaurantId);
                    }

                    if (menuList != null && !menuList.isEmpty()) {
                        for (menu m : menuList) {
                %>
                <article class="menu-item">
                    <img src="GetMenuImage?menuId=<%= m.getMenuID() %>" alt="<%= m.getName() %>">
                    <h3><%= m.getName() %></h3>
                    <p class="menu-item-description"><%= m.getDescription() %></p>
                    <p class="menu-item-price">Rs: <%= m.getPrice() %></p>
                    
                    <div class="menu-item-buttons">
                        <form action="CartServlet" method="post">
                            <input type="hidden" name="menuID" value="<%= m.getMenuID() %>">
                            <button type="submit" class="btn">
                                <i class="fas fa-shopping-cart"></i> Add to Cart
                            </button>
                        </form>
                    </div>
                </article>
                <%
                        }
                    } else {
                %>
                <p>No menu items found for "<%= searchQuery %>".</p>
                <%
                    }
                %>
            </div>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 FoodApp. All rights reserved.</p>
    </footer>
</body>
</html>

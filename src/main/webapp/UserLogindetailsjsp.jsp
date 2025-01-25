<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <style>
        :root {
            --primary-color: #ffffff;
            --secondary-color: #e74c3c; /* Dark Red */
            --background-color: #2c3e50; /* Dark Blue/Gray */
            --card-background: #f5f5f5; /* Lighter Gray for cards */
            --link-background: #dcdcdc; /* Light color for links */
            --username-color: #3498db; /* Blue for username */
            --link-color: #1e90ff; /* Blue color for links */
            --card-hover-color: #bdc3c7; /* Hover effect for card */
            --border-radius: 8px;
            --box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: var(--background-color);
            color: var(--primary-color);
            line-height: 1.6;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            padding: 30px;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: var(--secondary-color); /* Dark Red header */
            color: white;
            padding: 15px 30px;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
        }

        .logo {
            font-size: 26px;
            font-weight: bold;
            letter-spacing: 1px;
            text-transform: uppercase;
        }

        .user-info {
            display: flex;
            align-items: center;
            font-size: 18px;
            font-weight: bold;
            background-color: var(--card-background);
            padding: 15px;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            transition: background-color 0.3s ease;
        }

        .user-info:hover {
            background-color: var(--card-hover-color); /* Hover effect for card */
        }

        .username {
            color: var(--username-color); /* Blue color for username */
            font-size: 20px;
            font-weight: bold;
        }

        nav ul {
            list-style-type: none;
            padding: 0;
            margin: 30px 0;
            display: flex;
            flex-wrap: wrap;
            gap: 15px;
        }

        nav li {
            flex: 1 1 calc(50% - 15px); /* Adjust width for smaller screens */
        }

        nav a {
            display: block;
            text-decoration: none;
            background-color: var(--link-background); /* Light background color for links */
            color: var(--link-color); /* Blue color for links */
            padding: 15px;
            border-radius: var(--border-radius);
            box-shadow: var(--box-shadow);
            text-align: center;
            font-size: 16px;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        nav a:hover {
            background-color: var(--card-hover-color);
            transform: translateY(-5px);
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
            color: white; /* Change link color to white when hovered */
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">FoodApp</div>
            <div class="user-info">
                <% 
                    // Retrieve the logged-in user's username from the session
                    String loggedInUsername = (String) session.getAttribute("username");
                    if (loggedInUsername != null) {
                %>
                    <span class="username">Hello, <%= loggedInUsername %></span>
                <% 
                    } else {
                %>
                    <span class="username">Hello, Guest</span>
                <% 
                    }
                %>
            </div>
        </header>

        <nav>
            <ul>
                <li><a href="Userdetails.jsp">User Details</a></li>
                <li><a href="OrderSummary.jsp">Food Orders</a></li>
                <li><a href="home.jsp">View Restaurants</a></li>
                <li><a href="addToFavorites.jsp">Favorite Restaurants</a></li>
                <li><a href="cart.jsp"> View Cart</a></li>
                <li><a href="login.jsp">Log Out</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.foodapp.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>

    <!-- Include Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">

    <style>
        /* CSS Variables */
        :root {
            --primary-color: #ff5722;
            --secondary-color: #f39c12;
            --background-color: #f9fafc;
            --card-background: #ffffff;
            --text-color: #333;
            --button-hover-color: #e64a19;
            --shadow-color: rgba(0, 0, 0, 0.1);
            --font-family: 'Poppins', sans-serif;
            --transition-speed: 0.3s;
            --header-gradient: linear-gradient(45deg, #ff5722, #f39c12);
        }

        /* General Styles */
        body {
            font-family: var(--font-family);
            background-color: var(--background-color);
            color: var(--text-color);
            margin: 0;
            padding: 0;
        }

        /* Header Styles */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: var(--header-gradient);
            color: white;
            padding: 1rem 2rem;
            font-size: 1.5rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        header .logo {
            font-weight: bold;
        }

        header .title {
            text-align: center;
            flex-grow: 1;
        }

        header .user-info {
            display: flex;
            align-items: center;
            gap: 1rem;
        }

        header .user-icon {
            font-size: 1.8rem;
            cursor: pointer;
            color: white; /* Ensures the icon color is white */
            transition: color 0.3s ease;
        }

        header .user-icon:hover {
            color: #FFD700; /* Optional: Change color on hover */
        }

        header .username {
            font-size: 1rem;
        }

        /* Cart Container (Single Card) */
        .cart-container {
            max-width: 900px;
            margin: 2rem auto;
            padding: 2rem;
            background-color: var(--card-background);
            border-radius: 12px;
            box-shadow: 0 8px 15px var(--shadow-color);
            animation: fadeIn 0.5s;
        }

        /* Cart Item List */
        .cart-item-list {
            margin-bottom: 2rem;
        }

        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 1.2rem;
            background-color: var(--background-color);
            border-radius: 8px;
            box-shadow: 0 4px 8px var(--shadow-color);
            margin-bottom: 1.2rem;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .cart-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.15);
        }

        .cart-item img {
            width: 80px;
            height: 80px;
            border-radius: 8px;
            margin-right: 1rem;
        }

        .cart-item-content {
            flex: 1;
        }

        .cart-item h3 {
            margin: 0;
            font-size: 1.2rem;
            color: var(--text-color);
        }

        .cart-item p {
            margin: 0.5rem 0;
            font-size: 1rem;
            color: var(--text-color);
        }

        .quantity-control {
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }

        .quantity-control button {
            background-color: var(--primary-color);
            color: white;
            border: none;
            padding: 0.5rem;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color var(--transition-speed);
        }

        .quantity-control button:hover {
            background-color: var(--button-hover-color);
        }

        /* Buttons */
        .btn {
            background: linear-gradient(45deg, var(--primary-color), var(--secondary-color));
            color: white;
            padding: 0.75rem 1.5rem;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1rem;
            transition: all 0.3s ease;
            box-shadow: 0 4px 8px var(--shadow-color);
        }

        .btn:hover {
            background: linear-gradient(45deg, var(--secondary-color), var(--primary-color));
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        .remove-btn {
            background: none;
            border: none;
            color: var(--primary-color);
            cursor: pointer;
            font-size: 1rem;
            transition: color var(--transition-speed), transform var(--transition-speed);
        }

        .remove-btn:hover {
            color: var(--button-hover-color);
            transform: scale(1.1);
        }

        /* Empty Cart Message */
        .no-items-message {
            text-align: center;
            font-size: 1.2rem;
            color: var(--text-color);
        }

        .buttons-container {
            display: flex;
            justify-content: space-between;
            margin-top: 1.5rem;
        }
    </style>

    <script>
        function updateQuantity(itemId, newQuantity) {
            if (newQuantity < 1) return;
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "UpdateCartServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onload = function () {
                if (xhr.status === 200) {
                    location.reload();
                } else {
                    alert("Failed to update cart. Please try again.");
                }
            };
            xhr.send("itemId=" + encodeURIComponent(itemId) + "&quantity=" + newQuantity);
        }

        function deleteCartItem(itemId) {
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "DeleteCartItemServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onload = function () {
                if (xhr.status === 200) {
                    location.reload();
                } else {
                    alert("Failed to remove item. Please try again.");
                }
            };
            xhr.send("itemId=" + encodeURIComponent(itemId));
        }
    </script>
</head>

<body>
    <header>
        <div class="logo">Delicious Food</div>
        <div class="title">Cart</div>
          <a href="home.jsp" style="color: white; text-decoration: none;">
            <i class="fas fa-home" style="margin-right: 8px;"></i>
        </a>
    </div>
        <div class="user-info">
            <a href="UserLogindetailsjsp.jsp">
                <i class="fas fa-user user-icon"></i>
            </a>
            <% 
                String loggedInUsername = (String) session.getAttribute("username");
                if (loggedInUsername != null) {
            %>
                <span class="username"><%= loggedInUsername %></span>
            <% 
                } else {
            %>
                <span class="username">Hello, Guest</span>
            <% 
                }
            %>
        </div>
    </header>
    

    <div class="cart-container">
        <% 
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            if (cart == null || cart.isEmpty()) {
        %>
            <p class="no-items-message">Your cart is empty.</p>
        <%
            } else {
                double grandTotal = 0;
        %>
            <div class="cart-item-list">
                <% 
                    for (CartItem item : cart.values()) {
                        double total = item.getQuantity() * item.getPrice();
                        grandTotal += total;
                %>
                    <div class="cart-item">
                        <img src="GetCartImage?menuId=<%= item.getMenuID() %>" alt="<%= item.getName() %>">
                        <div class="cart-item-content">
                            <h3><%= item.getName() %></h3>
                            <p>Price: ₹<%= item.getPrice() %></p>
                            <p>Total: ₹<%= total %></p>
                        </div>
                        <div class="quantity-control">
                            <button onclick="updateQuantity(<%= item.getMenuID() %>, <%= item.getQuantity() - 1 %>)">-</button>
                            <span><%= item.getQuantity() %></span>
                            <button onclick="updateQuantity(<%= item.getMenuID() %>, <%= item.getQuantity() + 1 %>)">+</button>
                        </div>
                        <button class="remove-btn" onclick="deleteCartItem(<%= item.getMenuID() %>)">Remove</button>
                    </div>
                <% 
                    }
                %>
            </div>

            <div class="cart-item1">
                <h3>Grand Total: ₹<%= grandTotal %></h3>
            </div>

            <div class="buttons-container">
      <!-- Add Item Button -->


<!-- Back Button -->
<form action="javascript:void(0);">
    <button type="button" class="btn" onclick="window.history.back()">Add Item</button>
</form>
      
                <form action="checkOut.jsp" method="post">
                    <button type="submit" class="btn">Checkout</button>
                </form>
                <form action="ClearCartServlet" method="post">
                    <button type="submit" class="btn">Clear Cart</button>
                </form>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>

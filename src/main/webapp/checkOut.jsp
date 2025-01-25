<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="com.foodapp.model.CartItem" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <!-- Add Font Awesome CDN for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh; /* Full viewport height */
        }

        /* Navbar Section */
        .navbar {
            background-color: #ff5722;  /* Orange background */
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: absolute;
            width: 100%;
            top: 0;
            left: 0;
        }

        .logo {
            font-size: 1.8rem;
            color: white;  /* White color for FoodApp text */
            font-weight: bold;
        }

        .profile-container {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-left: auto;  /* Aligns profile container to the right */
        }

        .user-icon {
            font-size: 1.8rem;
            color: white;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .user-icon:hover {
            transform: scale(1.1);
        }

        .username {
            color: white;
            font-size: 1rem;
        }

        /* Checkout Container */
        .container {
            width: 100%;
            max-width: 600px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 80px; /* To avoid navbar overlap */
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-bottom: 10px;
            font-weight: bold;
        }

        input, textarea, select {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1rem;
        }

        button {
            background-color: #ff5722;
            color: white;
            border: none;
            padding: 15px;
            font-size: 1rem;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }

        button:hover {
            background-color: #e64a19;
        }

        /* Back Button Section */
        .back-button {
            text-align: center;
            margin-top: 20px;
        }

        .back-button button {
            background-color: #777;
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 5px;
        }

        .back-button button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

   <div class="navbar">
    <div class="logo">
        <!-- Home Icon -->
       
        Delicious Food
    </div>
    
    <div class="profile-container">
     <a href="home.jsp" style="color: white; text-decoration: none;">
            <i class="fas fa-home user-icon"></i> <!-- Use 'user-icon' class for consistent size -->
        </a>
        <!-- User Icon -->
        <a href="UserLogindetailsjsp.jsp">
            <i class="fas fa-user user-icon"></i>
        </a>
        <% 
            // Retrieve the logged-in user's username from the session
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
</div>
   

    <!-- Checkout Form Section -->
    <div class="container">
        <h2>Checkout</h2>

        <form action="checkoutServlet" method="post">
            <!-- Get Address -->
            <label for="address">Delivery Address:</label>
            <textarea id="address" name="address" rows="4" required></textarea>

            <!-- Get Payment Method -->
            <label for="paymentMode">Payment Method:</label>
            <select id="paymentMode" name="paymentMode" required>
               <option value="UPI">UPI</option>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="Cash on Delivery">Cash on Delivery</option>
            </select>

            <!-- Grand Total Calculation -->
            <%
                Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
                double grandTotal = 0;
                if (cart != null) {
                    for (CartItem item : cart.values()) {
                        grandTotal += item.getQuantity() * item.getPrice();
                    }
                }
            %>

            <!-- Grand Total Display -->
            <label for="grandTotal">Grand Total:</label>
            <input type="text" id="grandTotal" name="grandTotal" readonly value="<%= grandTotal %>">

            <!-- Submit Button -->
            <button type="submit">Place Order</button>
             <!-- Back Button Section -->
    <div class="back-button">
        <button onclick="window.history.back()">Back to Cart</button>
    </div>
        </form>
    </div>

   

</body>
</html>

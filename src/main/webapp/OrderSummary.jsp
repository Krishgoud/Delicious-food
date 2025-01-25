<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Summary</title>
    
    <!-- Import Google Font: Roboto (Bold) -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap" rel="stylesheet">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Navbar Styling */
        .navbar {
            background-color: #ff5722;  /* Orange background */
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
        }

        .logo {
            font-size: 1.8rem;
            color: white;  /* White text for FoodApp */
            font-weight: bold;
        }

        .profile-container {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-left: auto;  /* Align profile container to the right */
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

        /* Main Container for Order Summary */
        .container {
            background: white;
            max-width: 600px;
            width: 100%;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
            margin-top: 70px;  /* Adjust for navbar height */
            box-sizing: border-box;  /* Ensure padding is included in width/height calculation */
        }

        h1 {
            color: #ff5722;  /* Orange color for header */
            font-size: 24px;
            margin-bottom: 20px;
            font-family: 'Roboto', sans-serif; /* Apply Roboto font */
            font-weight: bold;
        }

        p {
            margin: 10px 0;
            font-size: 16px;
            line-height: 1.5;
        }

        .summary-details {
            margin-top: 20px;
            font-family: 'Roboto', sans-serif; /* Apply Roboto font */
            font-weight: bold;  /* Make the text bold */
        }

        /* Confirm Button with Orange Color */
        .confirm-btn {
            display: inline-block;
            text-decoration: none;
            color: white;
            background-color: #ff5722;  /* Orange background */
            padding: 12px 25px;
            border-radius: 5px;
            font-size: 18px;
            text-align: center;
            margin-top: 20px;
            width: 100%;
            transition: background-color 0.3s;
            box-sizing: border-box;  /* Ensure padding is included in width calculation */
        }

        .confirm-btn:hover {
            background-color: #e64a19;  /* Darker shade of orange */
        }

        /* Back Button */
        .back-button {
            margin: 20px 0;
            text-align: center;
        }

        .back-button button {
            padding: 10px 20px;
            background-color: #ccc;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .back-button button:hover {
            background-color: #999;
        }

        /* Responsive design for mobile devices */
        @media (max-width: 768px) {
            .container {
                padding: 15px;
            }

            h1 {
                font-size: 20px;
            }

            .confirm-btn {
                padding: 10px 20px;
                font-size: 16px;
            }

            .back-button button {
                padding: 8px 16px;
                font-size: 14px;
            }
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


    <!-- Order Summary Container -->
    <div class="container">
        <h1>Order Summary</h1>
        <div class="summary-details">
            <p><strong>Order ID:</strong> <%= session.getAttribute("orderId") %></p>
            <p><strong>User ID:</strong> <%= session.getAttribute("userId") %></p>
            <p><strong>Delivery Address:</strong> <%= session.getAttribute("orderAddress") %></p>
            <p><strong>Payment Mode:</strong> <%= session.getAttribute("paymentMode") %></p>
            <p><strong>Grand Total:</strong> ₹<%= session.getAttribute("grandTotal") %></p>
        </div>

        <!-- Confirm Order Button -->
        <a href="conorder.jsp" class="confirm-btn">Confirm Order</a>

        <!-- Back to Cart Button -->
        <div class="back-button">
            <button  onclick="window.history.back()">Back </button>
        </div>
    </div>

    <!-- Include Font Awesome -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
</body>
</html>

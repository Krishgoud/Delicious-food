<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmation</title>
    <!-- Add Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        @keyframes popIn {
            0% { transform: scale(0.5); opacity: 0; }
            100% { transform: scale(1); opacity: 1; }
        }

        @keyframes ride {
            0% { left: -150px; }
            100% { left: 50%; }
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #FFF5E6;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            overflow: hidden;
        }
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
    position: absolute; /* Position relative to the screen */
    top: 10px; /* Adjust vertical positioning */
    right: 20px; /* Aligns the container to the right edge */
    display: flex;
    align-items: center;
    gap: 10px; /* Space between the icon and the username */
    z-index: 100; /* Ensure it stays above other elements */
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
    white-space: nowrap; /* Prevent text wrapping */
    text-overflow: ellipsis; /* Adds ellipsis for overflow text */
    overflow: hidden; /* Hide overflowing text */
    max-width: 150px; /* Adjust max width based on design */
}
        

        /* Header styling */
        .header {
            width: 100%;
            background-color: #FF5722;
            color: white;
            padding: 15px 20px;
            position: fixed;
            top: 0;
            left: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            z-index: 100;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);  /* Optional: Adds shadow for better visual */
        }

        .logo {
            font-size: 30px;
            font-weight: bold;
            color: white;
        }

        .header-icons {
            display: flex;
            gap: 20px;
            justify-content: flex-end;
            order: 1; /* Ensure icons are ordered correctly */
        }

        .header-icons a {
            color: white;
            font-size: 24px;
            text-decoration: none;
            display: flex; /* To ensure icons align well */
            justify-content: center;
            align-items: center;
        }

        
        .header-icons a:hover {
            color: #FF7043;
        }

        .confirmation-container {
            background-color: white;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(255, 140, 0, 0.2);
            padding: 40px;
            text-align: center;
            position: relative;
            width: 80%;
            max-width: 600px;
            animation: popIn 1s ease-out 1s forwards; /* Delay card pop-in by 1 second */
            opacity: 0;
            display: none; /* Initially hidden */
            margin-top: 120px; /* Give space for fixed header */
            margin-bottom: 50px;
        }

        .delivery-bike {
            position: absolute;
            bottom: 150px; /* Adjusted to ensure bike appears above the card */
            left: -150px;
            width: 100px;
            height: 60px;
            animation: ride 2s ease-out forwards; /* Bike moves in first */
        }

        .bike {
            position: absolute;
            bottom: 0;
            width: 100px;
            height: 60px;
            background-color: #FF5722;
            border-radius: 30px 10px 20px 20px;
        }

        .wheel {
            position: absolute;
            bottom: -10px;
            width: 30px;
            height: 30px;
            background-color: #333;
            border-radius: 50%;
            box-shadow: 0 0 0 5px #FF5722;
        }

        .wheel:first-child {
            left: 5px;
        }

        .wheel:last-child {
            right: 5px;
        }

        .speech-bubble { 
            position: absolute;
            top: 180px; /* Adjusted top to place bubble above the card */
            left: 56%; /* Horizontal centering */
            transform: translateX(-50%); /* Fine-tunes centering */
            background-color: #fff; /* White background for better contrast */
            border: 3px solid #4CAF50; /* Slightly thicker border for a cleaner look */
            border-radius: 25px; /* More rounded corners for a modern feel */
            padding: 20px; /* Increased padding for more space inside */
            width: 220px; /* Slightly wider bubble */
            text-align: center; /* Center the text inside the bubble */
            font-size: 16px; /* Increased font size for readability */
            font-weight: bold; /* Bold text for emphasis */
            color: #4CAF50; /* Green text color to match the border */
            opacity: 0;
            animation: popIn 1s ease-out 3s forwards; /* Delayed pop-in animation */
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2); /* Added shadow for a subtle 3D effect */
        }

        .speech-bubble::after {
            content: '';
            position: absolute;
            bottom: -18px; /* Adjusted arrow position to be closer */
            left: 50%;
            transform: translateX(-50%); /* Center the arrow */
            border-width: 18px 18px 0;
            border-style: solid;
            border-color: #4CAF50 transparent transparent; /* Matching the bubble color */
        }

        h1 {
            color: #FF5722;
            margin-bottom: 20px;
        }

        .order-message {
            color: #4CAF50;
            font-size: 18px;
            line-height: 1.5;
            margin-bottom: 30px;
        }

        .order-id {
            font-weight: bold;
            color: #FF5722;
        }

        .thank-you {
            font-style: italic;
            color: #FFA000;
        }

        .next-card {
            display: none;
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 140, 255, 0.2);
            text-align: center;
            max-width: 600px;
            margin-top: 30px;
            animation: popIn 1s ease-out 5s forwards; /* Show next card after delay */
        }

        .next-card h2 {
            color: #009688;
        }

        /* Buttons */
        .buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 40px;
            width: 80%;
            max-width: 600px;
        }
        .button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            text-decoration: none;
            color: white;
            background-color: #FF5722;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .button i {
            margin-right: 8px;
        }

        .button:hover {
            background-color: #FF7043;
            transform: translateY(-2px);
        }

        .button:active {
            background-color: #E64A19;
            transform: translateY(0);
        }

      .button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            text-decoration: none;
            color: white;
            background-color: #FF5722;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .button i {
            margin-right: 8px;
        }

        .button:hover {
            background-color: #FF7043;
            transform: translateY(-2px);
        }

        .button:active {
            background-color: #E64A19;
            transform: translateY(0);
        }
        @media (max-width: 600px) {
    .profile-container {
        right: 10px;
        top: 5px;
    }

    .username {
        max-width: 100px; /* Reduce max-width for smaller screens */
        font-size: 0.9rem;
    }

        
        }

    </style>
</head>
<body>
     <!-- Navbar Section -->
<div class="navbar">
    <div class="logo">Delicious Food</div>
    <div class="profile-container">
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

    <!-- Bike appears first -->
    <div class="delivery-bike">
        <div class="bike">
            <div class="wheel"></div>
            <div class="wheel"></div>
        </div>
    </div>

    <!-- Speech bubble -->
    <div class="speech-bubble">
        <h1>Order Confirmed!</h1>
    </div>
<!-- First Next Card -->
<div class="confirmation-container">
    <p class="order-message">
        Your order with order ID: <%= session.getAttribute("orderId") %> is placed.
        <br>
        <span class="thank-you">Thank you for your order!</span>
    </p>
    
  <div class="order-actions">
            <a href="cart.jsp" class="button" title="Cart">
                <i class="fas fa-shopping-cart"></i> Go to Cart
            </a>
            <a href="home.jsp" class="button" title="Home">
                <i class="fas fa-home"></i> Go to Home
            </a>
        </div>
    </div>

    <script>
        setTimeout(function() {
            document.querySelector('.delivery-bike').style.display = 'none';
            // Hide the bike after 3 seconds
        }, 3000);

        setTimeout(function() {
            document.querySelector('.speech-bubble').style.display = 'none';  // Hide the speech bubble after 4 seconds
        }, 6000);

        setTimeout(function() {
            document.querySelector('.confirmation-container').style.display = 'block';  // Show the confirmation card after 7 seconds
        }, 6000);
    </script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.User" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>

<%! 
    public User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");  // Correctly retrieve the 'User' object
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp - User Details</title>
    <style>
        /* Page Background */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #e3f2fd; /* Light Blue Background */
            color: #333;
        }

        /* Centered Container */
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* Card Shadow Effect */
            text-align: center;
        }

        h1 {
            color: #2196F3; /* Blue Color */
            font-size: 2.5rem;
        }

        p {
            font-size: 1.2rem;
            margin: 10px 0;
            color: #555;
        }

        .back-button {
            display: inline-block;
            padding: 12px 25px;
            background-color: #2196F3; /* Blue Button */
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            font-size: 1rem;
        }

        .back-button:hover {
            background-color: #1976D2; /* Darker Blue on Hover */
        }

        /* User Details Box */
        .user-details-box {
            padding: 20px;
            background-color: #f5f5f5; /* Light Grey Box Background */
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
            margin-top: 20px;
            text-align: left;
        }

        /* Flexbox for Equal Spacing */
        .user-details-box .detail {
            display: flex;
            justify-content: space-between;
            margin: 8px 0;
        }

        .detail strong {
            width: 150px; /* Fixed width for label (like Username, Email, Address) */
            font-weight: bold;
        }

        .detail span {
            flex-grow: 1; /* Makes the value section take up remaining space */
            text-align: left;
        }

    </style>
</head>
<body>

<div class="container">
    <h1>User Details</h1>

    <%-- Get user object from session --%>
    <%
        User user = getUserFromSession(request);
        if (user != null) {
    %>

        <div class="user-details-box">
            <div class="detail">
                <strong>Username:</strong>
                <span><%= user.getUserName() %></span>
            </div>
            <div class="detail">
                <strong>Email:</strong>
                <span><%= user.getEmail() %></span>
            </div>
            <div class="detail">
                <strong>Address:</strong>
                <span><%= user.getAddress() %></span>
            </div>
        </div>

    <% 
        } else {
    %>
        <p>No user data found. Please try again.</p>
    <% 
        }
    %>

    <!-- Back Button -->
    <a href="javascript:window.history.back();" class="back-button">Back</a>

</div>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Successful Login</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: url('images/bgg.jpg') no-repeat center center fixed;
            background-size: cover; /* Ensures the background fits the screen */
            background-position: center; /* Center the background image */
            background-attachment: fixed; /* Locks the background in place during scroll */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full viewport height */
            width: 100vw;  /* Full viewport width */
            color: #333;
        }

        .navbar {
            width: 100%;
            background-color: #333;
            padding: 10px 20px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
        }

        .user-icon {
            font-size: 1.5rem;
            color: white;
            background-color: #ffcc00;
            cursor: pointer;
            transition: transform 0.3s ease;
            padding: 10px;
            border-radius: 50%;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 9999;
        }

        .user-icon:hover {
            transform: scale(1.1);
        }
        
.container {
    background: rgba(255, 255, 255, 0.9);
    padding: 1.2rem;
    border-radius: 38px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    text-align: center;
    max-width: 310px;
    width: 90%;
    backdrop-filter: blur(5px); /* Optional: Add a blur effect for better contrast */
    margin-top: 1rem;
    margin-left: 5rem;
    margin-right: auto; /* Centers the container horizontally */
}



        h1 {
            color: #ff5722;
            font-size: 2rem;
            margin-bottom: 1.25rem;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
        }

        p {
            font-size: 1.125rem;
            line-height: 1.6;
            margin-bottom: 1.875rem;
        }

        .button {
            display: inline-block;
            background-color: #ff5722;
            color: white;
            text-decoration: none;
            padding: 0.75rem 1.5rem;
            border-radius: 50px;
            font-size: 1.125rem;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(255, 87, 34, 0.2);
        }

        .button:hover {
            background-color: #e64a19;
            transform: translateY(-2px);
            box-shadow: 0 6px 8px rgba(255, 87, 34, 0.3);
        }
    </style>
</head>
<body>

    <a href="UserLogindetailsjsp.jsp">
        <i class="fas fa-user user-icon"></i>
    </a>  

    <div class="container">
        <h1>Welcome Back, 
        <br>
        <% 
            String username = (String) session.getAttribute("username");
            if (username == null || username.isEmpty()) {
                username = "User";
            }
            out.print(username);
        %>!
        </h1>
        <p>You have logged in successfully.</p>
        <a href="home.jsp" class="button">Go to Dashboard</a>
        
                <a href="login.jsp" class="button">Back</a>
  
    </div>

</body>
</html>

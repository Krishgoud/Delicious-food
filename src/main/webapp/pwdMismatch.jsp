<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Mismatch</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fff3e0; /* Light orange background for warning */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background: #ffffff; /* White background for the message box */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 24px;
            color: #ff9800; /* Orange color indicating warning */
            font-weight: bold;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            color: #757575; /* Neutral text color */
            margin-bottom: 20px;
        }

        a {
            display: inline-block;
            background-color: #1e88e5; /* Blue button */
            color: #ffffff;
            padding: 12px 20px;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
        }

        a:hover {
            background-color: #1565c0; /* Darker blue on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Password Mismatch!</h1>
        <p>The passwords you entered do not match. Please try again.</p>
        <a href="register.jsp">Click here to go back</a>
    </div>
</body>
</html>

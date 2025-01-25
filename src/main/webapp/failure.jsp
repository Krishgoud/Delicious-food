<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Failed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #ffebee; /* Light red background for failure */
            margin: 0;
            padding: 50px;
        }
        h1 {
            color: #f44336; /* Red color indicating failure */
            font-weight: bold;
        }
        p {
            font-size: 18px;
            color: #333; /* Neutral text color */
        }
        a {
            display: inline-block;
            background-color: #1e88e5; /* Blue button */
            color: white;
            padding: 12px 20px;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
            margin-top: 20px;
            cursor: pointer;
        }
        a:hover {
            background-color: #1565c0; /* Darker blue on hover */
        }
    </style>
</head>
<body>
    <h1>Registration Failed!</h1>
    <p>There was an error while processing your request. Please try again later.</p>
    <a href="register.jsp">Back to Registration</a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e8f7e8; /* Light green background for success */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background: #ffffff; /* White background for message box */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 28px;
            color: #28a745; /* Green color indicating success */
            font-weight: bold;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            color: #333; /* Neutral text color */
            margin-top: 10px;
        }

        .button {
            display: inline-block;
            background-color: #28a745; /* Green for button */
            color: white;
            padding: 12px 20px;
            text-decoration: none;
            font-size: 16px;
            border-radius: 5px;
            margin-top: 20px;
            cursor: pointer;
        }

        .button:hover {
            background-color: #218838; /* Darker green on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Registration Successful</h1>
        <p>Your registration was successful. You can now proceed to the  log in.</p>
        <a href="login.jsp" class="button">Go to Login</a>
        
    </div>
</body>
</html>

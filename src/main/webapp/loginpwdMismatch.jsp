<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Mismatch</title>
    <style>
        :root {
            --primary-color: #ff9800;
            --secondary-color: #1e88e5;
            --background-color: #fff3e0;
            --text-color: #333;
            --error-color: #d32f2f;
            --box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1), 0 1px 3px rgba(0, 0, 0, 0.08);
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            line-height: 1.6;
        }

        .container {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: var(--box-shadow);
            text-align: center;
            max-width: 90%;
            width: 400px;
            animation: fadeIn 0.5s ease-out;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h1 {
            font-size: 1.8rem;
            color: var(--error-color);
            margin-bottom: 1rem;
        }

        p {
            font-size: 1rem;
            color: var(--text-color);
            margin-bottom: 1.5rem;
        }

        a {
            display: inline-block;
            padding: 0.5rem 1rem;
            background-color: var(--secondary-color);
            color: white;
            text-decoration: none;
            border-radius: 4px;
            font-weight: 500;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        a:hover, a:focus {
            background-color: #1565c0;
            transform: translateY(-2px);
        }

        .icon {
            font-size: 3rem;
            color: var(--error-color);
            margin-bottom: 1rem;
        }

        @media (max-width: 480px) {
            .container {
                padding: 1.5rem;
            }

            h1 {
                font-size: 1.5rem;
            }

            p {
                font-size: 0.9rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="icon">&#9888;</div>
        <h1>Password Mismatch!</h1>
        <p>The password you entered do not match. Please try again.</p>
        <a href="login.jsp">Go Back to Login</a>
    </div>
</body>
</html>
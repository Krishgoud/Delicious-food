<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp - Registration</title>
    <style>
:root {
    --primary-color: #4a90e2;
    --secondary-color: #f5a623;
    --background-color: #f0f2f5;
    --text-color: #333;
    --form-background: #ffffff;
    --error-color: #e74c3c;
    --success-color: #2ecc71;
    --input-border: #dcdde1;
    --input-focus-border: #4a90e2;
}

body {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
    color: var(--text-color);
}

.form-container {
    background-color: var(--form-background);
    padding: 1.5rem;
    border-radius: 12px;
    box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
    width: 100%;
    max-width: 350px;
    animation: fadeIn 0.5s ease-in-out;
}

h2 {
    text-align: center;
    color: var(--primary-color);
    margin-bottom: 1rem;
    font-size: 1.5rem;
    font-weight: bold;
}

form {
    display: flex;
    flex-direction: column;
}

label {
    font-size: 0.85rem;
    font-weight: 500;
    margin-bottom: 0.4rem;
    color: var(--text-color);
}

input {
    margin-bottom: 1rem;
    padding: 0.7rem;
    border: 1px solid var(--input-border);
    border-radius: 6px;
    font-size: 0.9rem;
    background: #f9f9f9;
    transition: border-color 0.3s, box-shadow 0.3s;
}

input:focus {
    outline: none;
    border-color: var(--input-focus-border);
    box-shadow: 0 0 4px rgba(74, 144, 226, 0.4);
}

button {
    background-color: var(--primary-color);
    color: white;
    border: none;
    padding: 0.7rem;
    border-radius: 6px;
    font-size: 0.9rem;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s, transform 0.2s;
}

button:hover {
    background-color: #3a7bc8;
    transform: scale(1.02);
}

button:active {
    background-color: #2d65a2;
}

.link {
    text-align: center;
    margin-top: 0.8rem;
}

.link a {
    color: var(--primary-color);
    text-decoration: none;
    font-weight: 500;
    transition: color 0.3s;
}

.link a:hover {
    color: var(--secondary-color);
}

@media (max-width: 480px) {
    .form-container {
        padding: 1.2rem;
    }

    h2 {
        font-size: 1.3rem;
    }

    button {
        font-size: 0.85rem;
    }
}

@keyframes fadeIn {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

</style>
</head>
<body>
    <div class="form-container">
        <h2>FoodApp Registration</h2>
        <form action="/foodapp/Register" method="POST">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>
            
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
            <label for="cpassword">Confirm Password</label>
            <input type="password" id="cpassword" name="cpassword" placeholder="Confirm your password" required>
            
            <label for="address">Address</label>
            <input type="text" id="address" name="address" placeholder="Enter your address" required>
            
            <button type="submit">Register</button>
        </form>
        <div class="link">
            <p>Already have an account? <a href="login.jsp">Log in here</a></p>
        </div>
    </div>
</body>
</html>
    
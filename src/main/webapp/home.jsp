<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>
<%@ page import="com.foodapp.daoimpl.RestaurantDaoImpl" %>
<%@ page import="com.foodapp.daoimpl.MenuDaoImpl" %>
<%@ page import="com.foodapp.model.menu" %>
<%@ page import="java.util.stream.Collectors" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodApp - Restaurant List</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        /* Global Styles */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        /* Header background color */
.navbar {
    background-color: #ff5722; /* Orange color */
    color: white;
}

/* Restaurant name color in cards */
.restaurant-card h2 {
    font-size: 1.4rem;
    margin: 15px;
    color: #ff5722; /* Orange color */
}
        

       .navbar {
    background-color: #ff5722; /* Orange background */
    color: white;
    padding: 0rem 1.5rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: sticky;
    top: 0;
    z-index: 1000;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}
       
        
        .logo {
            font-size: 1.8rem;
            font-weight: bold;
            color: white;
        }

     .top-section {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px;
    width: 100%;
}
        

        .back-button {
            display: flex;
        }

        .back-button a {
            display: inline-block;
            background-color: #ff5722;
            color: white;
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 50px;
            font-size: 1rem;
            font-weight: bold;
            transition: all 0.3s ease;
            box-shadow: 0 4px 6px rgba(255, 87, 34, 0.2);
        }

        .back-button a:hover {
            background-color: #e64a19;
            transform: translateY(-2px);
            box-shadow: 0 6px 8px rgba(255, 87, 34, 0.3);
        }

      .search-form {
    display: flex;
    align-items: center;
    background-color: #444;
    border-radius: 5px;
    padding: 6px 10px;
    box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.2);
    max-width: 600px;
    flex-grow: 1;
    margin-right: 20px; /* Added space between search form and user icon */
}
      .profile-container {
    display: flex;
    align-items: center;
    gap: 15px;
}

.profile-container a {
    display: inline-block;
    cursor: pointer;
}
.search-form {
    display: flex;
    align-items: center;
    background-color: white;  /* Changed background to white */
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);  /* Added shadow for better focus */
    padding: 0.5rem;  /* Adjusted padding for better alignment */
}

.search-form input {
    border: none;
    outline: none;
    font-size: 1rem;
    flex: 1;
    padding: 0.4rem;
    border-radius: 5px 0 0 5px;
}

.search-form button {
    background-color: #ff5722;  /* Orange background for button */
    color: white;
    border: none;
    border-radius: 0 5px 5px 0;
    padding: 0.5rem 1rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.search-form button:hover {
    background-color: #f39c12;  /* Hover effect on button */
}


        .restaurants-container {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 15px;
            padding: 20px;
            justify-content: center;
            align-items: center;
        }

        .restaurant-card {
            background-color: white;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.3s ease;
            text-decoration: none;
            color: inherit;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .restaurant-card:hover {
            transform: scale(1.05);
        }

        .restaurant-card h2 {
            font-size: 1.4rem;
            margin: 15px;
        }

        .restaurant-card p {
            margin: 0 15px 15px;
            font-size: 0.9rem;
        }

        .restaurant-card .cta-buttons {
            display: flex;
            justify-content: space-between;
            padding: 10px 15px;
        }

        .restaurant-card .cta-buttons a {
            text-decoration: none;
            color: white;
            background-color: #ff5722;
            padding: 5px 10px;
            font-size: 14px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .restaurant-card .cta-buttons a:hover {
            background-color: #e64a19;
        }

        .cuisine-buttons form {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin-top: 20px;
        }

        .cuisine-buttons button {
            background-color: #ff5722;
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 1rem;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .cuisine-buttons button:hover {
            background-color: #e64a19;
        }
          .card-content {
            padding: 15px;
        }

        .card-content h2 {
            font-size: 1.5rem;
            margin: 0;
            color: #ff5722;
        }

        .card-content p {
            margin: 8px 0;
            font-size: 0.95rem;
            color: #555;
        }
             footer {
            background-color: var(--primary-color);
            color: #ff5722;
            text-align: center;
            padding: 1rem;
        }

        @media (max-width: 1024px) {
            .restaurants-container {
                grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            }
        }

        @media (max-width: 768px) {
            .restaurants-container {
                grid-template-columns: repeat(auto-fill, minmax(100%, 1fr));
            }
        }

        @media (max-width: 480px) {
            .navbar {
                flex-direction: column;
                align-items: flex-start;
            }

            .restaurants-container {
                flex-direction: column;
                align-items: center;
            }

            .restaurant-card {
                width: 90%;
            }

            .top-section {
                flex-direction: column;
                align-items: flex-start;
            }
       
            
        }
    </style>
</head>
<body>

   <div class="navbar">
    <div class="logo">Delicious Food</div>
     <div class="top-section">
        <form action="home.jsp" method="get" class="search-form">
            <input type="text" name="searchQuery" placeholder="Search for Restaurants..." required>
            <button type="submit">
                <i class="fas fa-search"></i>
            </button>
        </form>
    </div>
    
    <div class="profile-container" style="display: flex; align-items: center; gap: 15px;">
        <a href="UserLogindetailsjsp.jsp">
            <i class="fas fa-user user-icon" style="font-size: 1.8rem; color: white; cursor: pointer; transition: transform 0.3s ease;"></i>
        </a> 
        <% 
            // Retrieve the logged-in user's username from the session
            String loggedInUsername = (String) session.getAttribute("username");
            if (loggedInUsername != null) {
        %>
                 <%= loggedInUsername %>
        <% 
            } 
            else {
        %>
            <span class="username" style="color: white; font-size: 1rem;">Hello, Guest</span>
        <% 
            }
        %>
    </div>
</div>
   
  <div class="cuisine-buttons">
        <form method="get" action="restaurantList.jsp">
                <button type="submit" name="cuisine" value="Italian"><i class="fas fa-pizza-slice"></i> Italian</button>
                <button type="submit" name="cuisine" value="Chinese"><i class="fas fa-bowl-rice"></i> Chinese</button>
                <button type="submit" name="cuisine" value="Indian"><i class="fas fa-utensils"></i> Indian</button>
                <button type="submit" name="cuisine" value="Mexican"><i class="fas fa-taco"></i> Mexican</button>
                <button type="submit" name="cuisine" value="American"><i class="fas fa-hamburger"></i> American</button>
                <button type="submit" name="cuisine" value="Japanese"><i class="fas fa-sushi"></i> Japanese</button>
                <button type="submit" name="cuisine" value="Vegan"><i class="fas fa-leaf"></i> Vegan</button>
            </form>
         
        <div class="back-button">
            <a href="loginSuccess.jsp">Back</a>
        </div>
    </div>
    <div class="restaurants-container">
        <% 
            String searchQuery = request.getParameter("searchQuery");
            RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
            List<Restaurant> restaurants = null;
            MenuDaoImpl menuDao = new MenuDaoImpl();
            List<menu> foodItems = null;
            try {
                restaurantDao.connect();
                restaurants = restaurantDao.getAllRestaurants();
                foodItems = menuDao.searchFoodItemsByName(searchQuery);

                if (searchQuery != null && !searchQuery.isEmpty()) {
                    restaurants = restaurants.stream()
                        .filter(r -> r.getName().toLowerCase().contains(searchQuery.toLowerCase()) || 
                                     r.getCuisineType().toLowerCase().contains(searchQuery.toLowerCase()))
                        .collect(Collectors.toList());
                }

                if (restaurants != null && !restaurants.isEmpty()) {
                    for (Restaurant restaurant : restaurants) {
        %>
                    <div class="restaurant-card">
                    
                        <a href="showMenu?restaurantId=<%= restaurant.getRestaurantID() %>&restaurantName=<%= restaurant.getName() %> " class="restaurant-card">
                             <div class="card-content">
                            <h2><%= restaurant.getName() %></h2>
                            <p><strong>Cuisine:</strong> <%= restaurant.getCuisineType() %></p>
                            <p><strong>Location:</strong> <%= restaurant.getAddress() %></p>
                            
                            <p><strong>Rating:</strong> ⭐ <%= restaurant.getRatings() %>/5</p>
                        </div>
                        </a>
                        <div class="cta-buttons">
                            <a href="AddToFavoritesServlet?restaurantId=<%= restaurant.getRestaurantID() %>">Add to Favorites</a>
                            <a href="showMenu?restaurantId=<%= restaurant.getRestaurantID() %>&restaurantName=<%= restaurant.getName() %>">View Menu</a>                
                        </div>  
                    </div>
                    <% 
                    }
                } else {
                    out.println("<p>No restaurants available matching your search criteria.</p>");
                }
            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                restaurantDao.closeConnection();
            }
        %>
    </div>
     <footer>
        <p>&copy; 2024 FoodApp. All rights reserved.</p>
    </footer>

</body>
</html>

package com.foodapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.foodapp.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the menuID from the request
        String menuIdStr = request.getParameter("menuID");
        
        if (menuIdStr != null && !menuIdStr.isEmpty()) {
            try {
                int menuId = Integer.parseInt(menuIdStr);
                
                // Logic to add the item to the cart
                // For example, retrieve menu item from the database and add it to the cart
                // Assuming cart is stored in the session:
                HttpSession session = request.getSession();
                Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
                if (cart == null) {
                    cart = new HashMap<>();
                }
                
                // Assuming CartItem is a class representing a cart item
                CartItem item = new CartItem(menuId);
                cart.put(menuId, item);
                session.setAttribute("cart", cart);
                
                // Redirect to the cart page or display success
                response.sendRedirect("cart.jsp");

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID format");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Menu ID is required");
        }
    }
}



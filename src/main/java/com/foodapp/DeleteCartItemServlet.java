package com.foodapp;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.foodapp.model.CartItem;

public class DeleteCartItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        // Retrieve and validate the itemId parameter
        String itemIdParam = request.getParameter("itemId");
        if (itemIdParam == null || itemIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Item ID is missing or invalid.");
            return;
        }

        try {
            int itemId = Integer.parseInt(itemIdParam);

            if (cart != null) {
                cart.remove(itemId); // Remove the item from the cart
                session.setAttribute("cart", cart); // Update the session cart
            }

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid item ID format.");
        }
    }
}

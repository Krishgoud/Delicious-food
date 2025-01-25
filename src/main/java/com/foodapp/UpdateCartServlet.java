package com.foodapp;

import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.foodapp.model.CartItem;

public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve cart from session
        @SuppressWarnings("unchecked")
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");

        if (cart != null) {
            // Get the item ID and new quantity from the request
            String itemIdStr = req.getParameter("itemId");
            String quantityStr = req.getParameter("quantity");

            try {
                int itemId = Integer.parseInt(itemIdStr);
                int quantity = Integer.parseInt(quantityStr);

                // Update the quantity of the cart item
                CartItem item = cart.get(itemId);
                if (item != null) {
                    item.setQuantity(quantity);
                }

                // Update the session with the modified cart
                req.getSession().setAttribute("cart", cart);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Invalid input. Please provide valid item ID and quantity.");
            }
        }

        // Redirect back to the cart page
        resp.sendRedirect("cart.jsp");
    }
}

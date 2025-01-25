package com.foodapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.foodapp.daoimpl.MenuDaoImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // Parse menuID from the request
            int menuID = Integer.parseInt(request.getParameter("menuID"));

            // Fetch the item details from the database using menuID
            MenuDaoImpl menuDao = new MenuDaoImpl();
            menu menuItem = menuDao.fetchOne(menuID); // Assuming you have a method `fetchOne` in `MenuDaoImpl`

            if (menuItem == null) {
                // Handle case where menu item is not found
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Menu item not found");
                return;
            }

            // Get the cart from the session or create a new one if it doesn't exist
            HttpSession session = request.getSession();
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<>();
                session.setAttribute("cart", cart);
            }

            // Get quantity from the request, default to 1 if not provided
            int quantity = 1;
            if (request.getParameter("quantity") != null) {
                try {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                } catch (NumberFormatException e) {
                    // If quantity is invalid, default it to 1
                    quantity = 1;
                }
            }

            // Add the item to the cart with the specified quantity
            if (cart.containsKey(menuID)) {
                // If item already exists in the cart, update the quantity
                CartItem existingItem = cart.get(menuID);
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
            } else {
                // Create a new cart item with the given quantity
                CartItem newItem = new CartItem();
                newItem.setImagePath(menuItem.getImagepath());
                newItem.setMenuID(menuItem.getMenuID());
                newItem.setName(menuItem.getName());
                newItem.setPrice(menuItem.getPrice());
                newItem.setQuantity(quantity);
                cart.put(menuID, newItem);
            }

            // Redirect to the cart page
            response.sendRedirect("cart.jsp");
        } catch (NumberFormatException e) {
            // Handle invalid menuID
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid menu ID");
        } catch (Exception e) {
            // Handle general errors
            throw new ServletException("Error adding item to cart", e);
        }
    }
}

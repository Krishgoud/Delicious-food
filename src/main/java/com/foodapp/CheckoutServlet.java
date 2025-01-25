package com.foodapp;

import java.io.IOException;
import java.util.Map;

import com.foodapp.daoimpl.OrderDaoImpl;
import com.foodapp.daoimpl.OrderItemDaoImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch form data
            String address = request.getParameter("address");
            String paymentMode = request.getParameter("paymentMode");
            String grandTotalStr = request.getParameter("grandTotal");


            

            // Validate input
            if (address == null || paymentMode == null || grandTotalStr == null || grandTotalStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input data");
                return;
            }

            // Parse the grand total
            double grandTotal = Double.parseDouble(grandTotalStr);


            // Retrieve session and validate user
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
                return;
            }

            int userId = (int) session.getAttribute("userId");
            String username = (String) session.getAttribute("username");
            if (username == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Username not found in session");
                return;
            }

            // Set default restaurant ID (assuming one restaurant for simplicity)
            int restaurantId = 1;

            // Create order object and save using DAO
            Order order = new Order(0, userId, restaurantId, grandTotal, "Pending", paymentMode, address);
            OrderDaoImpl orderDao = new OrderDaoImpl();
            int orderId = orderDao.insert(order);

            if (orderId <= 0) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to insert order");
                return;
            }

            // Retrieve cart and process order items
            @SuppressWarnings("unchecked")
            Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
            if (cart != null && !cart.isEmpty()) {
                OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
                for (CartItem cartItem : cart.values()) {
                    double itemTotal = cartItem.getQuantity() * cartItem.getPrice();
                    OrderItem orderItem = new OrderItem(0, orderId, cartItem.getMenuID(), cartItem.getQuantity(), itemTotal);
                    orderItemDao.insert(orderId, orderItem);
                }
            }

            // Clear the cart after successful order placement
            session.removeAttribute("cart");

            // Log order details for tracking
            System.out.println("Order placed successfully by user: " + username + " (User ID: " + userId + ")");

            // Redirect to order summary page
            session.setAttribute("orderId", orderId);
            session.setAttribute("UserId", userId);
            session.setAttribute("Username", username);
            session.setAttribute("orderAddress", address);
            session.setAttribute("paymentMode", paymentMode);
            session.setAttribute("grandTotal", grandTotal);
            response.sendRedirect("OrderSummary.jsp");

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid grand total value");
        } catch (Exception e) {
            throw new ServletException("Error processing the order", e);
        }
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.foodapp.model.Restaurant" %>

<%
    // Get the restaurant ID to remove
    int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

    // Retrieve the existing favorites list from the session
    List<Restaurant> favorites = (List<Restaurant>) session.getAttribute("favorites");
    if (favorites != null) {
        favorites.removeIf(restaurant -> restaurant.getId() == restaurantId);
    }

    // Save the updated list back to the session
    session.setAttribute("favorites", favorites);

    // Redirect back to the favorites page
    response.sendRedirect("favorites.jsp");
%>

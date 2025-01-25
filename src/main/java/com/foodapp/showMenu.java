package com.foodapp;

import java.io.IOException;
import java.util.List;

import com.foodapp.DButil.MenuDBconnection;
import com.foodapp.daoimpl.MenuDaoImpl;
import com.foodapp.model.menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class showMenu extends HttpServlet {
    private MenuServices menuService;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize menuService using MenuDBConnection.connect()
            menuService = new MenuServices(new MenuDaoImpl(MenuDBconnection.connect()));
        } catch (Exception e) {
            throw new ServletException("Failed to initialize MenuService due to database connection error.", e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the restaurant ID from the request parameter
        String restaurantIdParam = req.getParameter("restaurantId");
        String restaurantName = req.getParameter("restaurantName");


        // Ensure that the parameter is present and valid
        if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
            req.setAttribute("errorMessage", "Restaurant ID is missing.");
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
            return;
        }

        try {
            int restaurantId = Integer.parseInt(restaurantIdParam);
         // Assuming you have already parsed restaurantId from the request
            req.setAttribute("RestaurantID", restaurantId);



            // Fetch menu list using the service
            List<menu> menuList = menuService.fetchmenubyRestaurantID(restaurantId);

            // Set the menu list as a request attribute
            req.setAttribute("menuList", menuList);
            req.setAttribute("restaurantName", restaurantName);
            


            // Forward the request to the JSP page to display the menu
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid Restaurant ID format.");
            req.getRequestDispatcher("menu.jsp").forward(req, resp);
        }
    }
}

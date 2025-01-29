package com.foodapp;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.foodapp.daoimpl.UserDaoImpl;
import com.foodapp.model.User;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDaoImpl userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDaoImpl();
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Email and Password must not be empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            for (User user : userDao.fetchAll()) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                   
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", user.getUserId()); // Store userId in session
                    session.setAttribute("username", user.getUserName()); 
                    System.out.println("User ID: " + session.getAttribute("userId"));
                    System.out.println("Username: " + session.getAttribute("username"));
// Ensure this sets the correct value
                    response.sendRedirect("home.jsp");
                    return;
                }
            }
            request.setAttribute("errorMessage", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing your request.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}

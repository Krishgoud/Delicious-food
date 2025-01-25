package com.foodapp;

import java.io.IOException;
import com.foodapp.model.User;
import com.foodapp.daoimpl.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        // Retrieve user input
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String cpassword = req.getParameter("cpassword");
        String email = req.getParameter("email");
        String address = req.getParameter("address");

        // Validate passwords
        if (!password.equals(cpassword)) {
            resp.sendRedirect("pwdMismatch.html");
            return;
        }

        // Create user object with plain data
        User user = new User(username, password, email, address);

        // Call UserDaoImpl to save the user to the database
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isUserSaved = userDao.saveUser(user);

        if (isUserSaved) {
            // Store user data in session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            // Redirect to confirmation page
            resp.sendRedirect("confirmRegistration.jsp");
        } else {
            // If saving failed, redirect to error page
            resp.sendRedirect("error.jsp");
        }
    }
}

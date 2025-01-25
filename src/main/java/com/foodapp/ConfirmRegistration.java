package com.foodapp;

import java.io.IOException;

import com.foodapp.model.User;
import com.foodapp.daoimpl.UserDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ConfirmRegistration extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User encryptedUser = (User) session.getAttribute("encryptedUser");

        if (encryptedUser == null) {
            resp.sendRedirect("register.jsp");
            return;
        }

        User user = new User(
            decrypt(encryptedUser.getUserName()),
            decrypt(encryptedUser.getPassword()),
            decrypt(encryptedUser.getEmail()),
            decrypt(encryptedUser.getAddress())
        );

        // Insert user into the database
        UserDaoImpl userDAO = new UserDaoImpl();
        int result = userDAO.insert(user);

        // Clear session
        session.removeAttribute("encryptedUser");

        // Redirect based on result
        if (result > 0) {
            resp.sendRedirect("success.jsp");
        } else {
            resp.sendRedirect("failure.jsp");
        }
    }

    private String decrypt(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) (str.charAt(i) - 2)); // Reverse shift each character by 2
        }
        return sb.toString();
    }
}

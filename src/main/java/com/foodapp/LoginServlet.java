package com.foodapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foodapp.daoimpl.RestaurantDaoImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/foodapp";
    private static final String DB_UN = "root";
    private static final String DB_PWD = "root";
    private static final String CHECK_EMAIL_QUERY = "SELECT * FROM USER WHERE EMAIL = ?";

    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet res;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, DB_UN, DB_PWD);
            
            pstmt = con.prepareStatement(CHECK_EMAIL_QUERY);
            pstmt.setString(1, email);
            res = pstmt.executeQuery();
            
            if (res.next()) {
                String dbPassword = res.getString("password");
                String decryptedPassword = (dbPassword);

                // Compare the decrypted password with the entered password
                if (password.equals(decryptedPassword)) {
                    String dbUsername = res.getString("username"); // Assuming column is 'username'
                    int dbUserId = res.getInt("userid");
                    String dbEmail = res.getString("email");
                    String dbAddress = res.getString("Address");
                    
                    HttpSession session = req.getSession();
                    session.setAttribute("userId", dbUserId); // Set userId in session
                    session.setAttribute("username", dbUsername);
                    session.setAttribute("email", dbEmail); // Set email in session
                    session.setAttribute("address", dbAddress); // Set address in session

                    // Set the entire User object in session
                    User user = new User(dbUsername, dbPassword, dbEmail, dbAddress);
                    session.setAttribute("user", user);
                    System.out.println("Entered Password: " + password);
                    System.out.println("Decrypted Password: " + decryptedPassword);

                    // Fetch all restaurants and pass to success page
                    RestaurantDaoImpl restaurantService = new RestaurantDaoImpl();
                    restaurantService.connect(); 
                    req.setAttribute("rlist", restaurantService.fetchAll());
                    req.getRequestDispatcher("loginSuccess.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("loginpwdMismatch.jsp"); // Redirect if password mismatch
                }
            } else {
                resp.sendRedirect("loginInvalidUser.jsp"); // Redirect if no user found
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("loginError.jsp"); // Redirect to error page on exception
        } finally {
            try {
                if (res != null) res.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


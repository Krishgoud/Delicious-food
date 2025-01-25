package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import com.foodapp.model.Order;
import com.foodapp.DButil.MenuDBconnection;

public class OrderDaoImpl {

    private static final String INSERTQUERY = "INSERT INTO orders ( UserID, RestaurantID, GrandTotal, Status, PaymentMode ,ADDRESS) VALUES (?, ?, ?, ?, ?,?)";
    private static final String FETCHALL = "SELECT * FROM orders";
    private static final String FETCHONE = "SELECT * FROM orders WHERE OrderID = ?";
    private static final String UPDATE = "UPDATE orders SET Status = ?, PaymentMode = ? WHERE OrderID = ?";
    private static final String DELETE = "DELETE FROM orders WHERE OrderID = ?";

    private Connection con;

    // Constructor to initialize connection
    public OrderDaoImpl() {
        this.con = MenuDBconnection.connect();
        if (this.con == null) {
            throw new RuntimeException("Failed to establish database connection.");
        }
    }
    public  int insert(Order order) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(INSERTQUERY, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserID());
            stmt.setInt(2, order.getRestaurantID());
            stmt.setDouble(3, order.getGrandTotal());
            stmt.setString(4, order.getStatus());
            stmt.setString(5, order.getPaymentMode());
            stmt.setString(6, order.getAddress());

            int affectedRows = stmt.executeUpdate();
            ResultSet res1 = stmt.getGeneratedKeys();
            int orderId = 0;
            while(res1.next()) {
            	orderId=res1.getInt(1);
            }
            return orderId;
            
            
            //if (affectedRows == 0) {
              //  throw new SQLException("Creating order failed, no rows affected.");
            //}

            //try (ResultSet res = stmt.getGeneratedKeys()) {
                //if (res.next()) {
                  // order.setOrderID(res.getInt(1)); // Return the generated order ID
                //} else {
               //     throw new SQLException("Creating order failed, no ID obtained.");
              //  }
            //}
            //catch(SQLException e) {
            	//throw new RuntimeException("Erroe  adding Order",e);
            //}
        

    }
    }


    // Fetch all Orders
    public ArrayList<Order> fetchAll() {
        ArrayList<Order> orderList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCHALL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                orderList.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("UserID"),
                        rs.getInt("RestaurantID"),
                        rs.getDouble("TotalAmount"),
                        rs.getString("Status"),
                        rs.getString("PaymentMode")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching orders: " + e.getMessage());
            e.printStackTrace();
        }
        return orderList;
    }

    // Fetch Order by ID
    public Order fetchOne(int orderId) {
        Order order = null;
        try {
            // Query to fetch the order details
            String sql = "SELECT * FROM orders WHERE orderid = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order();
                order.setOrderID(rs.getInt("OrderId"));
                order.setUserID(rs.getInt("UserId"));
                order.setRestaurantID(rs.getInt("Restaurantid"));
                order.setGrandTotal(rs.getDouble("GrandTotal"));
                order.setPaymentMode(rs.getString("Paymentmode"));
                order.setStatus(rs.getString("Status"));
                order.setAddress(rs.getString("Address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    // Update Order
    public int update(int orderID, String status, String paymentMode) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
            pstmt.setString(1, status);
            pstmt.setString(2, paymentMode);
            pstmt.setInt(3, orderID);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating order: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    // Delete Order
    public int delete(int orderID) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE)) {
            pstmt.setInt(1, orderID);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting order: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    // Close connection (optional if using connection pooling)
    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
	
}

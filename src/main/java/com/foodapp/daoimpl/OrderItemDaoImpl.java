package com.foodapp.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.foodapp.model.OrderItem;
import com.foodapp.DButil.OrderItemDBConnection;

public class OrderItemDaoImpl {
    private static final String INSERT_QUERY = "INSERT INTO orderitems (OrderID, MenuID, Quantity, ItemTotal) VALUES (?, ?, ?, ?)";
    private static final String FETCH_ALL_QUERY = "SELECT * FROM orderitems";
    private static final String FETCH_BY_ID_QUERY = "SELECT * FROM orderitems WHERE OrderItemID = ?";
    private static final String FETCH_BY_ORDER_ID_QUERY = "SELECT * FROM orderitems WHERE OrderID = ?";
    private static final String UPDATE_QUERY = "UPDATE orderitems SET MenuID = ?, Quantity = ?, ItemTotal = ? WHERE OrderItemID = ?";
    private static final String DELETE_QUERY = "DELETE FROM orderitems WHERE OrderItemID = ?";

    private Connection con;

    // Constructor initializes the database connection
    public OrderItemDaoImpl() {
        try {
            this.con = OrderItemDBConnection.connect();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish database connection");
        }
    }

    // Create (Insert) a new OrderItem
    public int insert(int OrderId,OrderItem orderItem) {
        try (PreparedStatement pstmt = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, OrderId);
            pstmt.setInt(2, orderItem.getMenuID());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setDouble(4, orderItem.getItemTotal());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return generated OrderItemID
                    }
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Read (Fetch) all OrderItems
    public List<OrderItem> fetchAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_ALL_QUERY);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                orderItems.add(mapResultSetToOrderItem(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }

    // Read (Fetch) a single OrderItem by its ID
    public OrderItem fetchOne(int orderItemID) {
        try (PreparedStatement pstmt = con.prepareStatement(FETCH_BY_ID_QUERY)) {
            pstmt.setInt(1, orderItemID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToOrderItem(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read (Fetch) all OrderItems for a specific OrderID
    public List<OrderItem> fetchByOrderId(int orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orderitems WHERE orderid = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setOrderItemID(rs.getInt("OrderItemId"));
                item.setOrderID(rs.getInt("OrderId"));
                item.setMenuID(rs.getInt("MenuId"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setItemTotal(rs.getInt("ItemTotal"));
                orderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }


    // Update an existing OrderItem
    public int update(OrderItem orderItem) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_QUERY)) {
            pstmt.setInt(1, orderItem.getMenuID());
            pstmt.setInt(2, orderItem.getQuantity());
            pstmt.setDouble(3, orderItem.getItemTotal());
            pstmt.setInt(4, orderItem.getOrderItemID());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Delete an OrderItem by its ID
    public int delete(int orderItemID) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_QUERY)) {
            pstmt.setInt(1, orderItemID);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Helper method to map ResultSet to OrderItem object
    private OrderItem mapResultSetToOrderItem(ResultSet rs) throws SQLException {
        return new OrderItem(
            rs.getInt("OrderItemID"),
            rs.getInt("OrderID"),
            rs.getInt("MenuID"),
            rs.getInt("Quantity"),
            rs.getDouble("ItemTotal")
        );
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

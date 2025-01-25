package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.foodapp.DButil.OrderItemDBConnection;
import com.foodapp.Interfaces.OrderHistoryInterface;
import com.foodapp.main.OrderHistory;

public class OrderHistoryDaiImpl  implements  OrderHistoryInterface
{
	 private static final String INSERT = "INSERT INTO orderhistory (OrderHistoryId, OrderID, UserId, TotalAmount, Status) VALUES (?, ?, ?, ?, ?)";
	    private static final String FETCHALL = "SELECT * FROM orderhistory";
	    private static final String FETCHONE = "SELECT * FROM orderhistory WHERE OrderHistoryId = ?";
	    private static final String UPDATE = "UPDATE orderhistory SET TotalAmount = ?, Status = ? WHERE OrderHistoryId = ?";
	    private static final String DELETE = "DELETE FROM orderhistory WHERE OrderHistoryId = ?";
		private static Connection con;

	    public static void connect() {
	        con = OrderItemDBConnection.connect(); // Assuming this method connects to the database
	    }

		private PreparedStatement pstmt;

	@Override
	public int insert(OrderHistory orderHistory) 
	{
		try {
            pstmt = con.prepareStatement(INSERT);
            pstmt.setInt(1, orderHistory.getOrderHistoryId());
            pstmt.setInt(2, orderHistory.getOrderID());
            pstmt.setInt(3, orderHistory.getUserId());
            pstmt.setDouble(4, orderHistory.getTotalAmount());
            pstmt.setString(5, orderHistory.getStatus());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
	 
	}

	@Override
	public ArrayList<OrderHistory> fetchAll() 
	{
	    ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(FETCHALL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                orderHistoryList.add(new OrderHistory(rs.getInt("OrderHistoryId"),
                        rs.getInt("OrderID"),
                        rs.getInt("UserId"),
                        rs.getString("Status"),
                        rs.getDouble("TotalAmount")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderHistoryList;
		
	}

	@Override
	public OrderHistory fetchOne(int orderHistoryId) {
        try {
            pstmt = con.prepareStatement(FETCHONE);
            pstmt.setInt(1, orderHistoryId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new OrderHistory(rs.getInt("OrderHistoryId"),
                        rs.getInt("OrderID"),
                        rs.getInt("UserId"),
                        rs.getString("Status"),
                        rs.getInt("TotalAmount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	

	@Override
	public int delete(int orderHistoryId) 
	{
		try {
            pstmt = con.prepareStatement(DELETE);
            pstmt.setInt(1, orderHistoryId);
            return pstmt.executeUpdate();
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
        return 0;
    }

	@Override
	public int update(int orderHistoryId, String status, double totalAmount) 
	{ 
		try
		{
			pstmt =con.prepareStatement(UPDATE);
			pstmt.setInt(1, orderHistoryId);
			pstmt.setString(2, status);
			pstmt.setDouble(3, totalAmount);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return 0;
		
	}


	}
	
	
	


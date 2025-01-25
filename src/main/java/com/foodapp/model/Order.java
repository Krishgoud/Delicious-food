package com.foodapp.model;

public class Order
{
	private int OrderID;
	private int UserID;
	private int restaurantID;
	private String Status;
	private String PaymentMode;
	private String Address;
    private double GrandTotal; // Change type from int to double if needed



	
	public double getGrandTotal() {
		return GrandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		GrandTotal = grandTotal;
	}
	public Order(String address) {
		super();
		Address = address;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getRestaurantID() {
		return restaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPaymentMode() {
		return PaymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}
	public Order() {
		super();
	}
	public Order(int orderID, int userID, int restaurantID, int grandTotal, String status, String paymentMode,String address) {
		super();
		this.OrderID = orderID;
		this.UserID = userID;
		this.restaurantID = restaurantID;
		this.GrandTotal =grandTotal;
		this.Status = status;
		this.PaymentMode = paymentMode;
		this.Address = address;

	}
	public Order(int i, int userId2, int restaurantId2, double grandTotal, String string, String paymentMode2,
			String address) {
		// TODO Auto-generated constructor stub
	}
	public Order(int int1, int int2, int int3, double double1, String string, String string2) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [OrderID=" + OrderID + ", UserID=" + UserID + ", restaurantID=" + restaurantID + ", TotalAmount="
				+ GrandTotal + ", Status=" + Status + ", PaymentMode=" + PaymentMode + "]";
	}
	
	
	

	




	

}

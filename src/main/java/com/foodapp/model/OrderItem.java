package com.foodapp.model;

public class OrderItem 
{
	private int OrderItemID;
	private int OrderID;
	private int MenuID;
	private int Quantity;
	private double ItemTotal;

	public int getOrderItemID() {
		return OrderItemID;
	}
	public void setOrderItemID(int orderItemID) {
		OrderItemID = orderItemID;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getMenuID() {
		return MenuID;
	}
	public void setMenuID(int menuID) {
		MenuID = menuID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public double getItemTotal() {
		return ItemTotal;
	}
	public void setItemTotal(int itemTotal) {
		ItemTotal = itemTotal;
	}
	public OrderItem(int i, int orderId2, int j, int k, double d) {
		super();
	}
	public OrderItem(int orderItemID, int orderID, int menuID, int quantity, int itemTotal) {
		super();
		this.OrderItemID = orderItemID;
		this.OrderID = orderID;
		this.MenuID = menuID;
		this.Quantity = quantity;
		this.ItemTotal = itemTotal;
	}
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderItem [OrderItemID=" + OrderItemID + ", OrderID=" + OrderID + ", MenuID=" + MenuID + ", Quantity="
				+ Quantity + ", ItemTotal=" + ItemTotal + "]";
	}
	
	





}

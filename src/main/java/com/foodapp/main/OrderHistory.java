package com.foodapp.main;

public class OrderHistory 
{
		private int OrderHistoryId;
		private int OrderID;
		private int UserId;
		private double TotalAmount;
		private String Status;
		
		public int getOrderHistoryId() {
			return OrderHistoryId;
		}
		public void setOrderHistoryId(int orderHistoryId) {
			OrderHistoryId = orderHistoryId;
		}
		public int getOrderID() {
			return OrderID;
		}
		public void setOrderID(int orderID) {
			OrderID = orderID;
		}
		public int getUserId() {
			return UserId;
		}
		public void setUserId(int userId) {
			UserId = userId;
		}
		public double getTotalAmount() {
			return TotalAmount;
		}
		public void setTotalAmount(int totalAmount) {
			TotalAmount = totalAmount;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public OrderHistory() {
			super();
		}
		public OrderHistory(int orderHistoryId, int orderID, int userId,  String status, double totalAmount) {
			super();
			this.OrderHistoryId = orderHistoryId;
			this.OrderID = orderID;
			this.UserId = userId;
			this.Status = status;
			this.TotalAmount = totalAmount;

		}
		@Override
		public String toString() {
			return "OrderHistory [OrderHistoryId=" + OrderHistoryId + ", OrderID=" + OrderID + ", UserId=" + UserId
					+ ", TotalAmount=" + TotalAmount + ", Status=" + Status + "]";
		}
		




		

}

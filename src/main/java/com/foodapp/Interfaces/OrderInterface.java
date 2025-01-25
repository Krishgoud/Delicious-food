package com.foodapp.Interfaces;

import java.util.ArrayList;

import com.foodapp.model.Order;

public interface OrderInterface
{ 
	
	    int insert(Order order);

	    ArrayList<Order> fetchAll();

	    Order fetchOne(int orderID);

	    int update(int orderID, String status, String paymentMode);

	    int delete(int orderID);
	}




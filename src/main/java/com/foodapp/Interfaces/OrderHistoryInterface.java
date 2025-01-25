package com.foodapp.Interfaces;

import java.util.ArrayList;

import com.foodapp.main.OrderHistory;

public interface OrderHistoryInterface
{
    int insert(OrderHistory orderHistory);

    ArrayList<OrderHistory> fetchAll();

    OrderHistory fetchOne(int orderHistoryId);


    int delete(int orderHistoryId);

	int update(int orderHistoryId, String status , double totalAmount);


}

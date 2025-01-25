package com.foodapp.Interfaces;

import java.util.ArrayList;

import com.foodapp.model.Order;
import com.foodapp.model.OrderItem;

public interface OrderItemsInterface 
{
    int insert(OrderItem orderitem );
    
    ArrayList<OrderItem> fetchAll();
    
    OrderItem fetchOne(int orderItemID);

    int update(int orderItemID, int Quantity, int ItemTotal);

    int delete(int orderItemID);


}

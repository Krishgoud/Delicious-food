package com.foodapp.Interfaces;

import java.util.ArrayList;

import com.foodapp.model.User;

public interface userInterface 
{
    int insert(User user);

    ArrayList<User> fetchAll();

    User fetchOne(int id);

    int update(int id, String name, String password, String email, String address);
    int delete(int id);


}



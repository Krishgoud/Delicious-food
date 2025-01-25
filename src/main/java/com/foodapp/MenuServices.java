package com.foodapp;

import java.util.List;

import com.foodapp.daoimpl.MenuDaoImpl;
import com.foodapp.model.menu;

public class MenuServices {

    private MenuDaoImpl menuDao;

    public MenuServices(MenuDaoImpl menuDao) {
        this.menuDao = menuDao;
    }

    public List<menu> fetchmenubyRestaurantID(int restaurantId) {
        return menuDao.fetchmenubyRestaurantID(restaurantId);
    }

    
}

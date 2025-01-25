package com.foodapp.model;

public class CartItem {

    private int itemId;
    private int MenuID; // Updated field name to camelCase
    private int RestaurantID; // Updated field name to camelCase
    private String name;
    private int quantity;
    private double price;
    private String ImagePath;
    

    public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}

	// Default constructor
    public CartItem() {
        super();
    }

    // Constructor with restaurantID, name, quantity, and price
    public CartItem(int restaurantID, String name, int quantity, double price) {
        super();
        this.RestaurantID = restaurantID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor with itemId, menuID, restaurantID, name, quantity, and price
    public CartItem(int itemId, int menuID, int restaurantID, String name, int quantity, double price) {
        super();
        this.itemId = itemId;
        this.MenuID = menuID; // Assigning the menuID
        this.RestaurantID = restaurantID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Constructor without itemId, just menuID, restaurantID, name, quantity, and price
    public CartItem(int menuID, int restaurantID, String name, int quantity, double price) {
        super();
        this.MenuID = menuID;
        this.RestaurantID = restaurantID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public CartItem(int itemId2, String string, double d, int i) {
		// TODO Auto-generated constructor stub
	}

	public CartItem(int menuId2) {
		// TODO Auto-generated constructor stub
	}

	// Getter and Setter for itemId
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    // Getter and Setter for menuID
    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        this.MenuID = menuID;
    }

    // Getter and Setter for restaurantID
    public int getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.RestaurantID = restaurantID;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override toString method
    @Override
    public String toString() {
        return "CartItem [itemId=" + itemId + ", menuID=" + MenuID + ", restaurantID=" + RestaurantID + ", name=" + name 
                + ", quantity=" + quantity + ", price=" + price + "]";
    }

	public void getImagePath(String imagepath2) {
		// TODO Auto-generated method stub
		
	}
}

package com.foodapp.model;

public class menu {
    private int MenuID;
    private int RestaurantID;
    private String Name;
    private String Description;
    private int Price;
    private String IsAvailable;
    private String Imagepath;
    private double Ratings;



    public menu(int menuID, int restaurantID, String name, String description, int price, String isAvailable,
			String imagepath, double ratings) {
		super();
		this.MenuID = menuID;
		RestaurantID = restaurantID;
		this.Name = name;
		this.Description = description;
		this.Price = price;
		this.IsAvailable = isAvailable;
		Imagepath = imagepath;
		Ratings = ratings;
	}



    public menu() {
		super();
	}



	// Getters and Setters
    public int getMenuID() {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }

    public int getRestaurantID() {
        return RestaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        RestaurantID = restaurantID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        IsAvailable = isAvailable;
    }

    public String getImagepath() {
        return Imagepath;
    }

    public void setImagepath(String imagepath) {
        Imagepath = imagepath;
    }

    public double getRatings() {
        return Ratings;
    }

    public void setRatings(double ratings) {
        Ratings = ratings;
    }



	@Override
	public String toString() {
		return "menu [MenuID=" + MenuID + ", RestaurantID=" + RestaurantID + ", Name=" + Name + ", Description="
				+ Description + ", Price=" + Price + ", IsAvailable=" + IsAvailable + ", Imagepath=" + Imagepath
				+ ", Ratings=" + Ratings + "]";
	}
    
}

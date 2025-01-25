package com.foodapp.model;

public class Restaurant {
    private int id;
    private int RestaurantID;
    private String name;
    private String cuisineType;
    private int deliveryTime;
    private String address;
    private double ratings;
    private String isActive;
    private String imagepath;
    private String description;

   
 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantID() {
		return RestaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		RestaurantID = restaurantID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Restaurant(int id, int restaurantID, String name, String cuisineType, int deliveryTime, String address,
			double ratings, String isActive, String imagepath, String description) {
		super();
		this.id = id;
		RestaurantID = restaurantID;
		this.name = name;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.ratings = ratings;
		this.isActive = isActive;
		this.imagepath = imagepath;
		this.description = description;
	}

	public Restaurant(int int1, String string, String string2, int int2, String string3, double double1, String string4,
			String string5, String string6) {
		// TODO Auto-generated constructor stub
	}

	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String toString() {
        return "Restaurant [id=" + id + ", name=" + name + ", cuisineType=" + cuisineType + ", deliveryTime=" + deliveryTime +
                ", address=" + address + ", ratings=" + ratings + ", isActive=" + isActive + ", imagepath=" + imagepath + ", description=" + description + "]";
    }
}

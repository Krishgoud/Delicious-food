package com.foodapp.model;

public class User
{
	private String UserName;
	private String Password;
	private String Email;
	private String Address;
	
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public User() {
		super();
	}
	public User( String username, String password, String email, String address) {
		super();

		this.UserName = username;
		this.Password = password;
		this.Email = email;
		this.Address = address;
	}
	public User(int int1, String string, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return UserName +"         "+ Password +"       "  +Email+"     "
				+  Address  ;
	}
	public Object getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}

package com.foodapp;

public class MyEncrypt
{
	public static String  encrypt(String str) 
	{
		if(str == null)
		{
			return null;
			
		}
	
		StringBuffer sb= new StringBuffer();
		for(int i=0;i<str.length();i++)
		{
		
			sb.append((char)(str.charAt(i)+2));
		}
		return sb.toString();
	}
}	


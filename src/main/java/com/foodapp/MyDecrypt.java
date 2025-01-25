package com.foodapp;

public class MyDecrypt 
{
	public static StringBuffer  decrypt(String name) 
	{
		if(name ==null) {
			return null;
		}
		StringBuffer sb= new StringBuffer();
		for(int i=0;i<name.length();i++)
		{
		
			sb.append((char)(name.charAt(i)-2));
		}
		return sb;

	}

}

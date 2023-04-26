package com.devashish.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstProject
{

	@GetMapping("/api/Demo")
	public String Demo(String email, String password)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Mysql@21");
		Statement stmt=con.createStatement();
		String query="insert into user values('"+email+"','"+password+"')";
		int i=stmt.executeUpdate(query);
		System.out.println("Number of rows inserted "+i);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "this is login api  "+email+" password is  "+password;
	}
	
	@GetMapping("/api/login")
	public String login(String email,String password)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Mysql@21");
			Statement stmt=con.createStatement();
			String query="select password from user where email ='"+email+"'";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
			{
				String pwd=rs.getString("password");
				if(pwd.equals(password))
				{
					return "You are valid user";
				}
				else
				{
					return "incorrect password";
				}
			}
			else
			{
				return "you are not registered";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "this is Devashish";
	}
	
	@GetMapping("/forget password")
	
	public String forget(String email)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Mysql@21");
			Statement stmt=con.createStatement();
			String query="select * from user where email='"+email+"'";
			ResultSet rs=stmt.executeQuery(query);
			if(rs.next())
			{
				int min = 100;  
				int max = 999;  
				//Generate random double value from 200 to 400   
				  
				int a = (int) (Math.random()*(max-min+1)+min);
				String query1="alter table user add otp int";
				int i=stmt.executeUpdate(query1);
				PreparedStatement stmt1=con.prepareStatement("update user set otp=? where email=?");
				stmt1.setLong(1, a);
				stmt1.setString(2, email);
				int j=stmt1.executeUpdate();
				return "otp is " +a;
						
			}
			else
			{
				return "invalid id";
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return "";
	}
	








@GetMapping("/api/v2/admin/location/states")

public Map  states()
{
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Mysql@21");
		PreparedStatement stmt=con.prepareStatement("select * from states");
		ResultSet rs =stmt.executeQuery();
		List l= new ArrayList();
		
		while(rs.next())
		{
			Map map =new HashMap();
			map.put("state-id",rs.getString("State_id"));
			map.put("state_name",rs.getString("State_name"));
			l.add(map);
			
		}
		Map data =new HashMap();
		data.put("states", l);
		data.put("ttl", 24);
		return data;
		
	}
	catch(Exception ex)
	{

		
	}
	
	
	return null;
}







@GetMapping("/api/v2/admin/location/district/{state_id}")

public Map  district(@PathVariable("state_id") String state_id)
{
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Mysql@21");
		PreparedStatement stmt=con.prepareStatement("select * from district where state_id=?");
		stmt.setInt(1, Integer.parseInt(state_id));
		ResultSet rs =stmt.executeQuery();
		List l= new ArrayList();
		
		while(rs.next())
		{
			Map map =new HashMap();
			map.put("district id",rs.getString("district_id"));
			map.put("district_name",rs.getString("district_name"));
			l.add(map);
			
		}
		Map data =new HashMap();
		data.put("district", l);
		data.put("ttl", 24);
		return data;
		
	}
	catch(Exception ex)
	{

		
	}
	
	
	return null;
}



// shortning url api


@GetMapping("/shortUrl")

public String shortUrl(String url)
{
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shortUrl", "root", "Mysql@21");
		Statement stmt=con.createStatement();
		String query=" insert into short_Url (url) values('"+url+"')";
		int i=stmt.executeUpdate(query);
		String query1="select * from short_Url where url='"+url+"'";
		ResultSet rs=stmt.executeQuery(query1);
		  if(rs.next())
		    {
			  int leftLimit = 97; // letter 'a'
			    int rightLimit = 122; // letter 'z'
			    int targetStringLength = 10;
			    Random random = new Random();
			    StringBuilder buffer = new StringBuilder(targetStringLength);
			    for (int i1 = 0; i1 < targetStringLength; i1++) {
			        int randomLimitedInt = leftLimit + (int) 
			          (random.nextFloat() * (rightLimit - leftLimit + 1));
			        buffer.append((char) randomLimitedInt);
			    }
			    String generatedString = buffer.toString();
			    String s=generatedString;
				String query2="Update short_url set shortUrl='s' where url='"+url+"'";
				int i1=stmt.executeUpdate(query2);
				
				return "short url is  " + generatedString;
					
		    }
		else
			{
				return "invalid id";
			}
		
		
	    } 
	catch (Exception e) 
	{
		
		e.printStackTrace();
	}
	
	
	return "";
}


}










































package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;
public class Fruits {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<Fruits> lstfruits;
    private MultipartFile file;
    private String name;
   public Fruits() {}
       
	public List<Fruits> getLstfruits() {
	return lstfruits;
}

public void setLstfruits(List<Fruits> lstfruits) {
	this.lstfruits = lstfruits;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

	public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}

	public Fruits(ResultSet rs)
	{
		try
		{
		name=rs.getString("name").toString().trim();
	 
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err="+e.getMessage());
		}
	}
	public void getFruits()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getFruits()}");
	         
	        lstfruits=new ArrayList<Fruits>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	               
	        while(rs.next())
	        { System.out.println("true");
	        lstfruits.add(new Fruits(rs));
	              
	        }
	        try{con.close();}catch(Exception ex){}
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	 
	 
}

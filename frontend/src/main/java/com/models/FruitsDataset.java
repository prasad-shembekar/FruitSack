package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;
import com.services.JavaFuns;
 
public class FruitsDataset {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<FruitsDataset> lstfruits;
    private MultipartFile file;
    private String title,availsts,desc,path,userid;
	private int stock,id;	
   public FruitsDataset() {}
  
	public List<FruitsDataset> getLstfruits() {
	return lstfruits;
}

public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

public void setLstfruits(List<FruitsDataset> lstfruits) {
	this.lstfruits = lstfruits;
}
 

	public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

	public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getAvailsts() {
	return availsts;
}

public void setAvailsts(String availsts) {
	this.availsts = availsts;
}

public String getDesc() {
	return desc;
}

public void setDesc(String desc) {
	this.desc = desc;
}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public int getStock() {
	return stock;
}

public void setStock(int stock) {
	this.stock = stock;
}

public List<FruitsDataset> getFruitsList(String userid){
	
	Connection con;
	Statement st;
	DBConnector gc = new DBConnector();
	ResultSet rs;
	FruitsDataset bl=new FruitsDataset();
	
	List<FruitsDataset> lst= new ArrayList<FruitsDataset>();
	
	try {
		con=gc.connect();
		st=con.createStatement();
		
		rs=st.executeQuery("select * from dataset ");
		
		while(rs.next()) {
			
			bl=new FruitsDataset();
			bl.setTitle(rs.getString("category")); 
			bl.setPath(rs.getString("photo")); 
			bl.setStock(rs.getInt("title")); 
			lst.add(bl);
			
		}	
		try{con.close();}catch(Exception ex){}
			
	}
	catch(Exception ex) {
		ex.printStackTrace();
	}
	
	return (lst);
}

public int getImgId()
{
	int imgid=0;
    try
    {
         JavaFuns jf=new JavaFuns();
         Vector v=jf.getValue("select ifnull(max(dId),1000)+1 as did from dataset;", 1);
         imgid=Integer.parseInt(v.elementAt(0).toString().trim());
       
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return imgid;
}
	 
}

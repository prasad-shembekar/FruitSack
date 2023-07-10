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
 
public class SellerFruits {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private List<SellerFruits> lstfruits;
    private MultipartFile file;
    private String title,availsts,desc,path,userid;
	private int stock,id;	
   public SellerFruits() {}
  
	public List<SellerFruits> getLstfruits() {
	return lstfruits;
}

public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

public void setLstfruits(List<SellerFruits> lstfruits) {
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

public List<SellerFruits> getFruitsList(String userid){
	
	Connection con;
	Statement st;
	DBConnector gc = new DBConnector();
	ResultSet rs;
	SellerFruits bl=new SellerFruits();
	
	List<SellerFruits> lst= new ArrayList<SellerFruits>();
	
	try {
		con=gc.connect();
		st=con.createStatement();
		
		rs=st.executeQuery("select * from sellerfruits where userid='"+userid+"'");
		
		while(rs.next()) {
			
			bl=new SellerFruits();
			bl.setTitle(rs.getString("title"));
			bl.setUserid(rs.getString("userid")); 
			bl.setAvailsts(rs.getString("availsts"));
			bl.setDesc(rs.getString("fruitDesc"));
			bl.setPath(rs.getString("photo")); 
			bl.setStock(rs.getInt("stock")); 
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
         Vector v=jf.getValue("select ifnull(max(sfruitId),1000)+1 as sfruitid from sellerfruits;", 1);
         imgid=Integer.parseInt(v.elementAt(0).toString().trim());
       
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
    return imgid;
}
	public boolean registration()
	    {
	        try
	        { 
	        	Date d=new Date();
	        	String dt=d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900);
	            String tm=d.getHours()+":"+d.getMinutes();
	        	DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertSellerFruits(?,?,?,?,?,?,?,?,?)}");
	            csmt.setInt(1, id);
	            csmt.setString(2, title);
	            csmt.setString(3, desc);
	            csmt.setString(4, userid);
	            csmt.setString(5, path);
	            csmt.setString(6, dt);
	            csmt.setString(7, tm);
	            csmt.setString(8, availsts);
	            csmt.setInt(9, stock);
	            
	           
	            
	             int n=csmt.executeUpdate();
	             
	                        
	            
	            if(n>0)
	            {
	                try{con.close();}catch(Exception ex){}
	                System.out.println("true");
	                return true;
	            }
	            else
	                return false;

	            }
	           
	         
	        catch(Exception ex)
	        {
	            System.out.println("err="+ex.getMessage());
	            return false;
	        }
	    }
	 
}

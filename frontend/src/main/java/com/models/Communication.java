package com.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import com.services.DBConnector;
import com.services.JavaFuns;
public class Communication {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
    private int commId;
    private MultipartFile file;
    private List<Communication> lstcomm;
    private String query,dt,reply,msg,attach1,attach2,sts,userid,username,expertName,expertUserid;
    public Communication() {}
      
     
	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public int getCommId() {
		return commId;
	}


	public void setCommId(int commId) {
		this.commId = commId;
	}


	public String getDt() {
		return dt;
	}


	public void setDt(String dt) {
		this.dt = dt;
	}


	public String getReply() {
		return reply;
	}


	public void setReply(String reply) {
		this.reply = reply;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getAttach1() {
		return attach1;
	}


	public void setAttach1(String attach1) {
		this.attach1 = attach1;
	}


	public String getAttach2() {
		return attach2;
	}


	public void setAttach2(String attach2) {
		this.attach2 = attach2;
	}


	public String getSts() {
		return sts;
	}


	public void setSts(String sts) {
		this.sts = sts;
	}


	public List<Communication> getLstcomm() {
		return lstcomm;
	}


	public void setLstcomm(List<Communication> lstcomm) {
		this.lstcomm = lstcomm;
	}


	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getExpertName() {
		return expertName;
	}


	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}


	public String getExpertUserid() {
		return expertUserid;
	}


	public void setExpertUserid(String expertUserid) {
		this.expertUserid = expertUserid;
	}

	public void generateCommId()
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getMaxIdComm()}");
	       
	         csmt.execute();
	         rs=csmt.getResultSet();
	                    
	        boolean auth=false;
	        while(rs.next())
	        { System.out.println("true");
	            auth=true;
	            
	            commId=rs.getInt("mxid");
	            if(commId==0 || commId==1000)
	            	commId=1001;
	            else
	            	commId+=1;
	              
	        }
	        try{con.close();}catch(Exception ex){}
	    } 
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public Communication(ResultSet rs)
	{
		try
		{
			JavaFuns jf=new JavaFuns();
		
			commId=rs.getInt("commId");
		userid=rs.getString("userid").toString().trim();
		Vector<String> vct=jf.getValue("select username from users where userid='"+userid+"'",1);
		username=vct.elementAt(0).trim();
		
		 
		expertUserid=rs.getString("expertUserid").toString().trim();
		Vector<String> vct1=jf.getValue("select username from users where userid='"+expertUserid+"'",1);
		expertName=vct1.elementAt(0).trim();
		dt=rs.getString("dt").toString().trim();
		sts=rs.getString("sts").toString().trim();
		attach1=rs.getString("attach1").toString().trim();
		attach2=rs.getString("attach2").toString().trim();
		reply=rs.getString("reply").toString().trim();
		query=rs.getString("msg").toString().trim();
	 
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("errd="+e.getMessage());
		}
	}
	public void getMessages(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getInbox(?)}");
	        csmt.setString(1, userid1);
	         
	        lstcomm=new ArrayList<Communication>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstcomm.add(new Communication(rs));
	              
	        }
	        try{con.close();}catch(Exception ex){}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public void getMessagesSent(String userid1)
	{
	    try
	    {
	         DBConnector obj=new  DBConnector();
	        con=obj.connect();
	        csmt=con.prepareCall("{call getSentItems(?)}");
	        csmt.setString(1, userid1);
	         
	        lstcomm=new ArrayList<Communication>();
	         
	         csmt.execute();
	         rs=csmt.getResultSet();
	                     
	        while(rs.next())
	        { System.out.println("true");
	        lstcomm.add(new Communication(rs));
	              
	        }
	        try{con.close();}catch(Exception ex){}
	    }
	       
	     
	    catch(Exception ex)
	    {
	        System.out.println("err="+ex.getMessage());
	         
	    }
	}
	public boolean registration()
	    {
	        try
	        {
	        	 Date dt1=new Date();
	        	 String d=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
	             DBConnector obj=new  DBConnector();
	            con=obj.connect();
	            csmt=con.prepareCall("{call insertCommunication(?,?,?,?,?)}");
	            csmt.setString(1, userid);
	            csmt.setString(2, expertUserid);
	            csmt.setString(3, msg);
	            csmt.setString(4, d);
	            csmt.setString(5, attach1); 
	             int n=csmt.executeUpdate();
	             try{con.close();}catch(Exception ex){}
	                        
	            
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
	public boolean updateComm()
    {
        try
        { 
             DBConnector obj=new  DBConnector();
            con=obj.connect();
            csmt=con.prepareCall("{call updateComm(?,?,?)}");
            csmt.setInt(1, commId);
            csmt.setString(2, reply);
            csmt.setString(3, attach2);
             int n=csmt.executeUpdate();  
             try{con.close();}catch(Exception ex){}
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

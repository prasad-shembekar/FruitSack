package com.fruitsQuality;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.models.Communication;
import com.models.Fruits;
import com.models.FruitsDataset;
import com.models.Login;
import com.models.Pass;
 
 
import com.models.SellerFruits;
import com.models.UserReg;
import com.services.JavaFuns;
import com.services.Mail;
import com.services.RandomString;

import com.models.*; 
 
 
 

@Controller
public class FruitController {
	@RequestMapping("/home")
	public String myspring()
	{
		return "index.jsp";
	}
	@RequestMapping("/")
	public String myspring1()
	{
		return "index.jsp";
	}
	@RequestMapping("viewRetailer1")
	public ModelAndView viewRetailer1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("seller");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewSeller1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/ViewFruits1")
	public ModelAndView viewFarmerProducts11(HttpSession ses,HttpServletRequest request)
	{
		
		List<SellerFruits> lst = new ArrayList<SellerFruits>();
		SellerFruits obj=new SellerFruits();
		obj.setUserid(request.getParameter("userid").toString().trim());
		 lst=obj.getFruitsList(request.getParameter("userid").toString().trim());
		 
		ModelAndView mv = new ModelAndView();

		mv.setViewName("ViewFruits1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/RegDSImg")
	public String RegDSImg()
	{
		return "RegDSImg.jsp";
	}
	@RequestMapping("/RegPlants")
	public String RegPlants()
	{
		return "RegPlants.jsp";
	}
	@RequestMapping("/forgot")
	public String forgot()
	{
		return "Forgot.jsp";
	}
	  
	 @RequestMapping("/datasetInsrtPython")
		public ModelAndView datasetInsrtPython(HttpServletRequest request) {
			ModelAndView mv=new ModelAndView();
		 	String sts=request.getParameter("sts").toString().trim() ;
			if(sts.equals("success"))
				 mv.setViewName("Success.jsp?type=datasetInsrt");
			else
				 mv.setViewName("Failure.jsp?type=datasetInsrt");
			mv.addObject("activity","prodReg");
			 return mv;
		}
	@RequestMapping("/passRecovery")
	public ModelAndView passRecovery(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.useridAuth())
			{
				String otp=RandomString.getAlphaNumericString(4);
				
			    mv.setViewName("ForgotOTP.jsp");
			    mv.addObject("userid",user.getUserid());
			    mv.addObject("otp",otp);
			    mv.addObject("email",user.getEmail());
			    Mail mail=new Mail();
			    String msg="Dear "+user.getName()+" \n Your one time password is "+otp;
			    System.out.println("otp="+otp);
			    try
			    {
			    	if(mail.sendMail(msg,user.getEmail(), "One Time Password"))
			    	{
			    		
			    	}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=Auth");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	@RequestMapping("/RegFruitsDataSet")
	public ModelAndView RegFruitsDataSet(FruitsDataset sf)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("RegFruitDataset.jsp");
		int id=sf.getImgId();   
		Fruits f=new Fruits();
		f.getFruits();
		List<Fruits> lst=f.getLstfruits();
		mv.addObject("id",id);
		mv.addObject("lst",lst);
		return mv;
	}
	@RequestMapping("/dataPrediction")
	public ModelAndView dataPrediction(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
	 	String sts=request.getParameter("sts").toString().trim() ;
		JavaFuns jf=new JavaFuns();
		Vector vtotal=jf.getValue("select count(*) from prediction where orderid_prod="+request.getParameter("porderid").toString().trim(), 1);
		Vector vworst=jf.getValue("select count(*) from prediction where   predCategory='Worst' and orderid_prod="+request.getParameter("porderid").toString().trim(), 1);
		Vector vavg=jf.getValue("select count(*) from prediction where predCategory='Average' and orderid_prod="+request.getParameter("porderid").toString().trim(), 1);
		Vector vprice=jf.getValue("select total from orderedprods where opid="+request.getParameter("porderid").toString().trim(), 1);
		double total=0,worst=0,avg=0,price=0,price1=0;
		total=Double.parseDouble(vtotal.elementAt(0).toString().trim());
		worst=Double.parseDouble(vworst.elementAt(0).toString().trim());
		avg=Double.parseDouble(vavg.elementAt(0).toString().trim());
		price=Double.parseDouble(vprice.elementAt(0).toString().trim());
		price1=Double.parseDouble(vprice.elementAt(0).toString().trim());
		if(worst>0)
		{
			worst=(worst*100)/total;
			price=price-((price*worst)/100);
			
			System.out.println("price="+price+" price1="+price1);
		}
		if(avg>0)
		{
			avg=(avg*100)/total;
			price=price-((price*20)/100);
			 
			System.out.println("price="+price+" price1="+price1+" per="+((price*20)/100));
		}
		price1=price1-price;
		System.out.println("price="+price+" price1="+price1);
		if(jf.execute("update orderedprods set total="+price+" where opid="+request.getParameter("porderid").toString().trim())) {}
		if(jf.execute("update orders set netbill=netbill-"+price1+" where orderno="+request.getParameter("orderid").toString().trim())) {}
		
		mv.setViewName("Success.jsp?type=Prediction");
		mv.addObject("activity","Prediction");
		 return mv;
	}
	@RequestMapping("/frompython1")
	public ModelAndView frompython1(HttpServletRequest request) {
		ModelAndView mv=new ModelAndView();
	 	String sts=request.getParameter("sts").toString().trim() ;
		if(sts.equals("success"))
			 mv.setViewName("Success.jsp?type=DsTrained");
		else
			 mv.setViewName("Failure.jsp");
		mv.addObject("activity","DsTrained");
		 return mv;
	}
	@RequestMapping("/TrainDataset")
	public ModelAndView TrainDataset(FruitsDataset sf)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("TrainFruitDataset.jsp");
		int id=sf.getImgId();   
		Fruits f=new Fruits();
		f.getFruits();
		List<Fruits> lst=f.getLstfruits();
		mv.addObject("id",id);
		mv.addObject("lst",lst);
		return mv;
	}
	 @RequestMapping("/regFruitSeller")
		public ModelAndView regFruitSeller(SellerFruits stu,ServletRequest request)
		{
			ModelAndView mv=new ModelAndView();
			 try
			 {MultipartFile file=stu.getFile();
			 String filepath=request.getServletContext().getRealPath("/")+"/Uploads/";
			 
			 
			 System.out.println("path="+filepath);
			 File f=new File(filepath);
			 f.mkdir();
			  
			 try {
				  
				 String fileName=stu.getId()+"."+ file.getOriginalFilename().split("\\.")[1];
				 file.transferTo(new File(filepath+"/"+fileName));
				 stu.setPath(fileName);
				 
					if(stu.registration())
						mv.setViewName("Success.jsp?type=RegSellerFruits");
					else
						mv.setViewName("Failure.jsp?type=RegSellerFruits");
			 }
			 catch (Exception e) {
				// TODO: handle exception
				 System.out.println("err="+e.getMessage());
				 mv.setViewName("Failure.jsp?type=RegSellerFruits");
			}}
			 catch (Exception e) {
					// TODO: handle exception
				 System.out.println("err1="+e.getMessage());
				 mv.setViewName("Failure.jsp");
				} 
			 return mv;
			
		}	 
	@RequestMapping("/ChangePassService")
	public String ChangePassService(Pass eobj,HttpSession ses)
	{ 
		 try
		 { 
			 eobj.setUserid(ses.getAttribute("userid").toString().trim());
			 if(eobj.changePassword())
			 { 
				 return "Success.jsp?type=ChangePass";
			 }
			 else
			 { 
				 return "Failure.jsp?type=ChangePass";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/process")
	public ModelAndView process(HttpServletRequest request,Communication obj,HttpSession ses)
	{
		 String fileName="NA";
		 
		 try {
		 MultipartFile file=obj.getFile();
		  
		 String filepath=request.getServletContext().getRealPath("/")+"/CommAttach/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 filepath+="/"+ses.getAttribute("userid").toString().trim();
		 f=new File(filepath);
		 f.mkdir();
		   
			 int mx=obj.getCommId();
			 fileName=mx+"Reply."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
		 obj.setAttach2(fileName);
		 obj.updateComm();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("Success.jsp?type=CommReply");
		 return mv;
	}	
	@RequestMapping("/RegSellerFruits")
	public ModelAndView RegSellerFruits(SellerFruits sf)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("RegSellerFruits.jsp");
		int id=sf.getImgId();   
		Fruits f=new Fruits();
		f.getFruits();
		List<Fruits> lst=f.getLstfruits();
		mv.addObject("id",id);
		mv.addObject("lst",lst);
		return mv;
	}
	@RequestMapping("/admin")
	public String admin()
	{
		return "admin.jsp";
	}
	@RequestMapping("/seller")
	public String seller()
	{
		return "seller.jsp";
	}
	@RequestMapping("/registration")
	public String registration()
	{
		return "Registration.jsp";
	}
	@RequestMapping("/viewDetails")
	public ModelAndView viewDetails(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		mv.setViewName("RegFarmerDetails.jsp");
		mv.addObject("plantName",request.getParameter("plantName").trim());
		mv.addObject("diseaseName",request.getParameter("diseaseName").trim());
		return mv;
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request)
	{
		 Login obj=new Login();
		 try
		 {
			 javax.servlet.http.HttpSession ses=request.getSession(true);
			 
			 if(obj.chkAuthentication(request.getParameter("txtuserid").trim(), request.getParameter("txtpass").trim()))
			 {
				 ses.setAttribute("userid", obj.getUserid());
				 System.out.println("userid="+obj.getUserid());
				 System.out.println("userid="+obj.getuType());
				 System.out.println("userid="+obj.getUserName());
				 ses.setAttribute("utype", obj.getuType());
				 ses.setAttribute("email", obj.getEmail());
				 ses.setAttribute("username", obj.getUserName());
				 return obj.getuType()+".jsp";
			 }
			 else
			 { 
				 return "Failure.jsp?type=Auth";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=Auth");
		}
		 
	}
	@RequestMapping("/viewSellers")
	public ModelAndView viewSellers(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("seller");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewSellers.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
        session.invalidate();
		return "Logout.jsp";
	}
	@RequestMapping("/Cities")
	public String cities()
	{
		return "Cities.jsp";
	}
	@RequestMapping("RegSeller")
	public String RegSeller(UserReg obj)
	{
		 try
		 {
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegSeller";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegSeller";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUser");
		}
	}
	@RequestMapping("RegUser")
	public String RegUser(UserReg obj)
	{
		 try
		 {
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegUser";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegUser";
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegUser");
		}
		 
	}
	@RequestMapping("/viewFruits")
	@SessionScope
	public ModelAndView viewFruits(HttpSession ses) {
		
		List<SellerFruits> lst = new ArrayList<SellerFruits>();
		SellerFruits vs = new SellerFruits();
		lst=vs.getFruitsList(ses.getAttribute("userid").toString().trim());
		ModelAndView mv=new ModelAndView();
		mv.addObject("std",lst);
		System.out.println("size="+lst.size());
		mv.setViewName("ViewFruits.jsp");
		return mv;
	}
	@RequestMapping("/viewFruits11")
	@SessionScope
	public ModelAndView viewFruits11(HttpSession ses,HttpServletRequest request) {
		
		List<SellerFruits> lst = new ArrayList<SellerFruits>();
		SellerFruits vs = new SellerFruits();
		lst=vs.getFruitsList(request.getParameter("userid").toString().trim());
		ModelAndView mv=new ModelAndView();
		mv.addObject("std",lst);
		System.out.println("size="+lst.size());
		mv.setViewName("ViewFruits1.jsp");
		return mv;
	}
	@RequestMapping("/ChangePass")
	public String ChangePass()
	{
		return "ChangePass.jsp";
	}
	@RequestMapping("/passRecoveryOTPAuth")
	public ModelAndView passRecoveryOTPAuth(UserReg user)
	{
		ModelAndView mv=new ModelAndView();
		try {
			if(user.getSentOTP().equals(user.getOtp()))
			{
				String pass=RandomString.getAlphaNumericString(8);
				user.setPass(pass);
				if(user.updatePass())
				{
					
				}
				
				
			    mv.setViewName("Success.jsp?type=passEmail");
			    
			    Mail mail=new Mail();
			    JavaFuns jf=new JavaFuns();
			    Vector v=jf.getValue("select username from users where userid='"+user.getUserid()+"'", 1);
			    String msg="Dear "+v.elementAt(0).toString().trim()+" \n Your password has been reset to "+pass;
			    System.out.println("pass="+pass);
			    try
			    {
			    	if(mail.sendMail(msg,user.getEmail(), "New password"))
			    	{
			    		
			    	}
			    }
			    catch (Exception e) {
					// TODO: handle exception
				}
			}
			else
			{
				mv.setViewName("Failure.jsp?type=passEmail");
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	    return mv;
	}
	  
	@RequestMapping("/user")
	public String distributor()
	{
		return "user.jsp";
	}
	@RequestMapping("/processor")
	public String processor()
	{
		return "processor.jsp";
	}
	@RequestMapping("/customer")
	public String customer()
	{
		return "customer.jsp";
	}
	@RequestMapping("/farmer")
	public String farmer()
	{
		return "farmer.jsp";
	}
	 
	@RequestMapping("/regProdPer")
	public String regProdPer()
	{
		return "regProdPer.jsp";
	}
	@RequestMapping("/RegProductPer")
	public String RegProductPer(HttpServletRequest request)
	{
		try
		{
			JavaFuns jf=new JavaFuns();
			Vector v=jf.getValue("select ifnull(max(pid),1000)+1 as mxid from prodPercent", 1);
			if(jf.execute("delete from prodPercent where utype='"+request.getParameter("utype").trim()+"'"))
			{
				
			}
			
			if(jf.execute("insert into prodPercent values("+Integer.parseInt(v.elementAt(0).toString().trim())+",'"+request.getParameter("utype").trim()+"',"+request.getParameter("percent").trim()+")"))
			{
				
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return "Success.jsp?type=ProdPerReg";
	}
	@RequestMapping("/ProcessOrder")
	public String ProcessOrder(HttpServletRequest request,HttpSession ses)
	{
    	 
    	try {
    	  JavaFuns jf=new JavaFuns();
    	  if(jf.execute("update orders set orderstatus='processed' where orderno="+request.getParameter("orderNo").trim()))
    	  {
    		  
    	  }
    	  
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return "Success.jsp?type=OrderProcess";
	}
	@RequestMapping("/PaidOrder")
	public String PaidOrder(Orders ord,HttpServletRequest request,HttpSession ses)
	{
    	 
    	try {
    	  JavaFuns jf=new JavaFuns();
    	 
    		  
    		  Transactions obj=new Transactions();
    		  obj.setUserid(request.getParameter("userid").trim());
    		  obj.setSellerUserid(ord.getSellerUserid());
    		  obj.setSellerName(ord.getSellerUsernm());
    		  //CallMinnerAPI miner=new CallMinnerAPI();
    		 // APIManager1 miner=new  APIManager1();
    		  Vector v=jf.getValue("select productid,productname,quantity,price,date,userid from orderedprods where orderno="+ord.getOrderno() , 6);
    		  String transids="";
    		  for(int i=0;i<v.size();i=i+6)
    		  {
    			 obj.setProdId(Integer.parseInt(v.elementAt(i).toString().trim()));
    			 obj.setProdName(v.elementAt(i+1).toString().trim());
    			 obj.setPrice(Double.parseDouble(v.elementAt(i+3).toString().trim()));
    			 obj.setDt(v.elementAt(i+4).toString().trim());
    			 obj.setUserid(v.elementAt(i+5).toString().trim());
    			 // String result=miner.insertTrans(obj);
    			  PurchasedProducts purchase=new PurchasedProducts();
        		  purchase.setProdId(obj.getProdId());
        		  purchase.setUserid(obj.getUserid());
        		  Vector v1=jf.getValue("select transId from products where prodId="+obj.getProdId(), 1);
        		  System.out.println("trans="+v1.elementAt(0).toString().trim());
        		  purchase.setTransid(v1.elementAt(0).toString().trim());
        		  purchase.setProdName(obj.getProdName());
        		  if(purchase.registration())
        		  {
        			  
        		  }
    			//  System.out.println("result="+result);
    			  
    		  }
    		  System.out.println("transids="+transids);
    		  
    		  if(jf.execute("update orders set orderstatus='paid', paymentsts='paid' where orderno="+request.getParameter("orderno").trim()))
        	  {   
        	  }
    	  
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return "Success.jsp?type=OrderProcess";
	}
	@RequestMapping("/MyOrders1")
	public ModelAndView MyOrders1(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	 MyOrders order=new MyOrders();
    	 order.setUserid(ses.getAttribute("userid").toString().trim());
    	 order.getMyOrders("processed");
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("orders1.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/MyOrders")
	public ModelAndView MyOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	 MyOrders order=new MyOrders();
    	 order.setUserid(ses.getAttribute("userid").toString().trim());
    	 order.getMyOrders("all");
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("orders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/viewOrderDetails1")
	public ModelAndView viewOrderDetails1(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  Orders order=new  Orders();
    	  order.getOrderDetails(Integer.parseInt(request.getParameter("orderNo").trim()));
    	 List<Orders> lstorder=new ArrayList<Orders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("OrderDetails1.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/viewOrderDetails")
	public ModelAndView viewOrderDetails(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  Orders order=new  Orders();
    	  order.getOrderDetails(Integer.parseInt(request.getParameter("orderNo").trim()));
    	 List<Orders> lstorder=new ArrayList<Orders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("OrderDetails.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/PendingOrders")
	public ModelAndView PendingOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  MyOrders order=new  MyOrders();
    	  order.getPendingOrders(ses.getAttribute("userid").toString().trim());
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("PendingOrders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/ProcessedOrders")
	public ModelAndView ProcessedOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  MyOrders order=new  MyOrders();
    	  order.getProcessedOrders(ses.getAttribute("userid").toString().trim());
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("ProcessedOrders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/PaidOrders")
	public ModelAndView PaidOrders(HttpServletRequest request,HttpSession ses)
	{
    	ModelAndView mv=new ModelAndView();
    	
    	try {
    	  MyOrders order=new  MyOrders();
    	  order.getPaidOrders(ses.getAttribute("userid").toString().trim());
    	 List<MyOrders> lstorder=new ArrayList<MyOrders>();
    	 lstorder=order.getLstorders();
    	 mv.setViewName("PaidOrders.jsp");
    	 mv.addObject("lst",lstorder);
    	}
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return mv;
	}
	@RequestMapping("/UploadProduct")
	public ModelAndView UploadProduct(HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView();
		String transid=request.getParameter("transid").trim();
		mv.setViewName("UploadProduct.jsp");
		mv.addObject("transid",transid.trim());
		return mv;
	}
	@RequestMapping("/viewFarmers")
	public ModelAndView viewFarmers(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("farmer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewFarmers.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/viewFarmers1")
	public ModelAndView viewFarmers1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("farmer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewFarmers1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewDistributors")
	public ModelAndView viewDistributors(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("distributor");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDistributors.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewDistributors1")
	public ModelAndView viewDistributors1(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("distributor");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewDistributors1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("viewRetailer")
	public ModelAndView viewRetailer(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("retailer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewRetailer.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	} 
	@RequestMapping("/viewCustomers")
	public ModelAndView viewCustomer(HttpSession ses)
	{
		
		List<UserReg> lst = new ArrayList<UserReg>();
		UserReg obj=new UserReg();
		obj.setUtype("customer");
		 obj.getUsers();
		 lst=obj.getLstuser();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewCust.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/ChangeQuantity")
	public ModelAndView ChangeQuantity(HttpSession ses,Products obj)
	{
		
		List<Products> lst = new ArrayList<Products>();
		obj.updateQuan(); 
		 
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Success.jsp?type=ProdUpdate");
		 
		return mv;
		 
	}
	@RequestMapping("/ChangePrice")
	public ModelAndView ChangePrice(HttpSession ses,Products obj)
	{
		
		List<Products> lst = new ArrayList<Products>();
		obj.updatePrice(); 
		 
		ModelAndView mv = new ModelAndView();

		mv.setViewName("Success.jsp?type=ProdUpdate");
		 
		return mv;
		 
	}
	@RequestMapping("/viewProducts")
	public ModelAndView viewFarmerProducts(HttpSession ses)
	{
		
		List<Products> lst = new ArrayList<Products>();
		Products obj=new Products();
		obj.setUserid(ses.getAttribute("userid").toString().trim());
		 obj.getProducts();
		 lst=obj.getLstprod();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProd.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/ShowCart")
	public ModelAndView ShowCart(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		 
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/AddToCart")
	public ModelAndView addToCart(Cart cart,HttpSession ses,HttpServletRequest request)
	{
		ModelAndView mv=new ModelAndView(); 
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		
		if(!cart.checkProductInCartORNOT(cart.getProdId(), ses.getAttribute("userid").toString().trim()))
		{
			if(cart.addToCart())
			{
				// JavaFuns jf=new JavaFuns();
		    	 // if(jf.execute("update products set searchCount=searchCount+1 where id="+cart.getProdId()))
		    	//  {
		    		  
		    	//  }
				
			}	
			List<Products> lst = new ArrayList<Products>();
			Products obj=new Products();
			obj.setUserid(request.getParameter("userid1").toString().trim());
			 obj.getProducts();
			 lst=obj.getLstprod();

		 
			mv.addObject("lst", lst);
				 System.out.println("list size="+lst.size());
				mv.addObject("stf",lst);
				mv.addObject("subcategory","NA");
				mv.addObject("category","NA");
				mv.addObject("availquan",request.getParameter("availquan").toString().trim());
				mv.setViewName(cart.getPage());
		}
		else
		{
			
			cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		}
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/PlaceOrder")
	public String PlaceOrder(HttpServletRequest request,HttpSession ses)
	{
    	try {
    		JavaFuns jf=new JavaFuns();
    	 Orders order=new Orders();
    	 Vector v1=jf.getValue("select prodId,quantity from cart where userid='"+ses.getAttribute("userid").toString().trim()+"'",2);
    	 for(int i=0;i<v1.size();i=i+2)
    	 {
    		 if(jf.execute("update products set availQuantity=availQuantity-"+v1.elementAt(i+1).toString().trim()+" where prodId="+v1.elementAt(i).toString().trim()))
     	    {}
    	 }
    	 Vector v=jf.getValue("select distinct(sellerUserid),sellerUserName,sellerType  from cart where userid='"+ses.getAttribute("userid").toString().trim()+"'",3);
    	 for(int i=0;i<v.size();i=i+3)
    	 {
    		 order.setUserid(ses.getAttribute("userid").toString().trim());
    		 order.setSellerUserid(v.elementAt(i).toString().trim());
    		 order.setSellerUsernm(v.elementAt(i+1).toString().trim());
    		 order.setSellerUtype(v.elementAt(i+2).toString().trim());
    	   	 if(order.placeOrder()) {}
    	    	 
    	    	}
    	 }
    	 
    	catch (Exception e) {
    		System.out.println("err in place order="+e.getMessage());
			// TODO: handle exception
		}
    	return "Success.jsp?type=placeOrder";
	}
	
	 
	@RequestMapping("/ViewProducts1")
	public ModelAndView viewFarmerProducts1(HttpSession ses,HttpServletRequest request)
	{
		
		List<Products> lst = new ArrayList<Products>();
		Products obj=new Products();
		obj.setUserid(request.getParameter("userid").toString().trim());
		 obj.getProducts();
		 lst=obj.getLstprod();

		ModelAndView mv = new ModelAndView();

		mv.setViewName("viewProd1.jsp");
		mv.addObject("lst", lst); 
		return mv;
		 
	}
	@RequestMapping("/RemoveItem")
	public ModelAndView RemoveItem(Cart cart,HttpSession ses)
	{
		ModelAndView mv=new ModelAndView(); 
		if(cart.removeItems())
		{
			
		}
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("Cart.jsp");
		 
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("/updateCart")
	public ModelAndView updateCart(HttpServletRequest request, HttpSession ses)
	{
		Cart cart=new Cart();
		int cartid=Integer.parseInt(request.getParameter("cartid").toString());
		int quant=Integer.parseInt(request.getParameter("quan").toString());
		cart.setCartid(cartid);
		cart.setQuantity(quant);
		ModelAndView mv=new ModelAndView(); 
		if(cart.updateItems())
		{
			
		}
		cart.setUserid(ses.getAttribute("userid").toString().trim());
		 cart.getCartDetails();
			List<Cart> lstcart=cart.getLstcart();
			mv.addObject("lst",lstcart);
			mv.setViewName("cartAjax.jsp");
		 System.out.println("in cart ajax");
	//mv.addObject("branch",request.getParameter("branch").toString().trim());
			return mv;
		 
	}
	@RequestMapping("RegProduct")
	public String RegProduct(Products obj,HttpServletRequest request,HttpSession ses)
	{
		boolean flag=true;
		double p=0;
		 try
		 {
			 JavaFuns jf=new JavaFuns();
			 if(ses.getAttribute("utype").equals("distributor")||ses.getAttribute("utype").equals("retailer"))
			 {
				 Vector v=jf.getValue("select price from prodPrices where prodId=(select prodId from purchasedproductsview where transId='"+obj.getTransid()+"') and unit='"+obj.getUnit()+"'", 1);
				 Vector v1=jf.getValue("select percent from prodpercent where utype='"+ses.getAttribute("utype").toString().trim()+"'", 1);
				 double percent=Double.parseDouble(v1.elementAt(0).toString().trim());
				   p=Double.parseDouble(v.elementAt(0).toString().trim());
				 double per=p*(percent/100);
				 p=p+per;
				 System.out.println("price="+p+" new price="+obj.getPrice());
				 if(obj.getPrice()>p)
				 {
					 flag=false;
				 }
			 }
			 if(flag)
			 {
			 MultipartFile file=obj.getFile();
		  
		 String filepath=request.getServletContext().getRealPath("/")+"/Products/";
		  
		 System.out.println("path="+filepath);
		 File f=new File(filepath);
		 f.mkdir();
		 filepath+="/"+ses.getAttribute("userid").toString().trim();
		 f=new File(filepath);
		 f.mkdir();
		  
			 obj.getId();
			 int mx=obj.getProdId();
			 String fileName=mx+"."+ file.getOriginalFilename().split("\\.")[1];
			 file.transferTo(new File(filepath+"/"+fileName));
			 obj.setProdId(mx);
			 obj.setCoverphoto(fileName);
			 obj.setUserid(ses.getAttribute("userid").toString().trim());
			Date dt=new Date();
			obj.setDt(dt.getDate()+"/"+(dt.getMonth()+1)+"/"+(dt.getYear()+1900));
			obj.setTm(dt.getHours()+":"+(dt.getMinutes()));
			 
			obj.setUserType(ses.getAttribute("utype").toString().trim());
			obj.setProdType(ses.getAttribute("utype").toString().trim()+"Product");				 
			obj.setUsernm(ses.getAttribute("username").toString().trim());
			System.out.println(ses.getAttribute("username").toString().trim());
			 if(obj.registration() )
			 {
				 
				 return "Success.jsp?type=RegProd";
			 }
			 else
			 { 
				 return "Failure.jsp?type=RegProd";
			 }
			 }
			 else
			 {
				 return "Failure.jsp?type=GreaterPrice&p="+p;
			 }
		 }
		 catch (Exception e) {
			// TODO: handle exception
			 System.out.println("err="+e.getMessage());
			 return("Failure.jsp?type=RegProd");
		}
	}
	 
	 
}

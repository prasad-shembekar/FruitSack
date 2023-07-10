
  
<%@page import="com.services.JavaFuns"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>

<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 

</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<% try{ response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("index.jsp");
}
%>
<div class="container">
  <div class="jumbotron"> 

     
<div class="row">

<div class="col-md-6"> <h2>Register Products</h2>
   <div class="form-group"> 
    <form method="post" name="frm" action="/RegProduct" enctype="multipart/form-data">
<%
Vector v=new Vector();
JavaFuns jf=new JavaFuns();
v=jf.getValue("select transId,prodName from purchasedProducts where userid='"+session.getAttribute("userid").toString().trim()+"'", 2);
%>
<table class="tblform"><tr><td>Title</td><td>
    <input type="text" name="title" class="form-control" required></input>
     </td></tr>
     <tr>
     <td>Description</td>
     <td>
  <textarea name="desc" class="form-control" required></textarea>
     
 </td></tr>
 <tr>
     <td>Price</td>
     <td>
  <input type="number" step="any" name="price" class="form-control" required></input>
     
 </td></tr>
 <tr>
     <td>Unit</td>
     <td>
   <select name="unit" class="form-control" required>
   <option value="Quintal">Quintal</option>
   <option value="Dozens">Dozens</option>
   <option value="Kg">KiloGram</option>
   <option value="Gram">Gram</option>
    <option value="Gallon">Gallon</option> 
   <option value="Liters">Liters</option>  
   <option value="ml">Mili-Liters</option>
   </select>
 </td></tr>
 <%if(session.getAttribute("utype").toString().trim().equals("seller")){ %>
 <input type="hidden" name="transid" value="0"/>
 <%}else{ %>
 <tr>
     <td>Raw Products Used/Purchased Product</td>
     <td>
   <select name="transid" required class="form-control" required>
   <option value=""><--Select--></option>
   <%for(int i=0;i<v.size();i=i+2)
	   {%>
   <option value="<%=v.elementAt(i).toString().trim() %>"><%=v.elementAt(i+1).toString().trim() %></option>
   <%} %>
   
   </select>
 </td></tr>
 <%} %>
 <tr>
     <td>Available Quantity/unit</td>
     <td>
  <input type="number" step="any" name="quantity" class="form-control" required></input>
     
 </td></tr>
 <tr><td>Product Cover Photo</td><td>
    <input type="file" name="file" class="form-control" required ></input>
 </td></tr>
 <tr><td colspan="2">
 <!-- <input type="hidden" value="<%=request.getAttribute("transid").toString().trim() %>" name="transid"/> -->
    <input type="submit" class="btn btn-primary" value="Submit" />
    </td></tr>
 
</table></form>
 
</div></div>
 <div class="col-md-6">
 <img src="images/products.jpg" width="100%" class="img-responsive"/>
 </div>
</div>
</div>
<%}
catch(Exception ex)
{
	
} %>

</div>
</body>
</html>
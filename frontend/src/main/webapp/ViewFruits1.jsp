<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="soham"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
 <link rel="stylesheet" href="css/cust.css">

<title>Fruits Quality Prediction</title>
</head>
<body><jsp:include page="Top.jsp"></jsp:include>
<%

String userid=String.valueOf(session.getAttribute("userid"));
String usertype=String.valueOf(session.getAttribute("utype"));
if(!userid.equalsIgnoreCase("null")){	
	
session.setMaxInactiveInterval(10*60);
 
%>

<div class="container">
<div class="row">
<div class="col-md-12">
<h2>  Fruits</h2>
<hr>

 
  
<soham:forEach items="${std}" var="rec">
<div class="col-md-3">
<table class="table table-bordered">
<tr>
<td rowspan="3">
<img src="Uploads/${rec.path}" class="img-responsive" width="70%"/>
</td>
<td>
<b>Title</b></td><td>${rec.title}
</td>
</tr>
<tr>
<td>
<b>Availability</b></td><td>${rec.availsts}
</td>
</tr>
<tr>
<td>
<b>Stock</b></td><td>${rec.stock}
</td>
</tr>
<tr>
<td>
<b>Description</b></td><td colspan="2">${rec.desc}
</td>
</tr>
 <tr>
	<td colspan="1">
	<soham:choose>
  <soham:when test="${rec.stock>0}">
  
   
	 <form action="AddToCart" method="post">
                                     <input type="hidden" name="prodId" value="${userdsc.getProdId()}"> 
                                        <input type="hidden" name="title" value="${userdsc.title}">
                                         <input type="hidden" name="price" value="${userdsc.price}">
                                         <input type="hidden" name="availquan" value="${userdsc.getQuantity()}">
                                         <input type="hidden" name="userid1" value="${userdsc.userid}">
                                         <input type="hidden" name="selleruid" value="${userdsc.userid}">
                                         <input type="hidden" name="sellerunm" value="${userdsc.usernm}">
                                         <input type="hidden" name="sellerutype" value="${userdsc.userType}">
                                         <input type="hidden" name="page" value="viewProd1.jsp">
                                         <input type="hidden" name="category" value="NA">
                                         <input type="hidden" name="subcategory" value="NA">
                                        <button type="submit" class="hub-cart phub-cart btn">
                                            <i class="fa fa-cart-plus" aria-hidden="true"></i>
                                        </button>
                                        <a href="#" data-toggle="modal" data-target="#myModal1"></a>
                                    </form>
             </soham:when> 
  <soham:otherwise>Out of Stock
  </soham:otherwise>
</soham:choose>
	                       
	</td>
	 
	</tr>
</table>
 
</div>
 
</soham:forEach>
 
</div>
 
</div>

<%
}
else{
	%>
	<h2>Invalid Session...Login again</h2>
	<br>
	<a href="index.jsp">Login</a>
	
	<%
}

%>
</body>
</html>
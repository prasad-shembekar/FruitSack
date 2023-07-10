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
<h2>Registered Fruits</h2>
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
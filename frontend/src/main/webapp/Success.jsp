<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="DefaultTop.jsp"></jsp:include>
<div class="container"><br/><br/>
<%
if(request.getParameter("type").toString().trim().equals("Reg"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
if(request.getParameter("type").toString().trim().equals("RegUser"))
{
	%><h2 class="h2">Your Registration Done Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%}
else if(request.getParameter("type").toString().trim().equals("Prediction"))
{
	%>
	New Price Calculated Successfully...Please check in processed orders
	<br/>
	<a href="admin">Home</a>
	<%
}
else if(request.getParameter("type").toString().trim().equals("DsTrained"))
{
	%>
	Dataset Trained Successfully...
	<br/>
	<a href="admin">Home</a>
	<%
}

else if(request.getParameter("type").toString().trim().equals("RegProd"))
{
	%><h2 class="h2">Product Registered Successfully....</h2>
	<br/>
	<a href="seller">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ProdPerReg"))
{
	%><h2 class="h2">Product Percentage Registered Successfully....</h2>
	<br/>
	<a href="admin">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ProdUpdate"))
{
	%><h2 class="h2">Product Updated Successfully....</h2>
	<br/>
	<a href="viewProducts">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("placeOrder"))
{
	%><h2 class="h2">Order Placed Successfully....</h2>
	<br/>
	<a href="<%=session.getAttribute("utype").toString().trim() %>">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegProd"))
{
	%><h2 class="h2">Product Registered Successfully....</h2>
	<br/>
	<a href="seller">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("OrderProcess"))
{
	%><h2 class="h2">Order Processed Successfully....</h2>
	<br/>
	<a href="<%=session.getAttribute("utype").toString().trim() %>">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegSellerFruits"))
{
	%><h2 class="h2">Fruits Registered Successfully....</h2>
	<br/>
	<a href="seller">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegSeller"))
{
	%><h2 class="h2">Fruits Seller Registered Successfully....</h2>
	<br/>
	<a href="admin">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegPrev"))
{
	%><h2 class="h2">Preventive Measures Registered Successfully....</h2>
	<br/>
	<a href="seller">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegComm"))
{
	%><h2 class="h2">Query Sent to Expert Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}else if(request.getParameter("type").toString().trim().equals("CommReply"))
{
	%><h2 class="h2">Query Processed Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegPlant"))
{
	%><h2 class="h2">Plant Registration Done Successfully....</h2>
	<br/>
	<a href="expert">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("RegPlantDisease"))
{
	%><h2 class="h2">Plant Disease Registered Successfully....</h2>
	<br/>
	<a href="expert">Home</a>
<%
}else if(request.getParameter("type").toString().trim().equals("datasetInsrt"))
{
	%><h2 class="h2">DataSet Image Registered Successfully....</h2>
	<br/>
	<a href="admin">Home</a>
<%
}else if(request.getParameter("type").toString().trim().equals("InputImgInsrt"))
{
	%><h2 class="h2">Input Image Registered Successfully....</h2>
	<br/>
	<a href="farmer">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ChangePass"))
{
	%><h2 class="h2">Password Changed Successfully....</h2>
	<br/>
	<a href="/<%=session.getAttribute("utype").toString().trim() %>">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("ReqUpd"))
{
	%><h2 class="h2">Request Updated Successfully....</h2>
	<br/>
	<a href="cityadminHome">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("passEmail"))
{
	%><h2 class="h2">New Password Sent on your registered email id Successfully....</h2>
	<br/>
	<a href="home">Home</a>
<%
}
else if(request.getParameter("type").toString().trim().equals("Doc"))
{
	%><h2 class="h2">Document Uploaded Successfully....</h2>
	<br/>
 
<%
}
%>
</div>
</body>
</html>
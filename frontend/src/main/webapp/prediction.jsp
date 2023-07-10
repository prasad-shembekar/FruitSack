<%@page import="com.services.JavaFuns"%>
<%@page import="com.models.*"%>
<%@page import="java.util.List"%>
<%@page import="com.models.GetStateNCities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script language="Javascript" type="text/javascript">
 

function createRequestObject() {
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest) {
            tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }

    return tmpXmlHttpObject;
}


var http = createRequestObject();

function makeGetRequest(st) {
   // st=document.frm.state.value;
   
    http.open('get', 'Cities?state=' + st);
    http.onreadystatechange = processResponse;
    http.send(null);
}

function processResponse() {
    if(http.readyState == 4){
        var response = http.responseText;
        document.getElementById('cities').innerHTML = response;
    }
}
 
</script>

</head>
<body>
<jsp:include page="Top.jsp"></jsp:include>
<%  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);%>
<div class="container">
<div class="row">
<%
JavaFuns jf=new JavaFuns();
String qr="delete from prediction where orderid_prod="+request.getParameter("porderid").toString().trim();
if(jf.execute(qr)){}
%>
<div class="col-md-6">
<img src="images/Registration.png" width="100%"/>
</div>
<div class="col-md-6">
<div ><center><h2>Fruit Quality Prediction</h2></center><br/>
 <form id="frm" action="http://localhost:80/FruitQualityPrediction/multiPhotosHandler1.py" method="post"  enctype="multipart/form-data">
									 
									  <table class="tblform">
						    <%
									 Fruits obj=new Fruits();
									 obj.getFruits();
									 List<Fruits> lst=obj.getLstfruits();
									 %>
									  
                <tr>
									 <td>Fruit</td>
									 <td> <input type="hidden" name="porderid" value='<%=request.getParameter("porderid") %>'/>
									 <input type="hidden" name="orderid" value='<%=request.getParameter("orderid") %>'/>
									 <select required name="title" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getName() %>"><%=lst.get(i).getName()%></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>			   
               <tr><td>Upload Image</td>
                <td><input type="file"   class="form-control" multiple="multiple" name="file" required></td></tr>
                 <tr>
									     <tr>
		 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
		  </td></tr>
		  </table>
		  </form>
</div>
</div>

</div>

</div>
</body>
</html>
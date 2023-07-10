<%@page import="com.models.*"%>
<%@page import="java.util.List"%>
<%@page import="com.models.GetStateNCities"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="zxx">

<head>
  <title>Organic Store Agriculture Category bootstrap Responsive web Template| Home :: w3layouts</title>
  <!--meta tags -->
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="keywords" content="Organic Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
         Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"
  />
  <script>
    addEventListener("load", function () {
      setTimeout(hideURLbar, 0);
    }, false);


    function hideURLbar() {
      window.scrollTo(0, 1);
    }
  </script>
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
  <!--booststrap-->
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">
  <!--//booststrap end-->
  <!-- font-awesome icons -->
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <!-- //font-awesome icons -->
  <!--stylesheets-->
  <link href="css/style.css" rel='stylesheet' type='text/css' media="all">
  <!--//stylesheets-->
  <link href="//fonts.googleapis.com/css?family=Raleway:400,500,600,700" rel="stylesheet">
  <link href="//fonts.googleapis.com/css?family=Patrick+Hand" rel="stylesheet">
  <link href="//fonts.googleapis.com/css?family=Roboto:400,500,700" rel="stylesheet">
</head>

<body>
  <!-- //banner -->
  <div class="banner-left-side" id="home">
    <!-- header -->
    <div class="headder-top">
      <!-- nav -->
      <nav>
        <div id="logo">
          <h1>
            <a href="home">OS</a>
          </h1>
        </div>
        <div class="sub-headder position-relative">
          <h6>
            <a href="home">Organic
              <br>Store</a>
          </h6>
        </div>
        <label for="drop" class="toggle">Menu</label>
        <input type="checkbox" id="drop">
        <ul class="menu mt-2">
          <li class="active">
            <a href="home">Home</a>
          </li>
           
          <li>
            <!-- First Tier Drop Down -->
            <!--<label for="drop-2" class="toggle toogle-2">Dropdown <span class="fa fa-angle-down" aria-hidden="true"></span>
              </label>
              <a href="#">Dropdown <span class="fa fa-angle-down" aria-hidden="true"></span></a>
              <input type="checkbox" id="drop-2">
              <ul>
                <li><a href="gallery.html" class="drop-text">Gallery</a></li>
                <li><a href="menu.html" class="drop-text">Menu</a></li>
                <li><a href="recipe.html" class="drop-text">Recipes</a></li>
              </ul>
            </li>-->
            <li>
              <a href="#login">Login</a>
            </li>
            <li>
              <a href="#about">Registration</a>
            </li>
            
        </ul>
      </nav>
      <!-- //nav -->
    </div>
    <!-- //header -->
    <!-- banner -->
    <div class="main-banner text-center">
      <div class="container">
         
        <div class="banner-right-txt">
          <h5 class="mb-sm-3 mb-2">Healthy Food</h5>
          <h4>Organic Store </h4>
        </div>
        <div class="slide-info-txt">
          <p>Fruits Quality & Price Calculation Using Deep Neural Network</p>
        </div>
      </div>
    </div>
  </div>
  <!-- //banner -->
	 
<%
try{
//JsonReaderBing json1=new JsonReaderBing();
//WeatherAPI api=new WeatherAPI();
//json1.readJsonFromUrl1("Sai nagar Amravati", "444607");
// api.readJsonFromUrl1(json1.getLat(), json1.getLng());
}
catch(Exception ee)
{
	System.out.println("errr="+ee.getMessage());
}

%>
	  
	<!-- //main -->
<!-- vegetable-info -->
  <section id="login" class="veg-info py-lg-4 py-md-4 py-sm-3 py-3">
    <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
      <div class="row">
        <div class="col-lg-7">
          <img src="images/bb4.jpg" alt="news image" class="img-fluid">
        </div>
        <div class="col-lg-5 veg-list-text">
          
          <div class="row">
            <div class="col-lg-11 col-md-7 col-sm-7 col-7 text-right py-2 py-1 w3three-veg-org">
               
               <form name="frm" method="post" action="login">
 <div class="jumbotron">
<table class="tblform ">
<tr>
<td>UserID
<td><input type="text" class="form-control" required name="txtuserid">
</tr>

<tr>
<td>Password
<td><input type="password"  class="form-control" required name="txtpass">
</tr>

<tr>
<td><input type="submit" class="btn btn-primary" value="Submit">
<td>
</tr>
<tr>
<td><a href="forgot">Password Recovery</a>
<td>
</tr>
</table></div>
</form>
                </div>
             
          </div>
        </div>
      </div>
    </div>
  </section>
  <!--//vegetable-info -->
	   
	<!-- //about -->

	 

	<!-- about -->
  <section class="about py-lg-4 py-md-4 py-sm-3 py-3" id="about">
    <div class="container py-lg-5 py-md-4 py-sm-4 py-3">
      <h3 class="title text-center mb-2">Registration</h3>
       
      <div class="row">
        <div class="col-lg-5 video-info-img text-center position-relative">
          <div class="abut-img-w3l">
            <img src="images/bb1.jpg" alt="" class="img-fluid">
          </div>
          <div class="abut-img-two">
            <img src="images/bb2.jpg" alt="" class="img-fluid">
          </div>
        </div>
        <div class="col-lg-7 left-abut-txt ">
          <div class="about-right-grid">
            <h2 class="mb-3">Our food should be our medicine,Our medicine organics should be our food your health</h2>
             
              <form id="frm" action="RegUser" method="post">
						 <div class="jumbotron">			 
									  <table class="tblform">
									 <tr><td>UserName</td>
                <td><input type="text" class="form-control"  name="name" required></td></tr>
            <tr><td>UserID</td>
                <td><input type="text"  class="form-control"  name="userid" required></td></tr>
                       
             <tr><td>Password</td>
                <td><input type="password"  class="form-control"  name="pass" required></td></tr>
            
			<tr>
				<td>Mobile Number</td>
				<td>
				<input type="text" required  class="form-control"  name="mobile" pattern="^\d{10}$"  >
				</td>
			</tr>
			
                <tr><td>Email ID:</td>
                    <td><input type="text" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"  name="email" required></td>
                </tr>
                  <tr>
<td>Gender</td>
<td>
<input type="radio" name="gender" value="Male"   checked="true" required >Male</input>
<input type="radio" name="gender" value="Female"  required>Female</input>
<input type="hidden" name="utype" value="user"/>
</td>
</tr>    
  
									 <%
									 GetStateNCities obj=new GetStateNCities();
									 obj.getStates();
									 List<States> lst=obj.getLststate();
									 %>
									  <tr>
									 <td>State
									 </td>
									 <td> 
									 <select required name="state" class="form-control" onchange="makeGetRequest(this.value)">
									 <option value=""><--select--></option>
										<%for(int i=0;i<lst.size();i++)
											{%>
									 <option value="<%=lst.get(i).getState() %>"><%=lst.get(i).getState() %></option>											
											<%}%>															  
									 </select>
									 </td>
									 </tr>
									   <tr>
									 <td>City
									 </td>
									 <td> 
									<div id="cities"></div>
									 </td>
									 </tr>
									  
	            <td>  Address</td>
		            <td>
		            <textarea rows="5" cols="25" required name="addr" class="form-control" ></textarea>          
		            </td>
            </tr>
								 
									 
<tr>
<td>DOB</td>
<td>
<input type="date" name="dob" required class="form-control"/>
</td>
</tr>
 
 

									 <tr>
									 <td colspan="2"><input type="submit" value="Submit" class="btn btn-primary"/>
									 </td></tr>
									 </table></div>
									 </form>
               </div>
        </div>
      </div>
    </div>
  </section>
  <!--//about -->
				</div>
				<div class="col-xl-6 mt-xl-0 mt-md-5 mt-4 text-xl-right text-center">
					<img class="img-fluid rounded" src="images/blog.jpg" alt="">
				</div>
			</div>
		</div>
	</section>
	<!-- //blog -->

	 
	<!-- popup-->
	<div id="gal1" class="popup-effect animate">
		<div class="popup">
			<img src="images/g1.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal2" class="popup-effect animate">
		<div class="popup">
			<img src="images/g2.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal3" class="popup-effect animate">
		<div class="popup">
			<img src="images/g3.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup3 -->
	<!-- popup-->
	<div id="gal4" class="popup-effect animate">
		<div class="popup">
			<img src="images/g4.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal5" class="popup-effect animate">
		<div class="popup">
			<img src="images/g5.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal6" class="popup-effect animate">
		<div class="popup">
			<img src="images/g6.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal7" class="popup-effect animate">
		<div class="popup">
			<img src="images/g7.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal8" class="popup-effect animate">
		<div class="popup">
			<img src="images/g8.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- popup-->
	<div id="gal9" class="popup-effect animate">
		<div class="popup">
			<img src="images/g9.jpg" alt="Popup Image" class="img-fluid" />
			<h4 class="p-mask">Plants</h4>
			<a class="close" href="#gallery">&times;</a>
		</div>
	</div>
	<!-- //popup -->
	<!-- //gallery -->

	   

	<!-- move top icon -->
	<a href="#home" class="move-top text-right "> <img src="images/move-top.png"/></a>
	<!-- //move top icon -->
<footer class="bottem-wthree-footer text-center py-md-4 py-3">
    <p>
      © 2023 Fruits Quality & Price Calculation Using Deep Neural Network. All Rights Reserved  </p>
  </footer>
  <!--//footer-copy-right -->

</body>

</html>
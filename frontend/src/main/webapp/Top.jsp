  
<!DOCTYPE html>
<%@page import="com.models.Cart"%>
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

<%
try
{  response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
if(session.getAttribute("userid")==null)
{
	response.sendRedirect("home");
}
%>

  <!-- //banner -->
  <div class="banner1-left-side" id="home">
    <!-- header -->
    <div class="headder-top">
      <!-- nav -->
      <nav>
        <div id="logo">
          <h1>
            <a href="index.html">OS</a>
          </h1>
        </div>
        <div class="sub-headder position-relative">
          <h6>
            <a href="index.html">Organic
              <br>Store</a>
          </h6>
        </div>
        <label for="drop" class="toggle">Menu</label>
        <input type="checkbox" id="drop">
        
          <ul class="menu mt-2">
								<li ><a href="<%=session.getAttribute("utype").toString().trim()%>">Home</a></li>
								
								
								<%if(session.getAttribute("utype").toString().trim().equals("admin"))
                                	{
                                	%>
                                	<li><a href="registration">Register Seller</a></li>
                                	<li><a href="RegFruitsDataSet">Generate DataSet</a> </li>
                                	<li><a href="TrainDataset">Train DataSet</a> </li>
								 <li>
             <div class="dropdown">
  <button type="button" style="background:transparent;border:none" class="dropdown-toggle nav-item nav-link" data-toggle="dropdown">
    Reports
  </button>
  <div class="dropdown-menu">
    <a class="dropdown-item" href="viewSellers">Seller Details</a>
    </div>
</div> 
            </li>
          
								 <%} else if(session.getAttribute("utype").toString().trim().equals("user"))
                            	{
                            	%>
                            		<li class="nav-item"><a class="nav-link" href="viewRetailer1" >view Sellers</a></li>
                            	<li class="nav-item"><a class="nav-link" href="MyOrders" >My Orders</a></li>
                            	<li class="nav-item"><a class="nav-link" href="MyOrders1" >My Processed Orders</a></li>
                            	 
                                	
                             
                            	 
                            	 <%}  
                            	 else if(session.getAttribute("utype").toString().trim().equals("seller"))
                            	{
                            	%>
                            	<li>
									
									<label for="drop-2" class="toggle toggle-drop">Manage Fruits <span class="fa fa-angle-down" aria-hidden="true"></span>
									</label>
									<a href="#">Manage Fruits<span class="fa fa-angle-down" aria-hidden="true"></span></a>
									<input type="checkbox" id="drop-2" />
									<ul class="list-unstyled">
									<!-- <li><a href="RegSellerFruits">Register Fruits</a> </li> -->
									<li><a href="UploadProduct?transid=0">Upload Fruits</a>
                            	 
                            		<li class="nav-item"><a class="nav-link" href="viewProducts" >View Products</a></li>
                            	<li class="nav-item"><a class="nav-link" href="PendingOrders" >Pending Orders</a></li>
                            	<%if(!(session.getAttribute("utype").toString().trim().equals("customer")||session.getAttribute("utype").toString().trim().equals("admin"))){ %>
      <li class="nav-item"><a class="nav-link" href="ProcessedOrders">Processed Orders</a></li>
    <li class="nav-item"><a class="nav-link"href="PaidOrders">Paid Orders</a></li>
   <%} %>
                            	 </ul>
								</li>
                            		  
                            	 <%} %> 
								<li class="nav-item"><a class="nav-link" href="ChangePass">Change Password</a></li>
								<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
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
  
 

<div class="container-fluid">
    <div class="CartPanel">
    <div class="row">
    <div class="col-md-9">
      Logged in as <%=session.getAttribute("username").toString() %>( <%=session.getAttribute("utype").toString() %>)
  <%if(!(session.getAttribute("utype").toString().trim().equals("user")||session.getAttribute("utype").toString().trim().equals("admin"))){ %>
   |  <a href="ProcessedOrders">Processed Orders</a>
    |<a href="PaidOrders">Paid Orders</a>
   <%} %>
    
    </div>
    <div class="col-md-3">
   <%if(!(session.getAttribute("utype").toString().trim().equals("seller") || session.getAttribute("utype").toString().trim().equals("admin")))
                                	{
                                	%>
    <% int totalItems=0;
    Cart cart=new Cart();
    totalItems=cart.totalItemsInCart(session.getAttribute("userid").toString().trim());
    %>
   <a href="ShowCart"> Total Items in your Cart : <i class="fa fa-cart"></i> <span><%=totalItems %></span> </a>
   <%} %>
   </div>
  </div>  </div>
    </div>
<%}catch(Exception ex)
{
    	System.out.println("err="+ex.getMessage());
    	 
}%> 

  
    <!--// end-smoth-scrolling -->
    
 
 
  <!-- move top -->
  <button onclick="topFunction()" id="movetop" title="Go to top">
    <span class="fa fa-angle-up"></span>
  </button>
  <script>
    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function () {
      scrollFunction()
    };

    function scrollFunction() {
      if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("movetop").style.display = "block";
      } else {
        document.getElementById("movetop").style.display = "none";
      }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
    }
  </script>
  <!-- /move top -->
</section>
<script src="assets/js/jquery-3.3.1.min.js"></script>
<!-- //footer-28 block -->
</section>
<script>
  $(function () {
    $('.navbar-toggler').click(function () {
      $('body').toggleClass('noscroll');
    })
  });
</script>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
  integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous">
</script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
  integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous">
</script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
  integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
</script>

<!-- Template JavaScript -->
<script src="assets/js/all.js"></script>
<!-- Smooth scrolling -->
<!-- <script src="assets/js/smoothscroll.js"></script> -->
<script src="assets/js/owl.carousel.js"></script>

<!-- script for -->
<script>
  $(document).ready(function () {
    $('.owl-one').owlCarousel({
      loop: true,
      margin: 0,
      nav: true,
      responsiveClass: true,
      autoplay: false,
      autoplayTimeout: 5000,
      autoplaySpeed: 1000,
      autoplayHoverPause: false,
      responsive: {
        0: {
          items: 1,
          nav: false
        },
        480: {
          items: 1,
          nav: false
        },
        667: {
          items: 1,
          nav: true
        },
        1000: {
          items: 1,
          nav: true
        }
      }
    })
  })
</script>
<!-- //script -->

</body>

</html>
  
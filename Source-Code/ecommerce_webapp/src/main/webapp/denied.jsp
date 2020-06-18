<%
	String user = (String) request.getSession(false).getAttribute("accountId");
%>


<!DOCTYPE html>
<html lang="en">
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Sign in page</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
		 <script type = "text/javascript" src="payment.js" ></script>
		<link href="css/login.css" rel="stylesheet">
		  
	</head>
<body onload="changeHashOnLoad()">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/index">Jasper team</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="<%=request.getContextPath()%>/index">Home</a>
				</li>
				<%
				if(user!=null&&!user.isEmpty()){
				%>
				<li class="nav-item" >
					<a class="nav-link" href="<%=request.getContextPath()%>/logout">Log out</a>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>
<div class="container-fluid">
	<div class="row vdivide text-center" style="margin-top: 50px;">
		<!--sign up-->
		<div class=" col-12">
				<h2 class="alert alert-warning display-4">Error happened!</h2>
				<div class="form-wrapper">
					<h4>Your Order was denied.</h4>
					<p>Credit Card Authorization Failed.</p>
				</div>
				<a href="<%=request.getContextPath()%>/index">try again from beginning...</a>
		</div>
</div>
</div>
</body>
</html>














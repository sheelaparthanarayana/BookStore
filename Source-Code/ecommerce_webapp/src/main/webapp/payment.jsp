<%
	String res = (String) request.getSession().getAttribute("logstats");
	request.getSession().removeAttribute("logstats");
	String o="";
	if(res!=null){
	    o=res;
	}
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
			</ul>
		</div>
	</div>
</nav>
<div class="container-fluid">
	<div class="row vdivide text-center" style="margin-top: 50px;">
		<%--payment form--%>
		<div class="col-12">
			<form class="form-signup" name="formsignup" data-toggle="validator" action="<%=request.getContextPath()%>/success" method="post" onsubmit="return verifyExpiration();">
				<h3>Payment Page</h3>
				<div class="form-wrapper">
					<input type="text" name="text" placeholder="Card User Name eg. Liam Johnson" class="form-control" required>
				</div>
				<div class="form-wrapper">
					<input type="text" name="cnn" id="cardNum" placeholder="Credit Card Number eg. 1111-2222-3333-4444" class="form-control" onchange="ccverify()" required>
				</div>
				
				<div class="form-wrapper">
						<p>Credit Card Expiry: MM/YYYY</p>	
						<select name="ExpMon" id="ExpMon" title="select Expiry Month">
							<option>01</option>
							<option>02</option>
							<option>03</option>
							<option>04</option>
							<option>05</option>
							<option>06</option>
							<option>07</option>
							<option>08</option>
							<option>09</option>
							<option>10</option>
							<option selected>11</option>
							<option>12</option>
						</select>
						<select name="ExpYear" id="ExpYear" title="Select Expiry Year">
							<option selected>2018</option>
							<option>2019</option>
							<option>2020</option>
							<option>2021</option>
							<option>2022</option>
							<option>2023</option>
						</select>
					</div>
				<div class="form-wrapper">
					<input type="text" name="cvv" id="cvv" placeholder="CVV eg. 121" class="form-control" onchange="cvvverify()" required >
				</div>
				<div class="btn-group">
				<button class="btn btn-lg btn-info" type="submit" id="paynow">Pay Now</button>
				<a class="btn btn-lg btn-warning" href="<%=request.getContextPath()%>/index">Cancel</a>
				</div>
				<br>
			</form>
		</div>
</div>
</div>
</body>
</html>













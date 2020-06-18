<%
    String user = (String) request.getSession().getAttribute("acountId");
	String ERROR_MESSAGE = (String) request.getSession().getAttribute("ERROR_MESSAGE");
%>
<html>
<head>
    <title>success</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type = "text/javascript" src="payment.js" ></script>
</head>
<body onload="changeHashOnLoad()">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/">Jasper team</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/">Home</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="jumbotron">
	<h1>Hello, You are landing in page of nowhere</h1>
	<p>Error occurred when you browsing this web site</p>
	<p>Error: <%=ERROR_MESSAGE%></p>
</div>
</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="com.ecom.ecar.entity.Book" %>
<%@ page import="com.ecom.ecar.entity.Item" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String user = (String) request.getSession(false).getAttribute("accountId");
    List<Book> book = (List<Book>) request.getSession(false).getAttribute("book");
    Map<String, Item> cart = (Map<String, Item>) request.getSession().getAttribute("myCart");
    Book this_book = book.get(0);
%>
<html>
<head>
    <title>detail of <%=this_book.getTitle()%></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="css/shop.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/index">Jasper team</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/">Home</a>
                </li>
                <li class="nav-item">
                    <%
                    if (cart != null && !cart.isEmpty()) {
                    	int q = cart.entrySet().size();
                    %>
                    <a class="nav-link" href="<%=request.getContextPath()%>/checkout">Check out<i class="fa fa-shoping-cart"></i><span id="cart-item-count">(<%=q%>)</span></a>
                    <%
                    }
                    %>
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

<a href="<%=request.getContextPath()%>/" class="btn btn-warning mt-4 ml-4"><i class="fa fa-angle-left"></i> < Back</a>
<div class="container padding">
    <div class="row">
        <div class="col-md-4">
            <img class="product-img img-fluid" src="img/<%=this_book.getBookid()%>.png">
        </div>
        <div class="col-md-8">
            <h1 class="display-5"><%=this_book.getTitle()%>
            </h1>
            <h4 class="display-5"><%=this_book.getAuthor()%>
            </h4>
            <h5 class="display-5"><%=this_book.getCategory()%>
            </h5>
            <h6 class="display-6">$<%=this_book.getPrice()%>
            </h6>
            <form action="addItem" method="post">
                <input type="hidden" class="form-control" name="action" value="add">
                <input type="hidden" class="form-control" name="bookid" value="<%=this_book.getBookid()%>">
                <input type="hidden" class="form-control" name="bookTitle" value="<%=this_book.getTitle()%>">
                <input type="hidden" class="form-control" name="bookPrice" value="<%=this_book.getPrice()%>">
                <input type="number" class="form-control" name="bookQuantity" value="1" min="1" max="10" style="width: 100px" onkeydown="return false">
                <button type="submit" class="btn btn-success btn-lg mt-4">Add to Cart</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>

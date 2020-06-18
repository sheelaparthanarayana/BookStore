<%@ page import="com.ecom.ecar.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ecom.ecar.entity.Item" %>
<%
    List<Book> books = (List<Book>) request.getSession().getAttribute("books");
    List<String> categories = (List<String>) request.getSession().getAttribute("categories");
    String user = (String) request.getSession(false).getAttribute("accountId");
    String category = (String) request.getSession(false).getAttribute("category");
    Map<String, Item> cart = (Map<String, Item>) request.getSession().getAttribute("myCart");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/shop.css" rel="stylesheet">
</head>
<body>
<!--navigation-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark sticky-top">
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
                    if (cart != null && !cart.isEmpty()) {
                        int q = cart.entrySet().size();
                %>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/checkout">Cart<i class="fa fa-shoping-cart"></i><span id="cart-item-count">(<%=q%>)</span></a>
                </li>
                <%
                    }
                    if (user != null && !user.isEmpty()) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">Log out</a>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>
<!--content-->
<div class="container-fluid">
    <div class="row">

        <!--genre-->
        <div class="col-md-3">
            <h1 class="my-4">Book store</h1>
            <div class="list-group">
                <%
                    if (categories != null) {
                        for (String c : categories) {
                            String ref = request.getContextPath() + "?category=" + c;
                            String act = "";
                            if (category.equals(c)) {
                                act = "active";
                            }
                %>
                <a href="<%=ref%>" class="list-group-item <%=act%>"><%=c%>
                </a>
                <%
                        }
                    }
                %>
            </div>
        </div>

        <!--list item-->
        <div class="col-md-9 container-fluid">
            <div class="card-columns">
                <%
                    if (books != null) {
                        for (Book b : books) {
                            String ref = request.getContextPath() + "/detail?bookid=" + b.getBookid();
                %>
                <div class="card mt-4">
                    <a href="<%=ref%>"><img src="img/<%=b.getBookid()%>.png" alt="<%=b.getTitle()%>"
                                            class="card-img-top index-list-img "></a>
                    <div class="card-body">
                        <h4 class="card-title"><%=b.getTitle()%>
                        </h4>
                        <h5><%=b.getAuthor()%>
                        </h5>
                        <p class="card-text">
                            $<%=b.getPrice()%>
                        </p>
                        <form action="addItem" method="post">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="bookid" value="<%=b.getBookid()%>">
                            <input type="hidden" name="bookTitle" value="<%=b.getTitle()%>">
                            <input type="hidden" name="bookPrice" value="<%=b.getPrice()%>">
                            <input type="hidden" name="bookQuantity" value="1">
                            <button type="submit" class="btn btn-success">Quick Add</button>
                        </form>
                    </div>
                </div>
                <%
                    }
                } else {
                %>
                <div class="col-12 text-center">
                    <h1 class="text-muted">Nothing to show here</h1>
                </div>
                <%
                    }
                %>
            </div>
        </div>


    </div>
</div>


</body>
</html>
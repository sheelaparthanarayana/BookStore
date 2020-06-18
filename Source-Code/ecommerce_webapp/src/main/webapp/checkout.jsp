<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="com.ecom.ecar.entity.Item" %>
<%@ page import="java.util.Map" %>
<%
    String user = (String) request.getSession(false).getAttribute("userEmail");
    Map<String, Item> cart = (LinkedHashMap) request.getSession(false).getAttribute("myCart");
%>
<html>
<head>
    <title>check out</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/index">Jasper team</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<%=request.getContextPath()%>/index">home</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid col-12">
    <div class="container ml-auto">
        <%
            if (cart != null && !cart.isEmpty()) {
        %>
        <div class="card card-outline-secondary my-4">
            <div class="card-header">Item in cart</div>
            <div class="card-body container-fluid">


                <div class="container">
                    <table class="table table-hover table-responsive">
                        <thead>
                        <tr>
                            <th style="width:50%">Product</th>
                            <th style="width:10%">Price</th>
                            <th style="width:8%">Quantity</th>
                            <th style="width:22%" class="text-center">Subtotal</th>
                            <th style="width:10%">action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            int totalPrice = 0;
                            for (Map.Entry<String, Item> itemGroup : cart.entrySet()) {
                                Item item = itemGroup.getValue();
                                totalPrice = totalPrice + item.getPrice() * item.getQuantity();
                        %>
                        <form action="addItem" method="post">
                            <tr>
                                <td data-th="Product">
                                    <div class="row">
                                        <div class="col-sm-2 hidden-xs"><img src="img/<%=item.getId()%>.png"
                                                                             style="height: 60px"
                                                                             class="img-responsive"/></div>
                                        <div class="col-sm-10">
                                            <h4 class="nomargin form-controls"><%=item.getTitle()%>
                                            </h4>
                                            <input type="hidden" name="bookid" class="form-control" value="<%=item.getId()%>">
                                            <p><%=item.getId()%>
                                            </p>
                                        </div>
                                    </div>
                                </td>
                                <td data-th="Price">$<%=item.getPrice()%>
                                </td>
                                <td data-th="Quantity">
                                    <input type="number" class="form-control text-center"
                                           name="bookQuantity" value="<%=item.getQuantity()%>" min="1" max="10" onkeydown="return false"></td>
                                <td data-th="Subtotal" class="text-center" >$<%=item.getPrice() * item.getQuantity()%>
                                </td>
                                <td data-th="" >
                                    <button class="btn btn-info btn-sm" type="submit" name="action" value="update"><i
                                            class="fa fa-refresh"></i></button>
                                    <button class="btn btn-danger btn-sm" type="submit" name="action" value="delete"><i
                                            class="fa fa-trash-o"></i></button>
                                </td>
                            </tr>
                        </form>
                        <%
                            }
                            request.getSession().setAttribute("total_price", totalPrice);
                        %>
                        </tbody>
                        <tfoot>
                        <tr class="d-block d-sm-none">
                            <td class="text-center"><strong>Total $<%=totalPrice%>
                            </strong></td>
                        </tr>
                        <tr>
                            <td><a href="<%=request.getContextPath()%>/" class="btn btn-warning"><i
                                    class="fa fa-angle-left"></i> Continue Shopping</a></td>
                            <td colspan="2" class="hidden-xs"></td>
                            <td class="d-none d-sm-block text-center"><strong>Total $<%=totalPrice%>
                            </strong></td>
                            <td><a href="<%=request.getContextPath()%>/order.jsp" class="btn btn-success btn-block">Checkout<i
                                    class="fa fa-angle-right"></i></a>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
                <%
                } else {
                %>
                <h4 class="text-center display-3 mt-4">Nothing in cart</h4>
                <%
                    }
                %>
            </div>
        </div>
        <!--card-->
    </div>

</div>


</body>
</html>

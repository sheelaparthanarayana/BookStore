<%@ page import="com.ecom.ecar.entity.UserInfo" %>
<%@ page import="com.ecom.ecar.entity.Account" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ecom.ecar.entity.Item" %>
<%
    String accountid = (String) request.getSession(false).getAttribute("accountId");
    String shipping_info = (String) request.getSession(false).getAttribute("shipping_info");
    String billing_info = (String) request.getSession(false).getAttribute("billing_info");
    Map<String, Item> cart = (Map<String, Item>) request.getSession(false).getAttribute("myCart");
    int total_price=0;
    if(cart!=null&&!cart.isEmpty()) {
        total_price = (Integer) request.getSession().getAttribute("total_price");
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Order Page</title>
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

<div class="container-fluid row">

    <!--user info-->
    <div class="container col-md-6 col-sm-12">
        <form class="form-signup" action="<%=request.getContextPath()%>/checkout" method="post">
            <h5 class="display-5">Please confirm your shipping and billing</h5>
            <b>Shipping Address</b>
            <div class="form-wrapper">
                <input type="text" name="address1" placeholder="Address" class="form-control" value="<%=shipping_info%>"
                       required>
            </div>
            <b>Billing Address</b>
            <div class="form-wrapper">
                <input type="text" name="address2" placeholder="Address" class="form-control" value="<%=billing_info%>"
                       required>
            </div>
            <div class="btn-group btn-group-lg">
                <button class="btn btn-lg btn-info" type="submit" id="subbtn">Next </button>
                <a href="<%=request.getContextPath()%>" class="btn btn-danger">Cancel</a>
            </div>
            <br>
        </form>
    </div>

    <!--product list-->
    <div class="container col-md-6 col-sm-12">
        <table class="table table-hover table-responsive">
            <thead>
            <tr>
                <th style="width:50%">Product</th>
                <th style="width:8%">Quantity</th>
                <th style="width:8%">unit price</th>

            </tr>
            </thead>
            <tbody>
            <%
            if (cart != null && !cart.isEmpty()) {
                    for (Map.Entry<String, Item> e : cart.entrySet()) {
                        String title = e.getValue().getTitle();
                        String id = e.getValue().getId();
                        int price = e.getValue().getPrice();
                        int quantity = e.getValue().getQuantity();
            %>
            <tr>
                <td data-th="Product">
                    <div class="row">
                        <div class="col-sm-2 hidden-xs"><img src="img/<%=id%>.png" style="height: 60px"
                                                             class="img-responsive"/></div>
                        <div class="col-sm-10"><h4 class="nomargin"><%=title%>
                        </h4></div>
                    </div>
                </td>
                <td data-th="Quantity"><p><%=quantity%>
                </p></td>
                <td data-th="unit price"><p>$<%=price%>
                </p></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
            <tfoot>
            <tr class="d-block">
                <td class="text-center"><strong>Total $<%=total_price%>
                </strong></td>
            </tr>
            </tfoot>
        </table>

    </div>
</div>
</body>
</html>














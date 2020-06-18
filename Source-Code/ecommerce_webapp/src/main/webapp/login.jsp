<%
    String error_message = (String) request.getSession(false).getAttribute("ERROR_MESSAGE");
    request.removeAttribute("ERROR_MESSAGE");
    String login_message = (String) request.getSession(false).getAttribute("LOGIN_MESSAGE");
    request.removeAttribute("LOGIN_MESSAGE");
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign in page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="login.js"></script>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top">
    <div class="container">
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


<div class="container">

    <div class="row vdivide text-center" style="margin-top: 100px;">
        <!--sign up-->
        <div class=" col-xs-12 col-md-6">
            <form class="form-signup" name="formsignup" data-toggle="validator"
                  action="<%=request.getContextPath()%>/signup" method="post">
                <h3>Registration Form</h3>
                <div class="form-group">
                    <input type="text" name="fname" placeholder="First Name" class="form-control" required>
                    <input type="text" name="lname" placeholder="Last Name" class="form-control" required>
                </div>
                <div class="form-wrapper">
                    <input type="email" name="email" placeholder="Email Address" class="form-control" required>
                </div>
                <div class="form-wrapper">
                    <input type="password" name="password" id="firstpass" placeholder="Password" class="form-control"
                           onchange="verify()" required>
                </div>
                <div class="form-wrapper">
                    <input type="password" name="confirm_password" id="confirmpass" placeholder="Confirm Password"
                           class="form-control" onchange="verify()" required>
                    <span id='displaymatch'></span>
                </div>

                <div class="form-wrapper">
                    <input type="text" id="phoneno" name="phoneno" placeholder="Phone Number" class="form-control"
                           onchange="phoneVerify()" required>
                </div>
                <b>Shipping Address</b>
                <div class="form-wrapper">
                    <input type="text" name="address1" placeholder="Address" class="form-control" required>
                </div>
                <div class="form-group">
                    <input type="text" name="city1" placeholder="City" class="form-control" style="margin-right: 0px;"
                           required>
                    <input type="text" name="province1" placeholder="Province" class="form-control" required>
                    <input type="text" id="zip1" name="zip1" placeholder="Zip" class="form-control"
                           onchange="zipVerifyFirst()" required>
                </div>
                <b>Billing Address</b>
                <div class="form-wrapper">
                    <input type="text" name="address2" placeholder="Address" class="form-control" required>
                </div>
                <div class="form-group">
                    <input type="text" name="city2" placeholder="City" class="form-control" style="margin-right: 0px;"
                           required>
                    <input type="text" name="province2" placeholder="Province" class="form-control" required>
                    <input type="text" id="zip2" name="zip2" placeholder="Zip" class="form-control"
                           onchange="zipVerifySecond()" required>
                </div>
                <button class="btn btn-lg btn-info" type="submit" id="subbtn">Register</button>
                <br>
                <div>
                    <%
                        if (error_message != null) {
                    %>
                    <%=error_message%>
                    <%
                        }
                    %>
                </div>
            </form>
        </div>

        <!--log in-->
        <div class="col-xs-12 col-md-6">
            <form class="form-signin" action="<%=request.getContextPath()%>/login" method="post">
                <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
                <%
                    if (login_message != null) {
                %>
                <div class="alert alert-danger">
                    <p><%=login_message%>
                    </p>
                </div>
                <%
                    }
                %>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email address"
                       required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                       required>
                <!-- <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> Remember me
                    </label>
                </div> -->

                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                <br>
                <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
            </form>
        </div>
    </div>
</div>
</body>
</html>














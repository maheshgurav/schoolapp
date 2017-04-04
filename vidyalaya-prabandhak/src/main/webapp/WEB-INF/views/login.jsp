<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/WEB-INF/views/include.jsp" %>
<html lang="en">
<head>
	<title>School Management System -Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</meta>
    <link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet"/>
	<script type="text/javascript" src="js/login.js"></script>
    
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">School Management System</h1>
            <div class="account-wall">
                <img class="profile-img" src="img/deltabee.png" alt="">
		<form:form  cssClass="form-signin" commandName="user" action="login.htm" method="POST" enctype="multipart/form-data" name="loginFormObject">
                
				<form:input class="form-control" placeholder="Username" name="username" id="username" path="userName" />
                <form:password cssClass="form-control" placeholder="Password" name="password" id="password" path="password" />

                <button class="btn btn-lg btn-default btn-block" type="submit">
                    Sign in</button>
                <label class="checkbox pull-left">
                </label>
				<span class="clearfix"></span>
                </form:form>
            </div>
            <a href="http://www.deltabee.com" class="text-center new-account">Deltabee Technologies Pvt.Ltd.</a>
        </div>
    </div>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: techtalk
  Date: 6/15/14
  Time: 6:32 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Login</title>
</head>
<body>

<div class="jumbotron">
    <h1>Login!</h1>
    <g:if test='${flash.message}'>
        <div class="alert alert-info">${flash.message}</div>
    </g:if>
    <div class="row">
        <form action="${createLink(uri: '/j_spring_security_check')}" method='POST' id='loginForm' class='cssform' autocomplete='off'>
            <div class="control-group input-group-lg col-lg-4">
                <label class="control-label" for="username">Username</label>
                <div class="controls">
                    <input type="text" class="form-control" placeholder="Username.." id="username" name="j_username"/>
                </div>
            </div>
            <div class="control-group input-group-lg col-lg-4">
                <label class="control-label" for="username">password</label>
                <div class="controls">
                    <input type="password" class="form-control" placeholder="Password.." id="password" name="j_password"/>
                </div>
            </div>
            </form>
    </div>
    <br/>
    <p><a class="btn btn-primary btn-lg" role="button" onclick="document.getElementById('loginForm').submit()">Login...</a></p>
</div>

</body>
</html>
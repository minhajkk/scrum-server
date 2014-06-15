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
    <title>Register</title>
</head>
<body>
<div id="create-project" class="content scaffold-create" role="main">
    <h1>Register User...</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${userInstance}">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="list-group-item list-group-item-danger"><strong>Oh snap!</strong></div>
                <div class="list-group">
                    <g:eachError bean="${userInstance}" var="error"> <div class="list-group-item list-group-item-danger"><g:message error="${error}"/></div> </g:eachError>
                </div>
            </div>
        </div>
    </g:hasErrors>
    <g:form action="save" controller="myRegister" method="POST" name="registerForm">
        <!-- Text input-->
        <div class="control-group input-group-lg ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
            <label class="control-label" for="username">Username</label>*
            <div class="controls">
                <input type="text" class="form-control" placeholder="User Name" id="username" name="username" required="" value="${userInstance?.username}">
            </div>
        </div>
        <br />

        <!-- Textarea -->
        <div class="control-group ${hasErrors(bean: userInstance, field: 'password', 'error')}">
            <label class="control-label" for="password">Password</label>*
            <div class="controls">
                <input type="password" class="form-control" placeholder="Password" id="password" name="password" required="" value="${userInstance?.password}">
            </div>
        </div>
        <br/>


        <a class="btn btn-primary btn-lg" role="button" onclick="document.getElementById('registerForm').submit()">Create</a>
    </g:form>
</div>

</body>
</html>
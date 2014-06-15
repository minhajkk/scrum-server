<%@ page import="com.scrum.Project" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
    <div class="jumbotron">
        <h1>${projectInstance.name}</h1>
        <p>${projectInstance.description}</p>
        <g:if test="${projectInstance.tasks}">
            <g:each in="${projectInstance.tasks}" var="task">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">${task.title}</h3>
                    </div>
                    <div class="panel-body">
                        <p class="list-group-item-text">${task.description}</p>
                        <p class="list-group-item-text">${task.priority}</p>
                        <p class="list-group-item-text">${task.status}</p>
                        <p class="list-group-item-text">${task.assignedTo.username}</p>
                        <p class="list-group-item-text">${task.reportedBy.username}</p>
                    </div>
                </div>
            </g:each>
        </g:if>
        <g:else>
            <div class="alert alert-info">No task.</div>
            <p><a class="btn btn-primary btn-lg" role="button">Click here to add</a></p>
        </g:else>

    </div>
</body>
</html>

<%@ page import="static com.epam.lowcost.util.Endpoints.UPDATE_USER" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="com.epam.lowcost.model.User"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.update"/></title>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<div class="row">
    <div class="col-md-3 changeData">
        <form action="<%=UPDATE_USER%>" method="post">
            <input type="hidden" name="fromAdmin" value="admin"/>
            <input type="hidden" name="id" value="${user.id}"/>

            <spring:message code="lang.newEmail"/><br/><input type="email" class="form-control input"
                                                               value="${user.username}"
                                                               name="username"/>
            <spring:message code="lang.newFirstName"/><br/><input type="text" class="form-control input"
                                                                   name="firstName"
                                                                   value="${user.firstName}"/>
            <spring:message code="lang.newLastName"/><br/><input type="text" class="form-control input"
                                                                 name="lastName"
                                                                 value="${user.lastName}"/>
            <spring:message code="lang.newDocument"/><br/><input type="text" class="form-control input"
                                                                  name="documentInfo"
                                                                  value="${user.documentInfo}"/>
            <spring:message code="lang.newDateBirth"/><br/><input required type="date" class="form-control input"
                                                                  name="birthday" value="${user.birthday}"/>
            <spring:message code="lang.password"/><br/><input type="password" class="form-control input"
                                                              name="password" value="${user.password}"/>


            <input type="submit" class="btn btn-outline-primary changeDataBtn" value="OK"/>
        </form>
    </div>
</div>
</body>
</html>
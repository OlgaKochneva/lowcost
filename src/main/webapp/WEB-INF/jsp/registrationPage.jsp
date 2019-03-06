<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><spring:message code="lang.registrationPage"/></title>
    <spring:url value="/resources/static/css/login.css" var="login_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${login_css}" rel="stylesheet">
</head>

<body>

<div class="wrapper">
    <p align="right" class="language"><a href="?lang=en"><img
            src="../../resources/static/img/united_kingdom_round_icon_64.png"/></a>
        <a href="?lang=ru"><img src="../../resources/static/img/russia_round_icon_64.png"/> </a></p>
    <div class="main_block">
        <form:form action="<%=Endpoints.REGISTRATION%>" method="POST" modelAttribute="userForm" class="form-signin">
        <h5><spring:message code="lang.fillForm"/></h5>
        <spring:bind path="username">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.email"/>
            <form:input type="text" path="username" class="form-control input"
                        autofocus="true"></form:input>
            <p class="errors"><form:errors path="username"/></p>
        </div>
        </spring:bind>

        <spring:bind path="password">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.password"/>
            <form:input type="password" path="password" class="form-control input"/>
            <p class="errors"><form:errors path="password"/></p>
        </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.repeatPassword"/>
            <form:input type="password" path="passwordConfirm" class="form-control input"/>
            <div class="errors"><form:errors path="passwordConfirm"/></div>
        </div>
        </spring:bind>

        <spring:bind path="firstName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.firstName"/>
            <form:input type="text" path="firstName" class="form-control input"/>
            <div class="errors"><form:errors path="firstName"/></div>
        </div>
        </spring:bind>

        <spring:bind path="lastName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.lastName"/>
            <form:input type="text" path="lastName" class="form-control input"/>
            <p class="errors"><form:errors path="lastName"/></p>
        </div>
        </spring:bind>

        <spring:bind path="documentInfo">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.document"/>
            <form:input type="text" path="documentInfo" class="form-control input"/>
            <p class="errors"><form:errors path="documentInfo"/></p>
        </div>
        </spring:bind>

        <spring:bind path="birthday">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <spring:message code="lang.birthday"/>
            <form:input type="date" path="birthday" class="form-control input"/>
            <p class="errors"><form:errors path="birthday"/></p>
            </spring:bind>
            <button class="btn btn-outline-primary btnSignUp" type="submit"><spring:message
                    code="lang.submit"/></button>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>
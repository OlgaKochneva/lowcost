<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns:th="http://www.thymeleaf.org">
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 13-Feb-19
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <base href="/">
    <title><spring:message code="lang.login" text="Login"/></title>
    <spring:url value="/resources/static/css/login.css" var="login_css" />
    <link href="/resources/static/css/login.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>
<body>

<div class="wrapper">



    <br/>
    <div class="main_block">
        <p class="greeting"><spring:message code="lang.loginIntroduction"/></p>
        <p class="message">${message}</p>
        <form action="/login" method="post">
            <input type="text" name="username" class="form-control input" placeholder="<spring:message code="lang.login" text="Login" />"/>
            <br>
            <input type="password" name="password" class="form-control input" placeholder="<spring:message code="lang.password" text="password"/>"/>
            <br>
            <input type="submit" value="<spring:message code="lang.logIn" text="Log In"/>" class="btn btn-outline-primary btnAuth"/>

        </form>

        <a href="<%=Endpoints.REGISTRATION%>"><spring:message code="lang.signIn" text="Sign In"/></a>
    </div>

</div>
</body>
</html>

<form action=/logout method="post">
    <input type="submit" value="Sign Out"/>
</form>

<form action="/login" method="post">
    <input type="text" name="username"/>
    <input type="password" name="password" />
    <input type="submit" value="Sign In"/>
</form>
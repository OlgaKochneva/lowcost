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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="/resources/static/css/login.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">



    <br/>
    <div class="main_block">
        <p class="greeting"><spring:message code="lang.loginIntroduction"/></p>
        <form method="POST" action="${contextPath}/login" >


            <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <input name="username" type="text" class="form-control input" placeholder="Username"
                       autofocus="true"/>
                <input name="password" type="password" class="form-control input" placeholder="Password"/>
                <span>${error}</span>
                <button class="btn btn-lg btn-primary btn-block btnAuth" type="submit"><spring:message code="lang.logIn"/></button>

                <a href="${contextPath}/registration"><spring:message code="lang.signIn"/></a>
            </div>
        </form>
    </div>

</div>
</body>
</html>

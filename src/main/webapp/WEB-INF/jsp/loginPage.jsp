<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title><spring:message code="lang.login"/></title>
    <spring:url value="/resources/static/css/login.css" var="login_css" />
    <link href="${login_css}" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
</head>
<body>

<div class="wraper">
    <p align="right" class="language"><a href="?lang=en">Eng</a>|<a href="?lang=ru">Rus</a></p>


    <br/>
    <div class="main_block">
        <p class="greeting"><spring:message code="lang.loginIntroduction"/></p>
        <p class="message">${message}</p>
        <form action="/login" method="post">
            <input type="text" name="username" class="form-control input" placeholder="<spring:message code="lang.login"/>"/>
            <br>
            <input type="password" name="password" class="form-control input" placeholder="<spring:message code="lang.password"/>"/>
            <br>
            <input type="submit" value="<spring:message code="lang.logIn"/>" class="btn btn-outline-primary btnAuth"/>

        </form>

        <a href="<%=Endpoints.ENTRY + Endpoints.REGISTRATION%>"><spring:message code="lang.signIn"/></a>
    </div>

</div>
</body>
</html>

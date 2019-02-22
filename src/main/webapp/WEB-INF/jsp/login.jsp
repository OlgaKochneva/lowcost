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
    <title><spring:message code="lang.loginPageEntry"/></title>
    <p align="right"><a href="?lang=en">Eng</a>|<a href="?lang=ru">Rus</a></p>
</head>
<body>

<h4>${message}</h4><br/><a href="<%=Endpoints.ENTRY + Endpoints.REGISTRATION%>"><spring:message code="lang.signIn"/></a>
<br/>

<h3><spring:message code="lang.loginIntroduction"/></h3>
<form action="<%=Endpoints.ENTRY %>" method="post">
    <input type="email" name="email" placeholder="<spring:message code="lang.login"/>"/>
    <input type="password" name="password" placeholder="<spring:message code="lang.password"/>"/>
    <input type="submit" value="<spring:message code="lang.logIn"/>"/>
</form>


</body>
</html>

<%@ page import="com.epam.lowcost.util.Endpoints" %><%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 13-Feb-19
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.registrationPage"/></title>
    <div align="right"><a href="?lang=en">Eng</a>|<a href="?lang=ru">Rus</a></div>
</head>
<body>
<h5><spring:message code="lang.fillForm"/></h5>

<form action="<%=Endpoints.USER + Endpoints.ENROLL%>" method="post">
    <input type="email" name="email"/> <spring:message code="lang.email"/><br/>
    <input type="password" name="password"/> <spring:message code="lang.password"/><br/>
    <input type="hidden" name="isAdmin" value="false"/>
    <input type="text" name="firstName"/> <spring:message code="lang.firstName"/> <br/>
    <input type="text" name="lastName"/> <spring:message code="lang.lastName"/> <br/>
    <input type="text" name="documentInfo"/> <spring:message code="lang.document"/> <br/>
    <input type="datetime-local" name="birthday"/> <spring:message code="lang.birthday"/> <br/>
    <input type="submit" value="<spring:message code="lang.signIn"/>"/>
</form>


</body>
</html>

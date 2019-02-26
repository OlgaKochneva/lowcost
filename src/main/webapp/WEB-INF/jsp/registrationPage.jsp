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
    <spring:url value="/resources/css/login.css" var="login_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${login_css}" rel="stylesheet">

</head>
<body>
<div class="wraper">
    <p align="right" class="language"><a href="?lang=en">Eng</a>|<a href="?lang=ru">Rus</a></p>
    <div class="main_block">
    <h5><spring:message code="lang.fillForm"/></h5>

    <form action="<%=Endpoints.USER + Endpoints.ENROLL%>" method="post">
        <spring:message code="lang.email"/><input type="email" name="email" class="form-control input"/>
        <spring:message code="lang.password"/><input type="password" name="password" class="form-control input"/>
        <input type="hidden" name="isAdmin"  value="false"/>
        <spring:message code="lang.firstName"/> <input type="text" name="firstName" class="form-control input"/>
        <spring:message code="lang.lastName"/> <input type="text" name="lastName" class="form-control input"/>
        <spring:message code="lang.document"/><input type="text" name="documentInfo" class="form-control input"/>
        <spring:message code="lang.birthday"/><input type="date" name="birthday" class="form-control input"/>
        <input type="submit" value="<spring:message code="lang.signIn"/>" class="btn btn-outline-primary btnSignUp"/>
    </form>
    </div>

</div>

</body>
</html>

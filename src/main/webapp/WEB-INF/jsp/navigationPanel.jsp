<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<header class="languageAndAccaunt">
<div align="right" class="language" >
    <a href="?lang=en">Eng |</a> <a href="?lang=ru">Rus</a>

</div>
    <div align="right" class="language" >
    <a href="<%=Endpoints.USER + Endpoints.SETTINGS%>"><spring:message code="lang.userSettings"/> ${sessionUser.firstName}  |</a>
    <a href="<%=Endpoints.LOG_OUT%>"><spring:message code="lang.logOut"/></a>
    </div>
    <div class="topnav">
        <a class=" navbarLink " href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message code="lang.personalCabinet"/></a>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> |<a href="/users" class="navbarLink"> <spring:message code="lang.users"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.PLANE + Endpoints.ALL %>" class="navbarLink"> <spring:message code="lang.planes" /></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>" class="navbarLink"> <spring:message code="lang.flights"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.AIRPORT + Endpoints.ALL%>" class="navbarLink"> <spring:message code="lang.airports"/></a>|</sec:authorize>

    </div>
</header>



</body>
</html>


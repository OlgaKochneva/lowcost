<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #000540;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: #248baf;
        }

        .topnav a.active {
            background-color: #39af82;
            color: white;
        }
    </style>
</head>
<body>

<div class="topnav">
    <a class="active" href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message code="lang.ticket"/></a>
    <c:if test="${sessionUser.isAdmin()}"> <a href="<%=Endpoints.USER + Endpoints.ALL%>"> <spring:message
            code="lang.userDAO"/></a></c:if>
    <c:if test="${sessionUser.isAdmin()}"> <a href="<%=Endpoints.PLANE + Endpoints.ALL%>"> <spring:message
            code="lang.planeDAO"/></a></c:if>
    <c:if test="${sessionUser.isAdmin()}"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"> <spring:message
            code="lang.flightDAO"/></a></c:if>
    <c:if test="${sessionUser.isAdmin()}"> <a href="<%=Endpoints.TICKETS + Endpoints.ALL%>"> <spring:message
            code="lang.ticketDAO"/></a></c:if>
    <a href="<%=Endpoints.USER + Endpoints.SETTINGS%>"><spring:message
            code="lang.loginPageEntry"/> ${sessionUser.firstName}</a>
    <a href="<%=Endpoints.ENTRY + Endpoints.LOG_OUT%>"><spring:message code="lang.logOut"/></a>
    <div align="right"><a href="?lang=en">Eng</a>|<a href="?lang=ru">Rus</a></div>
</div>

</body>
</html>


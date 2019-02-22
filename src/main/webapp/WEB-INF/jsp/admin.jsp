<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 13-Feb-19
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.adminPage"/></title>
</head>
<body>

<p align="right"><spring:message code="lang.currentUser"/> ${sessionUser.firstName} <a href="/entry/log-out">
    <spring:message code="lang.logOut"/></a><br/></p>


<a href="<%=Endpoints.USER + Endpoints.ALL%>"> <spring:message code="lang.userDAO"/></a><br/>
<a href="<%=Endpoints.PLANE + Endpoints.ALL%>"> <spring:message code="lang.planeDAO"/></a><br/>
<a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"> <spring:message code="lang.flightDAO"/></a><br/>
<a href="<%=Endpoints.TICKETS + Endpoints.ALL%>"> <spring:message code="lang.ticketDAO"/></a><br/>


</body>
</html>

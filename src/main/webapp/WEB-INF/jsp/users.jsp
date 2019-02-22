<%@ page import="com.epam.lowcost.util.Endpoints" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.userDAO"/></title>
</head>
<body>

<spring:message code="lang.adminPage"/>
<h2><spring:message code="lang.usersInDB"/></h2><br/><br/>


<br/><br/>
<a href="<%=Endpoints.USER + Endpoints.ALL%>"><spring:message code="lang.allUsers"/></a><br/><br/>

<c:forEach items="${users}" var="user">
    <c:out value="${user.toString()}"/><br/>
</c:forEach>


<br/>
${user}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findUserById"/></h4>
<form action="<%=Endpoints.USER%>" method="get">
    <input type="number" name="id"/><spring:message code="lang.userID"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewUser"/></h4>
<form action="<%=Endpoints.USER%>" method="post">
    <input type="email" name="email"/> <spring:message code="lang.email"/><br/>
    <input type="password" name="password"/> <spring:message code="lang.password"/><br/>
    <input type="text" name="isAdmin"/> <spring:message code="lang.isAdmin"/><br/>
    <input type="text" name="firstName"/> <spring:message code="lang.firstName"/> <br/>
    <input type="text" name="lastName"/> <spring:message code="lang.lastName"/> <br/>
    <input type="text" name="documentInfo"/> <spring:message code="lang.document"/> <br/>
    <input type="datetime-local" name="birthday"/> <spring:message code="lang.birthday"/> <br/>
    <input type="submit" value="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.updateUser"/></h4>
<form action="<%=Endpoints.USER + Endpoints.UPDATE%>" method="post">
    <input type="text" name="id"/> <spring:message code="lang.userID"/><br/>
    <input type="email" name="email"/> <spring:message code="lang.email"/><br/><br/>
    <input type="password" name="password"/> <spring:message code="lang.password"/><br/>
    <input type="text" name="isAdmin"/> <spring:message code="lang.isAdmin"/><br/>
    <input type="text" name="firstName"/> <spring:message code="lang.firstName"/> <br/>
    <input type="text" name="lastName"/> <spring:message code="lang.lastName"/> <br/>
    <input type="text" name="documentInfo"/> <spring:message code="lang.document"/> <br/>
    <input type="datetime-local" name="birthday"/> <spring:message code="lang.birthday"/> <br/>
    <input type="submit" value="OK"/>
</form>

<h4><spring:message code="lang.deleteUser"/> <br/></h4>
<form action="<%=Endpoints.USER + Endpoints.DELETE%>" method="post">
    <input type="number" name="id"/><spring:message code="lang.userID"/><br/>
    <input type="submit" name="OK"/>
</form>

</body>
</html>

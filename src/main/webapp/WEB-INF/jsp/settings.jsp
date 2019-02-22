<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 18-Feb-19
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.userSettings"/></title>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
</head>
<body>


<spring:message code="lang.changePersonalData"/><br/>

<form action="<%=Endpoints.USER + Endpoints.UPDATE%>" method="post">
    <input type="hidden" name="id" value="${sessionUser.id}"/>
    <input type="email" value="${sessionUser.email}" name="email"/> <spring:message code="lang.newEmail"/> <br/>
    <input type="hidden" name="password" value="${sessionUser.password}"/>
    <input type="hidden" name="isAdmin" value="${sessionUser.admin}"/>

    <input type="text" name="firstName" value="${sessionUser.firstName}"/> <spring:message
        code="lang.newFirstName"/> <br/>
    <input type="text" name="lastName" value="${sessionUser.lastName}"/> <spring:message code="lang.newLastName"/>
    <br/>
    <input type="text" name="documentInfo" value="${sessionUser.documentInfo}"/> <spring:message
        code="lang.newDocument"/> <br/>
    <input type="datetime-local" name="birthday" value="${sessionUser.birthday}"/> <spring:message
        code="lang.newDateBirth"/><br/>
    <input type="hidden" name="userUpdate" value="fromUser"/>
    <input type="submit" value="ОК"/>
</form>


Change password.
<form action="<%=Endpoints.USER + Endpoints.CHANGE_PASSWORD%>" method="post">
    <input required type="password" name="oldPassword"> <spring:message code="lang.oldPassword"/> <br/>
    <input required type="password" name="newPassword"> <spring:message code="lang.newPassword"/><br/>
    <input required type="password" name="newPassword2"> <spring:message code="lang.repeatPassword"/><br/>
    <input type="submit" value="OK">
</form>

</body>
</html>

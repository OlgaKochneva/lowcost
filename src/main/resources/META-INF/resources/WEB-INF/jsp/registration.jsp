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
</head>
<body>
<h3>${message}</h3>

<form action="<%=Endpoints.REGISTRATION%>" method="post">
    <input type="email" name="email"/>
    <input type="password" name="password"/>
    <input type="text" name="firstName"/>
    <input type="text" name="lastName"/>
    <input type="text" name="documentInfo"/>
    <input type="datetime-local" name="birthday"/>
    <input type="submit" value="signIn"/>
</form>

<form action=/logout method="post">
    <input type="submit" value="Sign Out"/>
</form>


</body>
</html>

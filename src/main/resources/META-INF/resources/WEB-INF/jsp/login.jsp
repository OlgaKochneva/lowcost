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
</head>
<body>

<h4>${message}</h4>
<br/>

<form action="/login" method="post">
    <input type="text" name="username"/>
    <input type="password" name="password" />
    <input type="submit" value="Sign In"/>
</form>


</body>
</html>

<%@ page import="com.epam.lowcost.util.EndPoints" %>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <spring:url value="/resources/css/main.css" var="main_css"/>
    <link href="${main_css}" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row">


        <div class="col-md-3 changeDataTitle">
            <spring:message code="lang.changePersonalData"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-3 changeData">
            <form action="<%=Endpoints.USER + Endpoints.UPDATE%>" method="post">
                <input type="hidden" name="id" value="${sessionUser.id}"/>

                <spring:message code="lang.newEmail"/> <br/><input type="email" class="form-control input"
                                                                   value="${sessionUser.email}"
                                                                   name="email"/>
                <input type="hidden" name="isAdmin" value="${sessionUser.admin}"/>
                <spring:message code="lang.newFirstName"/> <br/><input type="text" class="form-control input"
                                                                       name="firstName"
                                                                       value="${sessionUser.firstName}"/>
                <spring:message code="lang.newLastName"/><br/><input type="text" class="form-control input"
                                                                     name="lastName"
                                                                     value="${sessionUser.lastName}"/>
                <spring:message code="lang.newDocument"/> <br/><input type="text" class="form-control input"
                                                                      name="documentInfo"
                                                                      value="${sessionUser.documentInfo}"/>
                <spring:message code="lang.newDateBirth"/><br/><input type="date" class="form-control input"
                                                                      name="birthday" value="${sessionUser.birthday}"/>

                Enter password for confirmation
                <input type="password" name="password" />
                <input type="hidden" name="updateFrom" value="user"/>
                <input type="submit" class="btn btn-outline-primary changeDataBtn" value="ОК"/>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3 changePassTitle">
            <spring:message code="lang.changePassword"/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3 changePass">

            <div class="d-xl-inline-block"><h5>${message}</h5></div>
            <form action="<%=Endpoints.USER + Endpoints.CHANGE_PASSWORD%>" method="post">
                <spring:message code="lang.oldPassword"/> <br/><input required class="form-control input"
                                                                      type="password" name="oldPassword">
                <spring:message code="lang.newPassword"/><br/><input required class="form-control input" type="password"
                                                                     name="newPassword">
                <spring:message code="lang.repeatPassword"/><br/> <input required class="form-control input"
                                                                         type="password" name="newPassword2">
                <input type="submit" class="btn btn-outline-primary changePassBtn" value="OK">
            </form>
        </div>
    </div>
</div>
</body>
</html>

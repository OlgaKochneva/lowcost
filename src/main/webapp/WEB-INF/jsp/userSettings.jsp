<%@ page import="static com.epam.lowcost.util.Endpoints.UPDATE_USER" %>
<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="user" type="com.epam.lowcost.model.User"--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.update"/></title>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<header class="languageAndAccaunt">
    <div align="right" class="language">
        <a href="?lang=en"><img src="../../resources/static/img/united_kingdom_round_icon_64.png"/></a>
        <a href="?lang=ru"><img src="../../resources/static/img/russia_round_icon_64.png"/> </a>

    </div>
    <div align="right" class="language">
        <a href="<%=Endpoints.USER + Endpoints.SETTINGS%>"><spring:message
                code="lang.userSettings"/> ${sessionUser.firstName} |</a>

        <c:if test="${sessionUser == null}"><a href="<%=Endpoints.LOGIN%>">
            <spring:message code="lang.logIn"/>
        </c:if>
        <c:if test="${sessionUser != null}"><a href="<%=Endpoints.LOGOUT%>">
            <spring:message code="lang.logOut"/>
        </c:if>

    </div>
    <div class="topnav">
        <a class=" navbarLink" href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message
                code="lang.personalCabinet"/></a>|
        <a class=" navbarLink " href="/"><spring:message code="lang.buyMoreTickets"/></a>|
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.USERS%>" class="navbarLink activeNav ">
            <spring:message code="lang.users"/></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.PLANE%>" class="navbarLink ">
            <spring:message code="lang.planes"/></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"
                                                          class="navbarLink"> <spring:message code="lang.flights"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.AIRPORT%>" class="navbarLink">
            <spring:message code="lang.airports"/></a>|</sec:authorize>

    </div>

</header>

<div class="container">
    <div class="row">
        <div class="col-md-3 changeData">
            <form action="<%=UPDATE_USER%>" method="post">
                <input type="hidden" name="fromAdmin" value="admin"/>
                <input type="hidden" name="id" value="${user.id}"/>

                <spring:message code="lang.newEmail"/><br/><input type="email" class="form-control input"
                                                                  value="${user.username}"
                                                                  name="username"/>
                <spring:message code="lang.newFirstName"/><br/><input type="text" class="form-control input"
                                                                      name="firstName"
                                                                      value="${user.firstName}"/>
                <spring:message code="lang.newLastName"/><br/><input type="text" class="form-control input"
                                                                     name="lastName"
                                                                     value="${user.lastName}"/>
                <spring:message code="lang.newDocument"/><br/><input type="text" class="form-control input"
                                                                     name="documentInfo"
                                                                     value="${user.documentInfo}"/>
                <spring:message code="lang.newDateBirth"/><br/><input required type="date" class="form-control input"
                                                                      name="birthday" value="${user.birthday}"/>
                <spring:message code="lang.password"/><br/><input type="password" class="form-control input"
                                                                  name="password" value="${user.password}"/>
                Change role<br/>
                <select id="newRole" name="newRole">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role}">${role}</option>
                    </c:forEach>
                </select>
                <br/>

                <input type="submit" class="btn btn-outline-primary changeDataBtn" value="OK"/>
            </form>
        </div>
    </div>

</div>

</body>
</html>
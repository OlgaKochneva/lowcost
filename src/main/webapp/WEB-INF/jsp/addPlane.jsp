<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.updateFlight"/></title>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<jsp:include page="navigationPanel.jsp"/>
<header class="languageAndAccaunt">
    <div align="right" class="language">
        <a href="?lang=en"><img src="../../resources/static/img/united_kingdom_round_icon_64.png" /></a>
        <a href="?lang=ru"><img src="../../resources/static/img/russia_round_icon_64.png"/> </a>

    </div>
    <div align="right" class="language">
        <a href="<%=Endpoints.USER + Endpoints.SETTINGS%>"><spring:message code="lang.userSettings"/> ${sessionUser.firstName} |</a>

        <c:if test="${sessionUser == null}"><a href="<%=Endpoints.LOGIN%>">
            <spring:message code="lang.logIn"/>
        </c:if>
        <c:if test="${sessionUser != null}"><a href="<%=Endpoints.LOGOUT%>">
            <spring:message code="lang.logOut"/>
        </c:if>

    </div>
    <div class="topnav">
        <a class=" navbarLink " href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message code="lang.personalCabinet"/></a>|
        <a class=" navbarLink " href="/"><spring:message code="lang.buyMoreTickets"/></a>|
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.USERS%>" class="navbarLink "> <spring:message code="lang.users"/></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.PLANE%>" class="navbarLink activeNav "> <spring:message code="lang.planes" /></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>" class="navbarLink"> <spring:message code="lang.flights"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.AIRPORT%>" class="navbarLink"> <spring:message code="lang.airports"/></a>|</sec:authorize>

    </div>

</header>


<div class="container">
    <div class="row">

        <div class="col-md-3 mainContentUpdate">
            <h4><spring:message code="lang.addNewPlane"/></h4>
            <form:form action="<%=Endpoints.PLANE + Endpoints.ADD%>" method="post" modelAttribute="planeForm">
                <spring:bind path="model">
                    <spring:message code="lang.model"/>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" required="true" path="model" class="form-control"
                                    bodycontent ="" autofocus="true"></form:input>
                        <form:errors path="model"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="businessPlacesNumber">
                    <spring:message code="lang.numBusiness"/>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" required="true"  path="businessPlacesNumber" class="form-control"
                                    autofocus="true"></form:input>
                        <form:errors path="businessPlacesNumber"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="economPlacesNumber">
                    <spring:message code="lang.numEconom"/>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" required="true"  path="economPlacesNumber" class="form-control"
                                    autofocus="true"></form:input>
                        <form:errors path="economPlacesNumber"></form:errors>
                    </div>
                </spring:bind>
                <input type="submit" class="btn btn-outline-success addPlaneBtn" value="OK"/>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>

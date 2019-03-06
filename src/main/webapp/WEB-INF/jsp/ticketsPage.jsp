<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page </title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
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
        <a class=" navbarLink" href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message code="lang.personalCabinet"/></a>|
        <a class=" navbarLink " href="/"><spring:message code="lang.buyMoreTickets"/></a>|
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.USERS%>" class="navbarLink "> <spring:message code="lang.users"/></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.PLANE%>" class="navbarLink "> <spring:message code="lang.planes" /></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>" class="navbarLink activeNav"> <spring:message code="lang.flights"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.AIRPORT%>" class="navbarLink"> <spring:message code="lang.airports"/></a>|</sec:authorize>

    </div>

</header>
<div class="container">

    <div class="row mainContentAdd">
        <div class="col-md-12 ticketsTable">
            <h4><spring:message code="lang.allTickets"/></h4><br/>
            <p><c:out value="${flight.departureAirport.cityEng}"/> - <c:out value="${flight.arrivalAirport.cityEng}"/>,
                <spring:message code="lang.departureAt"/>  <c:out
                        value="${flight.departureDate.toString().replaceAll( 'T', ' ')}"/>,
                <spring:message code="lang.arriveAt"/>  <c:out
                        value="${flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/>
            </p>


            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.firstName"/></th>
                    <th scope="col"><spring:message code="lang.lastName"/></th>
                    <th scope="col"><spring:message code="lang.document"/></th>
                    <th scope="col"><spring:message code="lang.birthday"/></th>
                    <th scope="col"><spring:message code="lang.placePriority"/></th>
                    <th scope="col"><spring:message code="lang.isBusiness"/></th>
                    <th scope="col"><spring:message code="lang.hasLuggage"/></th>
                    <th scope="col"><spring:message code="lang.price"/></th>
                    <th scope="col"><spring:message code="lang.status"/></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                        <td><c:out value="${ticket.user.firstName}"/></td>
                        <td><c:out value="${ticket.user.lastName}"/></td>
                        <td><c:out value="${ticket.user.documentInfo}"/></td>
                        <td><c:out value="${ticket.user.birthday.toString()}"/></td>
                        <td><c:if test="${ticket.placePriority}"><p align="center"><spring:message code="lang.yes"/></p>
                        </c:if></td>
                        <td><c:if test="${ticket.business}"><p align="center"><spring:message code="lang.yes"/></p>
                        </c:if></td>
                        <td><c:if test="${ticket.hasLuggage}"><p align="center"><spring:message code="lang.yes"/></p>
                        </c:if></td>
                        <td><c:out value="${ticket.price}"/></td>
                        <td><c:if test="${ticket.paid}"><spring:message code="lang.paid"/> </c:if>
                            <c:if test="${!ticket.paid}"><p style="color: #dc3545"><spring:message code="lang.unpaid"/>
                                </c:if> </p></td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

</div>
</body>
</html>

<%@ page
        import="com.epam.lowcost.util.EndPoints" %><%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="lang.personalCabinet"/></title>
    <spring:url value="/resources/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
    <jsp:include page="navigationPanel.jsp"/>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12 GroupSearch">
            <a href="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT + EndPoints.FIRST_PAGE%>"><spring:message
                    code="lang.buyMoreTickets"/></a>
        </div>
    </div>
    <div class="row OneRow">


        <c:forEach items="${currentUserTickets}" var="ticket">
            <div class="col-md-6 OneTicket">
                <div class="backgroundTicket">
                    <spring:message code="lang.departureAirport"/> <c:out
                        value="${ticket.flight.departureAirport.cityEng}"/><br/>
                    <spring:message code="lang.arrivalAirport"/> <c:out
                        value="${ticket.flight.arrivalAirport.cityEng}"/><br/>
                    <spring:message code="lang.departureAt"/> <c:out value="${ticket.flight.departureDate}"/><br/>
                    <spring:message code="lang.arriveAt"/> <c:out value="${ticket.flight.arrivalDate}"/><br/>
                    <spring:message code="lang.price"/> <c:out value="${ticket.price}"/><br/>
                    <div class="buttonGroupCard">

                    </div>
                    <input type="button"
                           onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport.cityEng} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport.cityEng} At ${ticket.flight.arrivalDate}')"
                           value="<spring:message code="lang.details"/>"
                           class="btn btn-outline-primary buttonFloatLeft"/>

                    <form action="<%=EndPoints.TICKETS + EndPoints.CANCEL%>" method="post">
                        <input type="hidden" name="id" value="${ticket.id}"/>
                        <input type="submit" value="<spring:message code="lang.cancel"/>" class="btn btn-outline-info"/>

                    </form>
                </div>
            </div>
        </c:forEach>


    </div>

</div>
</body>
</html>

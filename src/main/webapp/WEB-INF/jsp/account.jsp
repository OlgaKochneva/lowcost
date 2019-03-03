<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.personalCabinet"/><</title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/static/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 GroupSearch">
            <a href="<%=Endpoints.FLIGHTS + Endpoints.FLIGHT%>"><spring:message
                    code="lang.buyMoreTickets"/></a>
        </div>
    </div>
    <div class="row OneRow">


        <c:forEach items="${currentUserTickets}" var="ticket">
            <div class="col-md-6 OneTicket">
                <c:if test="${ticket.paid}">
                <div class="backgroundTicket">

                    <spring:message code="lang.departureAirport"/> <c:out
                        value="${ticket.flight.departureAirport.cityEng}"/><br/>
                    <spring:message code="lang.arrivalAirport"/> <c:out
                        value="${ticket.flight.arrivalAirport.cityEng}"/><br/>
                    <spring:message code="lang.departureAt"/> <c:out value="${ticket.flight.departureDate.toString().replaceAll( 'T', ' ')}"/><br/>
                    <spring:message code="lang.arriveAt"/> <c:out value="${ticket.flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/><br/>
                    <spring:message code="lang.price"/> <c:out value="${ticket.price}"/><br/>
                    <div class="buttonGroupCard">


                    <input type="button"
                           onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport.cityEng} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport.cityEng} At ${ticket.flight.arrivalDate}')"
                           value="<spring:message code="lang.details"/>"
                           class="btn btn-outline-primary buttonFloatLeft"/>

                    <form action="<%=Endpoints.TICKETS + Endpoints.CANCEL%>" method="post">
                        <input type="hidden" name="id" value="${ticket.id}"/>
                        <input type="submit" value="<spring:message code="lang.cancel" />" class="btn btn-outline-danger buttonFloatLeft"/>

                    </form>
                        <br/> <br/>
                    </div>
                </div>
                </c:if>
                <c:if test="${!ticket.paid}">
                    <div class="backgroundTicketUnpaid">

                        <spring:message code="lang.departureAirport"/> <c:out
                            value="${ticket.flight.departureAirport.cityEng}"/><br/>
                        <spring:message code="lang.arrivalAirport"/> <c:out
                            value="${ticket.flight.arrivalAirport.cityEng}"/><br/>
                        <spring:message code="lang.departureAt"/> <c:out value="${ticket.flight.departureDate.toString().replaceAll( 'T', ' ')}"/><br/>
                        <spring:message code="lang.arriveAt"/> <c:out value="${ticket.flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/><br/>
                        <spring:message code="lang.price"/> <c:out value="${ticket.price}"/><br/>
                        <div class="buttonGroupCard">


                            <input type="button"
                                   onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport.cityEng} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport.cityEng} At ${ticket.flight.arrivalDate}')"
                                   value="<spring:message code="lang.details"/>"
                                   class="btn btn-outline-primary buttonFloatLeft"/>

                            <form action="<%=Endpoints.TICKETS + Endpoints.CANCEL%>" method="post">
                                <input type="hidden" name="id" value="${ticket.id}"/>
                                <input type="submit" value="<spring:message code="lang.cancel" />" class="btn btn-outline-danger buttonFloatLeft"/>

                            </form>
                            <c:if test="${!ticket.paid}">
                                <form action="<%=Endpoints.TICKETS+Endpoints.PAY%>" method="post">
                                    <input type="submit" value="<spring:message code="lang.pay"/> " class="btn btn-outline-success btPay "/>
                                </form>
                            </c:if>


                        </div>
                    </div>
                </c:if>

            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
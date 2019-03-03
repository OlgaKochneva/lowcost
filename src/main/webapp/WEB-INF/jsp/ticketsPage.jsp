<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page </title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/static/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<div class="container">
<div class="row mainContentAdd">

    <h4><spring:message code="lang.allTickets"/><br/></h4>
    <c:out value="${ticket.flight.departureAirport.cityEng}"/>
    <c:out value="${ticket.flight.arrivalAirport.cityEng}"/>
    <c:out value="${ticket.flight.departureDate.toString().replaceAll( 'T', ' ')}"/>
    <c:out value="${ticket.flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/>

    <div class="col-md-12 ticketsTable">


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
                <th scope="col"><spring:message code="lang.price"/> </th>
            </tr>

            </thead>
            <tbody>
            <c:forEach items="${tickets}" var="ticket">
                <tr>
                    <td><c:out value="${ticket.user.firstName}"/></td>
                    <td><c:out value="${ticket.user.lastName}"/></td>
                    <td><c:out value="${ticket.user.documentInfo}"/></td>
                    <td><c:out value="${ticket.user.birthday.toString().replaceAll( 'T', ' ')}"/></td>
                    <td><c:out value="${ticket.placePriority}"/></td>
                    <td><c:out value="${ticket.business}"/></td>
                    <td><c:out value="${ticket.hasLuggage}"/></td>
                    <td><c:out value="${ticket.price}"/></td>
                    <%--<td>--%>
                        <%--<c:if test="${sessionUser.isAdmin()}">--%>
                            <%--<form action="<%=EndPoints.TICKETS%>" method="get">--%>
                                <%--<input type="hidden" name="id" value="${ticket.id}"/>--%>
                                <%--<input type="submit" value="<spring:message code="lang.update"/>" class="btn btn-outline-primary updateTicket"/>--%>
                            <%--</form>--%>
                            <%--<form action="<%=EndPoints.FLIGHTS + EndPoints.DELETE%>" method="post">--%>
                                <%--<input type="hidden" name="id" value="${ticket.id}"/>--%>
                                <%--<input type="submit" value="<spring:message code="lang.deleteTicket"/>" class="btn btn-outline-danger deleteTicket"/>--%>
                            <%--</form>--%>



                        <%--</c:if>--%>

                    <%--</td>--%>


                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>

</div>
</body>
</html>

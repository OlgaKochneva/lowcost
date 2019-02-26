<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page </title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<div class="container">
<div class="row">
    <div class="col-md-12 ticketsTable">


        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"><spring:message code="lang.firstName"/></th>
                <th scope="col"><spring:message code="lang.email"/></th>
                <th scope="col"><spring:message code="lang.departureAirport"/></th>
                <th scope="col"><spring:message code="lang.arrivalAirport"/></th>
                <th scope="col"><spring:message code="lang.departureAt"/></th>
                <th scope="col"><spring:message code="lang.arriveAt"/></th>
                <th scope="col"><spring:message code="lang.price"/></th>
                <th></th>
            </tr>

            </thead>
            <tbody>
            <c:forEach items="${tickets}" var="ticket">
                <tr>
                    <td><c:out value="${ticket.user.firstName}"/></td>
                    <td><c:out value="${ticket.user.email}"/></td>
                    <td><c:out value="${ticket.flight.departureAirport.cityEng}"/></td>
                    <td><c:out value="${ticket.flight.arrivalAirport.cityEng}"/></td>
                    <td><c:out value="${ticket.flight.departureDate}"/></td>
                    <td><c:out value="${ticket.flight.arrivalDate}"/></td>
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

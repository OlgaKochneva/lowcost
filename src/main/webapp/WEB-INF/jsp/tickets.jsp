<%@ page import="com.epam.lowcost.util.Endpoints" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page </title>
    <jsp:include page="navigationPanel.jsp"/>
</head>
<body>
<spring:message code="lang.adminPage"/>
<h2><spring:message code="lang.tickets"/></h2><br/><br/>

<a href="<%=Endpoints.TICKETS + Endpoints.ALL%>"><spring:message code="lang.allTickets"/></a><br/><br/>


<c:forEach items="${tickets}" var="ticket">
    <c:out value="${ticket.toString()}"/><br/>
</c:forEach>


<br/>
${ticket}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findTicketById"/></h4>
<form action="<%=Endpoints.TICKETS%>" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewTicket"/></h4>
<form action="<%=Endpoints.TICKETS%>" method="post">
    <input type="number" name="userId"/> <spring:message code="lang.userID"/><br/>
    <input type="number" name="flightId"/> <spring:message code="lang.flightId"/><br/>
    <input type="text" name="hasLuggage"/> <spring:message code="lang.hasLuggage"/><br/>
    <input type="text" name="placePriority"/> <spring:message code="lang.placePriority"/> <br/>
    <input type="text" name="isBusiness"/> <spring:message code="lang.isBusiness"/> <br/>
    <input type="submit" value="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.updateTicket"/></h4>
<form action="<%=Endpoints.TICKETS + Endpoints.UPDATE%>" method="post">
    <input type="number" name="ticketId"/> <spring:message code="lang.ticketId"/><br/>
    <input type="text" name="hasLuggage"/> <spring:message code="lang.hasLuggage"/><br/>
    <input type="text" name="placePriority"/> <spring:message code="lang.placePriority"/> <br/>
    <input type="text" name="isBusiness"/> <spring:message code="lang.isBusiness"/> <br/>
    <input type="submit" value="OK"/>
</form>

<h4><spring:message code="lang.deleteTicket"/></h4>
<form action="<%=Endpoints.TICKETS + Endpoints.DELETE%>" method="post">
    <input type="number" name="id"/><spring:message code="lang.ticketId"/><br/>
    <input type="submit" name="OK"/>
</form>

</body>
</html>

<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 19.02.2019
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.update"/></title>
    <spring:url value="/resources/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<jsp:include page="navigationPanel.jsp"/>

<div class="container">
    <div class="row">

        <div class="col-md-3 mainContentUpdate">
            <h4><spring:message code="lang.update"/></h4>


            <form action="<%=Endpoints.FLIGHTS + Endpoints.UPDATE%>" method="post">
                <input type="hidden" name="id" value="${flight.id}"/>
                <spring:message code="lang.planeId"/><br/>
                <input type="text" required name="planeId" class="form-control input" value="${flight.plane.id}"/>
                <spring:message code="lang.departureAt"/><br/><input type="datetime-local" required name="departureDate" class="form-control input" value="${flight.departureDate}"/>
                <spring:message code="lang.arriveAt"/>. <br/> <input type="datetime-local" required name="arrivalDate" class="form-control input" value="${flight.arrivalDate}"/>
                <spring:message code="lang.departureAirport"/> <br/><input type="text" required list="airport" class="form-control input" name="departureAirport" value="${flight.departureAirport.code}"/>
                <spring:message code="lang.arrivalAirport"/> <br/><input type="text" class="form-control input" required list="airport" name="arrivalAirport" value="${flight.arrivalAirport.code}"/>
                <spring:message code="lang.placePriorityPrice"/><br/><input type="text" class="form-control input" required name="placePriorityPrice" value="${flight.placePriorityPrice}"/>
                <spring:message code="lang.businessPrice"/><br/><input type="text" class="form-control input" required name="luggagePrice"value="${flight.businessPrice}"/>
                <spring:message code="lang.luggagePrice"/><br/><input type="text" class="form-control input" required name="businessPrice" value="${flight.luggagePrice}"/>
                <spring:message code="lang.price"/>.<br/><input type="text" class="form-control input" required name="initialPrice" value="${flight.initialPrice}"/>
                <input type="submit" value="<spring:message code="lang.update"/>"
                       class="btn btn-outline-success updateBtn"/>
            </form>
        </div>

    </div>
</div>
<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

<%--<datalist id="planes">--%>

<%--</datalist>--%>
</body>
</html>

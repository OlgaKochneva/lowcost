<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 15.02.2019
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.buy"></spring:message> </title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/static/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">

</head>

<script>
    function updatePrice() {
        var price, isBusiness, hasLuggage, placePriority;
        price = parseInt(${flight.initialPrice});
        isBusiness = document.getElementById("business");
        hasLuggage = document.getElementById("hasLuggage");
        placePriority = document.getElementById("placePriority");
        if (isBusiness.checked == true) {
            price += parseInt(${flight.businessPrice})
        }
        if (hasLuggage.checked == true) {
            price += parseInt(${flight.luggagePrice});
        }
        if (placePriority.checked == true) {
            price += parseInt(${flight.placePriorityPrice});
        }
        document.getElementById("price").value = price.toString()
    }
</script>

<body>
<div class="container">
    <div class="row ">
        <div class="col-md-12 mainContentBuy">
            <form:form action="<%=Endpoints.TICKETS + Endpoints.ADD%>" modelAttribute="ticket" method="post">
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.firstName"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="firstName">${sessionUser.firstName}</output>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <strong> <spring:message code="lang.lastName"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="lastName">${sessionUser.lastName}</output>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.document"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="Passport">${sessionUser.documentInfo}</output>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.birthday"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="Birthdate">${sessionUser.birthday}</output>

                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.departureAirport"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="from">${flight.departureAirport.cityEng}</output>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.arrivalAirport"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="arrivalAirport">${flight.arrivalAirport.cityEng}</output>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.departureAt"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <output name="departureDate">${flight.departureDate.toString().replaceAll('T',' ')}</output>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.arriveAt"/>:</strong>
                    </div>
                    <div class="col-md-3">
                        <output name="arrivalDate">${flight.arrivalDate.toString().replaceAll('T',' ')}</output>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.isBusiness"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <input type="checkbox" id="business" value="true" name="business"
                               onclick="updatePrice()"/>
                        + <c:out value="${flight.businessPrice}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.hasLuggage"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <input type="checkbox" id="hasLuggage" name="hasLuggage" value="true"
                               onclick="updatePrice()"/>+ <c:out
                            value="${flight.luggagePrice}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.placePriority"/>:</strong>
                    </div>
                    <div class="col-md-2">
                        <input type="checkbox" id="placePriority" name="placePriority"
                               value="true"
                               onclick="updatePrice()"/> + <c:out
                            value="${flight.placePriorityPrice}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <strong><spring:message code="lang.total"/></strong>
                    </div>
                    <div class="col-md-2">
                        <output id="price">${flight.initialPrice}</output>
                    </div>
                </div>

                <input type="hidden" name="flight" value="${flight.id}"/>

                </br> <input type="submit" value="<spring:message code="lang.book"/>" class="buyButtonBuyPage btn btn-outline-success"/>
            </form:form>
            <form action="<%=Endpoints.FLIGHTS + Endpoints.FLIGHT%>" method="get">
                <input type="submit" value="<spring:message code="lang.cancel"/>" class="cancellButtonBuyPage2 btn btn-outline-danger"/>
            </form>
        </div>
    </div>
</div>







</body>
</html>


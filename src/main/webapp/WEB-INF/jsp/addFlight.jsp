<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.epam.lowcost.util.Endpoints" %><%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 20.02.2019
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.addNewFlight"/></title>

    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<jsp:include page="navigationPanel.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-3 mainContentAdd">
            <h4><spring:message code="lang.addNewFlight"/><br/></h4>
            <form:form method="post" action="<%=Endpoints.FLIGHTS + Endpoints.ADD%>" modelAttribute="flight" >

                <spring:message code="lang.price"/><br/><input type="text" class="form-control input" required name="initialPrice"/>
                <spring:message code="lang.planeId"/><br/><input type="text"  class="form-control input" required name="planeId"/>
                <spring:bind path="departureDate">
                    <div>
                        <spring:message code="lang.departureDateFrom"/><br/>
                        <form:errors path="departureDate" cssStyle="color: #dc3545"/>
                        <form:input type="datetime-local" path="departureDate" class="form-control input"/>

                    </div>
                </spring:bind>
                <spring:bind path="arrivalDate">
                    <div>
                        <spring:message code="lang.arriveAt"/><br/>
                        <form:errors path="arrivalDate" cssStyle="color: #dc3545"/>
                        <form:input type="datetime-local" path="arrivalDate" class="form-control input"/>

                    </div>
                </spring:bind>
                <%--<spring:message code="lang.departureDateFrom"/><br/><input type="datetime-local"  class="form-control input" required name="departureDate"/>--%>
                <%--<spring:message code="lang.arriveAt"/><br/><input type="datetime-local"  class="form-control input" required name="arrivalDate"/>--%>
                <spring:message code="lang.departureAirport"/><br/><input type="text"  class="form-control input" required list="airport" name="departureAirport"/>
                <spring:message code="lang.arrivalAirport"/><br/><input type="text"  class="form-control input" required list="airport" name="arrivalAirport"/>
                <spring:message code="lang.placePriorityPrice"/>.<br/><input type="text"  class="form-control input" required name="placePriorityPrice"/>
                <spring:message code="lang.businessPrice"/><br/><input type="text"  class="form-control input" required name="businessPrice"/>
                <spring:message code="lang.luggagePrice"/><br/><input type="text"  class="form-control input" required name="luggagePrice"/>

                <input type="submit" class="btn btn-outline-success addFlightBtn" value="OK"/>
            </form:form>
        </div>
    </div>
</div>
<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

</body>
</html>

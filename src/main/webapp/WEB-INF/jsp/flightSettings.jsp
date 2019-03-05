<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <%--<title><spring:message code="lang.update"/></title>--%>
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

        <div class="col-md-3 mainContentUpdate">
            <%--<h4><spring:message code="lang.update"/></h4>--%>

            <form:form method="post" action="<%=Endpoints.FLIGHTS + Endpoints.UPDATE%>" modelAttribute="flight">
                <spring:bind path="id">
                    <form:errors path="id" cssStyle="color: #dc3545"/>
                    <form:input type="hidden" path="id" />
                </spring:bind>
                <spring:bind path="initialPrice">
                    <div>
                        <spring:message code="lang.price"/><br/>
                        <form:input type="text" class="form-control input" path="initialPrice"
                                    value="${flight.initialPrice}"/>
                    </div>
                </spring:bind>
                <spring:bind path="plane">
                    <div>
                        <spring:message code="lang.planeId"/><br/>
                        <form:input type="text" class="form-control input" path="plane"/>
                    </div>
                </spring:bind>
                <spring:bind path="departureDate">
                    <div>
                        <spring:message code="lang.departureDateFrom"/><br/>
                        <form:input type="datetime-local" path="departureDate" class="form-control input"/>
                        <form:errors path="departureDate" cssStyle="color: #dc3545"/>

                    </div>
                </spring:bind>
                <spring:bind path="arrivalDate">
                    <div>
                        <spring:message code="lang.arriveAt"/><br/>
                        <form:input type="datetime-local" path="arrivalDate" class="form-control input"/>
                        <form:errors path="arrivalDate" cssStyle="color: #dc3545"/>

                    </div>
                </spring:bind>
                <spring:bind path="departureAirport">
                    <div>
                        <spring:message code="lang.departureAirport"/><br/>
                        <form:input type="text" class="form-control input" list="airport" path="departureAirport"/>
                    </div>
                </spring:bind>
                <spring:bind path="arrivalAirport">
                    <div>
                        <spring:message code="lang.arrivalAirport"/><br/>
                        <form:input type="text" path="arrivalAirport" list="airport" class="form-control input"/>
                        <form:errors path="arrivalAirport" cssStyle="color: #dc3545"/>
                    </div>
                </spring:bind>
                <spring:bind path="placePriorityPrice">
                    <div>
                        <spring:message code="lang.placePriorityPrice"/>.<br/>
                        <form:input type="text" class="form-control input" path="placePriorityPrice"/>
                    </div>
                </spring:bind>
                <spring:bind path="businessPrice">
                    <div>
                        <spring:message code="lang.businessPrice"/>.<br/>
                        <form:input type="text" class="form-control input" path="businessPrice"/>
                    </div>
                </spring:bind>
                <spring:bind path="luggagePrice">
                    <div>
                        <spring:message code="lang.luggagePrice"/>.<br/>
                        <form:input type="text" class="form-control input" path="luggagePrice"/>
                    </div>
                </spring:bind>
                <input type="submit" value="<spring:message code="lang.update"/> "
                       class="btn btn-outline-success updateBtn"/>

            </form:form>


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

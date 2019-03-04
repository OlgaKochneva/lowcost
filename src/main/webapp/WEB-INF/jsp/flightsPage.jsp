<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 14.02.2019
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.findFlight"/></title>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row BlockBachground">
        <div class="col-md-8">

            <p class="labelSeatchFlight"><spring:message code="lang.findFlight"/></p>

            <form action="<%=Endpoints.FLIGHTS + Endpoints.SEARCH + Endpoints.ADMIN%>" method="get">
                <div class="leftBlockSerch">
                    <label for="inpSerc"><spring:message code="lang.departureDateFrom"/>:</label>
                    <input type="date" id="inpSerc" required name="departureDateFrom" class="form-control searchInput"/>
                    <br/>
                    <label for="inpSerc2"><spring:message code="lang.departureDateTo"/>: </label>
                    <input type="date" id="inpSerc2" name="departureDateTo" class="form-control searchInput"/><br/>
                </div>
                <div class="leftBlockSerchRight">
                    <label for="inpSerc3"><spring:message code="lang.departureAirport"/>: </label>
                    <input type="text" id="inpSerc3" required list="airport" name="departureAirport"
                           class="form-control searchInput"/> <br/>
                    <label for="inpSerc4"><spring:message code="lang.arrivalAirport"/>: </label>
                    <input type="text" id="inpSerc4" required list="airport" name="arrivalAirport"
                           class="form-control searchInput"/> <br/>
                </div>

        </div>
        <div class="col-md-4">
            <input type="submit" value="<spring:message code="lang.search"/>" class="btn btn-outline-warning btnSeach"/>

            </form>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <form action="<%=Endpoints.FLIGHTS + Endpoints.ADD%>" method="get">
                    <input type="submit" value="<spring:message code="lang.addNewFlight"/>"
                           class="flightAddBtn btn btn-outline-success"/>
                </form>
            </sec:authorize>
        </div>
    </div>
</div>
<div class="container mainSerchPage">
    <div class="row">
        <div class="col-md-10">

        </div>
        <div class="col-md-2 numOfUsers">
            <%--<form></form>--%>
            <%--<form action="<%=Endpoints.FLIGHTS + Endpoints.PAGE%>" method="get">--%>
            <%--<input type="hidden" name="number" value="3"/>--%>

            <%--<input type="hidden" name="fromPage" value="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"/>--%>
            <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="3"/>--%>
            <%--</form>--%>
            <%--<form action="<%=Endpoints.FLIGHTS + Endpoints.PAGE%>" method="get">--%>
            <%--<input type="hidden" name="number" value="5"/>--%>
            <%--<input type="hidden" name="fromPage" value="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"/>--%>
            <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="5"/>--%>
            <%--</form>--%>
            <%--<form action="<%=Endpoints.FLIGHTS + Endpoints.PAGE%>" method="get">--%>
            <%--<input type="hidden" name="number" value="10"/>--%>
            <%--<input type="hidden" name="fromPage" value="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"/>--%>
            <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="10"/>--%>
            <%--</form>--%>
            <%--<form action="<%=Endpoints.FLIGHTS + Endpoints.PAGE%>" method="get">--%>
            <%--<input type="hidden" name="number" value="20"/>--%>
            <%--<input type="hidden" name="fromPage" value="<%=Endpoints.FLIGHTS + Endpoints.ALL%>"/>--%>
            <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="20"/>--%>
            <%--</form>--%>
            <%--</div>--%>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.departureAirport"/></th>
                    <th scope="col"><spring:message code="lang.arrivalAirport"/></th>
                    <th scope="col"><spring:message code="lang.departureAt"/></th>
                    <th scope="col"><spring:message code="lang.arriveAt"/></th>
                    <th scope="col"><spring:message code="lang.price"/></th>
                    <th></th>
                    <th><div>
                        <form></form>
                        <spring:message code="lang.showUsersBy"/> <a
                            href="?size=1&searchTerm=${searchTerm}&searchString=${searchString}">1 | </a><a
                            href="?size=5&searchTerm=${searchTerm}&searchString=${searchString}"> 5</a>
                    </div></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${flights.getContent()}" var="flight">

                    <tr>


                        <td><c:out value="${flight.departureAirport.cityEng}"/></td>
                        <td><c:out value="${flight.arrivalAirport.cityEng}"/></td>
                        <td><c:out value="${flight.departureDate.toString().replaceAll( 'T', ' ')}"/></td>
                        <td><c:out value="${flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/></td>
                        <td><c:out value="${flight.initialPrice}"/></td>
                        <td><c:if test="${flight.departureDate gt currentTime}">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">

                                <p style="color:#28a745" class="inProgress" ><spring:message code="lang.uncomplete"/></p>
                                <form action="<%=Endpoints.FLIGHTS%>/${flight.id}" method="get">
                                    <input type="submit" value="<spring:message code="lang.update"/>"
                                           class="btn btn-outline-primary modifyFlight"/>
                                </form>
                            </sec:authorize>
                        </c:if>
                            <c:if test="${flight.departureDate lt currentTime}">

                                <p style="color:#28a745" class="inProgress"><spring:message code="lang.complete"/></p>
                                <form action="<%=Endpoints.FLIGHTS%>/${flight.id}" method="get">
                                    <input type="submit" value="<spring:message code="lang.repeat"/>"
                                           class="btn btn-outline-primary modifyFlight"/>
                                </form>
                            </c:if>



                        </td>



                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <form action="<%=Endpoints.FLIGHTS+ Endpoints.TICKETS%>/${flight.id}" method="get">
                                    <input type="submit" value="<spring:message code="lang.tickets"/>"
                                           class="btn btn-outline-primary updateFlight"/>
                                </form>
                            </sec:authorize>


                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <form action="<%=Endpoints.FLIGHTS + Endpoints.DELETE%>" method="post">
                                    <input type="hidden" name="id" value="${flight.id}"/>
                                    <input type="submit" value="<spring:message code="lang.delete"/>"
                                           class="btn btn-outline-danger deleteFlight"/>
                                </form>
                            </sec:authorize>


                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
            <div>
                <c:if test="${flights.hasPrevious()}"> <a
                        href="?page=${flights.number-1}&size=${flights.size}"><spring:message
                        code="lang.previous"/></a></c:if>
                <c:forEach var="page" begin="1" end="${flights.totalPages}">
                    <a href="?page=${page-1}&size=${flights.size}">${page}</a>
                </c:forEach>
                <c:if test="${flights.hasNext()}"> <a href="?page=${flights.number+1}&size=${flights.size}"><spring:message
                        code="lang.next"/></a></c:if>
            </div>
        </div>
    </div>
</div>


<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>
<br/>
<br/>

</body>
</html>

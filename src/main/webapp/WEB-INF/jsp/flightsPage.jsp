<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <spring:url value="/resources/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row BlockBachground">
        <div class="col-md-8">

            <p class="labelSeatchFlight"><spring:message code="lang.findFlight"/></p>

            <form action="<%=Endpoints.FLIGHTS + Endpoints.SEARCH%>" method="get">
                <div class="leftBlockSerch">
                    <label for="inpSerc"><spring:message code="lang.departureDateFrom"/>:</label>
                    <input type="date" id="inpSerc" required name="departureDateFrom" class="form-control searchInput"/> <br/>
                    <label for="inpSerc2"><spring:message code="lang.departureDateTo"/>: </label>
                    <input type="date" id="inpSerc2" name="departureDateTo" class="form-control searchInput"/><br/>
                </div>
                <div class="leftBlockSerchRight">
                    <label for="inpSerc3"><spring:message code="lang.departureAirport"/>: </label>
                    <input type="text" id="inpSerc3" required list="airport" name="departureAirport" class="form-control searchInput"/> <br/>
                    <label for="inpSerc4"><spring:message code="lang.arrivalAirport"/>: </label>
                    <input type="text" id="inpSerc4" required list="airport" name="arrivalAirport" class="form-control searchInput"/>  <br/>
                    <input type="text" hidden name="adminPage" value="true"/>
                </div>


        <div class="col-md-4">
            <input type="submit" value="<spring:message code="lang.search"/>" class="btn btn-outline-warning btnSeach"/>
        </div>
            </form>
            <c:if test="${sessionUser.isAdmin()}">
                <form action="<%=Endpoints.FLIGHTS + Endpoints.ADD%>" method="get">
                    <input type="submit" value="<spring:message code="lang.addNewFlight"/>" class="flightAddBtn btn btn-outline-warning"/>
                </form>
            </c:if>
        </div>
    </div>
</div>

<div class="container mainSerchPage">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.departureAirport"/></th>
                    <th scope="col"><spring:message code="lang.arrivalAirport"/></th>
                    <th scope="col"><spring:message code="lang.departureDateFrom"/></th>
                    <th scope="col"> <spring:message code="lang.arriveAt"/></th>
                    <th scope="col">  <spring:message code="lang.price"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${flights}" var="flight">

                    <tr>


                        <td><c:out value="${flight.departureAirport.cityEng}"/></td>
                        <td> <c:out value="${flight.arrivalAirport.cityEng}"/></td>
                        <td>   <c:out value="${flight.departureDate}"/></td>
                        <td>  <c:out value="${flight.arrivalDate}"/></td>
                        <td>  <c:out value="${flight.initialPrice}"/></td>


                        <td>
                            <c:if test="${sessionUser.isAdmin()}">
                                <form action="<%=Endpoints.FLIGHTS%>" method="get">
                                    <input type="hidden" name="id" value="${flight.id}"/>
                                    <input type="submit" value="<spring:message code="lang.update"/>" class="btn btn-outline-primary updateFlight"/>
                                </form>
                                <form action="<%=Endpoints.FLIGHTS + Endpoints.DELETE%>" method="post">
                                    <input type="hidden" name="id" value="${flight.id}"/>
                                    <input type="submit" value="<spring:message code="lang.delete"/>" class="btn btn-outline-danger deleteFlight"/>
                                </form>



                            </c:if>

                        </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
    </div>
</div>



<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option  hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

<div id="content">

</div>

</body>
</html>

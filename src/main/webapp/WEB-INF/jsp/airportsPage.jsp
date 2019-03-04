<%--
  Created by IntelliJ IDEA.
  User: Anastasia_Berlina
  Date: 2/22/2019
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.airports"/></title>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-3 airportTitle">
            <spring:message code="lang.airports"/>
            <br/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3 findAirportBtn">
        <spring:message code="lang.findAirportByCity"/>
        <form action="<%=Endpoints.AIRPORT%>" method="get">
            <input class="form-control input codeInputAirports" required type="text" name="city"/>
            <input type="submit" class="btn btn-outline-primary okBtnAirports" name="OK"/>
        </form>
        </div>

    </div>
    <div class="row">
        <div class="col-md-9">

        </div>
        <%--<div class="col-md-3 numOfUsers">--%>
            <%--<form></form>--%>
            <%--<form action="<%=Endpoints.AIRPORT + Endpoints.PAGE%>" method="get">--%>
                <%--<input type="hidden" name="number" value="10"/>--%>

                <%--<input type="hidden" name="fromPage" value="<%=Endpoints.AIRPORT + Endpoints.ALL%>"/>--%>
                <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="10"/>--%>
            <%--</form>--%>
            <%--<form action="<%=Endpoints.AIRPORT + Endpoints.PAGE%>" method="get">--%>
                <%--<input type="hidden" name="number" value="50"/>--%>
                <%--<input type="hidden" name="fromPage" value="<%=Endpoints.AIRPORT + Endpoints.ALL%>"/>--%>
                <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="50"/>--%>
            <%--</form>--%>
            <%--<form action="<%=Endpoints.AIRPORT + Endpoints.PAGE%>" method="get">--%>
                <%--<input type="hidden" name="number" value="100"/>--%>
                <%--<input type="hidden" name="fromPage" value="<%=Endpoints.AIRPORT + Endpoints.ALL%>"/>--%>
                <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="100"/>--%>
            <%--</form>--%>
            <%--<form action="<%=Endpoints.AIRPORT + Endpoints.PAGE%>" method="get">--%>
                <%--<input type="hidden" name="number" value="200"/>--%>
                <%--<input type="hidden" name="fromPage" value="<%=Endpoints.AIRPORT + Endpoints.ALL%>"/>--%>
                <%--<input type="submit" class="btn btn-link numOfUsersBtn" value="200"/>--%>
            <%--</form>--%>
        <%--</div>--%>
        <div  class="col-md-2 numOfUsersBtn">

            <spring:message code="lang.showUsersBy"/><br/>
            <a href="?size=20&searchTerm=${searchTerm}&searchString=${searchString}">20|</a>
            <a href="?size=50&searchTerm=${searchTerm}&searchString=${searchString}">50|</a>
            <a href="?size=200&searchTerm=${searchTerm}&searchString=${searchString}">200</a>

        </div>
    <div class="row">
        <div class="col-md-12  mainContentAirport">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.airportCode"/></th>
                    <th scope="col"><spring:message code="lang.cityEng"/></th>
                    <th scope="col"><spring:message code="lang.cityRus"/></th>
                    <th scope="col"><spring:message code="lang.nameEng"/></th>
                    <th scope="col"><spring:message code="lang.nameRus"/></th>
                    <th scope="col"><spring:message code="lang.countryEng"/></th>
                    <th scope="col"><spring:message code="lang.countryRus"/></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${airports.getContent()}" var="airport">
                    <tr>
                        <td><c:out value="${airport.code}"/></td>
                        <td><c:out value="${airport.cityEng}"/></td>
                        <td><c:out value="${airport.cityRus}"/></td>
                        <td><c:out value="${airport.nameEng}"/></td>
                        <td><c:out value="${airport.nameRus}"/></td>
                        <td><c:out value="${airport.countryEng}"/></td>
                        <td><c:out value="${airport.countryRus}"/></td>


                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <div>
                <c:if test="${airports.hasPrevious()}"> <a
                        href="?page=${airports.number-1}&size=${airports.size}"><spring:message
                        code="lang.previous"/></a></c:if>
                <c:forEach var="page" begin="1" end="${airports.totalPages}">
                    <a href="?page=${page-1}&size=${airports.size}">${page}</a>
                </c:forEach>
                <c:if test="${airports.hasNext()}"> <a href="?page=${airports.number+1}&size=${airports.size}"><spring:message
                        code="lang.next"/></a></c:if>
            </div>
    </div>


    <br/>
    ${airport}<br/> <h4>${message}</h4>

</div>
</div>
</body>
</html>

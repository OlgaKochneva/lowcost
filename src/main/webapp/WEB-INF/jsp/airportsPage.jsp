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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<header class="languageAndAccaunt">
    <div align="right" class="language">
        <a href="?lang=en"><img src="../../resources/static/img/united_kingdom_round_icon_64.png" /></a>
        <a href="?lang=ru"><img src="../../resources/static/img/russia_round_icon_64.png"/> </a>

    </div>
    <div align="right" class="language">
        <a href="<%=Endpoints.USER + Endpoints.SETTINGS%>"><spring:message code="lang.userSettings"/> ${sessionUser.firstName} |</a>

        <c:if test="${sessionUser == null}"><a href="<%=Endpoints.LOGIN%>">
            <spring:message code="lang.logIn"/>
        </c:if>
        <c:if test="${sessionUser != null}"><a href="<%=Endpoints.LOGOUT%>">
            <spring:message code="lang.logOut"/>
        </c:if>

    </div>
    <div class="topnav">
        <a class=" navbarLink " href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message code="lang.personalCabinet"/></a>|
        <a class=" navbarLink " href="/"><spring:message code="lang.buyMoreTickets"/></a>|
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.USERS%>" class="navbarLink "> <spring:message code="lang.users"/></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.PLANE%>" class="navbarLink "> <spring:message code="lang.planes" /></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>" class="navbarLink"> <spring:message code="lang.flights"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.AIRPORT%>" class="navbarLink activeNav"> <spring:message code="lang.airports"/></a>|</sec:authorize>

    </div>

</header>


<div class="container">
    <%--<div class="row">--%>
        <%--<div class="col-md-3 airportTitle">--%>
            <%--<spring:message code="lang.airports"/>--%>
            <%--<br/>--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="row">
        <div class="col-md-3 findAirportBtn">
        <spring:message code="lang.findAirportByCity"/>
        <form action="<%=Endpoints.AIRPORT%>" method="get">
            <input class="form-control input codeInputAirports" list="cities" required type="text" name="city"/>
            <input type="submit" class="btn btn-outline-primary okBtnAirports" value="<spring:message code="lang.find"/> "/>
        </form>
        </div>

    </div>
    <div class="row">
        <div class="col-md-10">

        </div>
        <div  class="col-md-2 numOfUsersBtn">
            <spring:message code="lang.showUsersBy"/><br/>
            <a href="?size=20&searchTerm=${searchTerm}&searchString=${searchString}">20</a>|
            <a href="?size=50&searchTerm=${searchTerm}&searchString=${searchString}">50</a>
            |<a href="?size=200&searchTerm=${searchTerm}&searchString=${searchString}">200</a>

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
    </div>


    <br/>
    ${airport}<br/> <h4>${message}</h4>
        <datalist id="cities">
            <c:forEach items="${cities}" var="airport">
                <option  value="${airport.cityEng}"> </option>
                <option value="${airport.cityRus}"> </option>
            </c:forEach>
        </datalist>
</div>
</body>
</html>

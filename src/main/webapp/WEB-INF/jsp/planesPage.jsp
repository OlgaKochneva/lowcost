<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.planes"/></title>
    <spring:url value="/resources/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3 planeTitle">
            <spring:message code="lang.planes"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 addPlaneBtn">

            <form action="<%=Endpoints.PLANE + Endpoints.ADD%>" method="get">
                <input type="submit" class="btn btn-outline-primary addPlaneBtn" value="<spring:message code="lang.addNewPlane"/> "/>
            </form>

        </div>
    </div>
    <div class="row">
    <div class="col-md-10">

    </div>
    <div class="col-md-2 numOfUsers">
        <form></form>
        <form action="<%=Endpoints.PLANE + Endpoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="3"/>

            <input type="hidden" name="fromPage" value="<%=Endpoints.PLANE + Endpoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="3"/>
        </form>
        <form action="<%=Endpoints.PLANE + Endpoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="5"/>
            <input type="hidden" name="fromPage" value="<%=Endpoints.PLANE + Endpoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="5"/>
        </form>
        <form action="<%=Endpoints.PLANE+ Endpoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="10"/>
            <input type="hidden" name="fromPage" value="<%=Endpoints.PLANE + Endpoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="10"/>
        </form>
        <form action="<%=Endpoints.PLANE + Endpoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="20"/>
            <input type="hidden" name="fromPage" value="<%=Endpoints.PLANE + Endpoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="20"/>
        </form>
    </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.model"/></th>
                    <th scope="col"><spring:message code="lang.numBusiness"/></th>
                    <th scope="col"><spring:message code="lang.numEconom"/></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${planes}" var="plane">
                    <tr>

                        <td><c:out value="${plane.model}"/></td>
                        <td><c:out value="${plane.businessPlacesNumber}"/></td>
                        <td><c:out value="${plane.economPlacesNumber}"/></td>


                    <td>
                    <c:if test="${sessionUser.isAdmin()}">
                    <form action="<%=Endpoints.PLANE%>" method="get">
                    <input type="hidden" name="id" value="${plane.id}"/>
                    <input type="submit" value="<spring:message code="lang.update"/>" class="btn btn-outline-primary updatePlaneBtn"/>
                    </form>
                    <form action="<%=Endpoints.PLANE + Endpoints.DELETE%>" method="post">
                    <input type="hidden" name="id" value="${plane.id}"/>
                    <input type="submit" value="<spring:message code="lang.delete"/>" class="btn btn-outline-danger deletePlaneBtn"/>
                    </form>



                    </c:if>

                    </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <form action="<%=Endpoints.PLANE + Endpoints.ALL%>/${pageId-1}">
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.previous"/>">
            </form>
            <c:forEach var="page" begin="1" end="${pagesNum}">
                <form action="<%=Endpoints.PLANE + Endpoints.ALL%>/${page}">
                    <input type="submit" class="btn btn-link paginationBtn" value="${page}">
                </form>
            </c:forEach>
            <form action="<%=Endpoints.PLANE + Endpoints.ALL%>/${pageId+1}">
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.next"/>">
            </form>

        </div>
    </div>
    <br/>



</div>

</body>
</html>

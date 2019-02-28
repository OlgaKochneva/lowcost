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

            <a href="<%=Endpoints.PLANE + Endpoints.ADD%>" class="btn btn-outline-primary addPlaneBtn"> <spring:message
                    code="lang.addNewPlane"/></a>

        </div>
    </div>

    <h5>${message}</h5>

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
                            <form action="<%=Endpoints.PLANE%>" method="get">
                                <input type="hidden" name="id" value="${plane.id}"/>
                                <input type="submit" value="<spring:message code="lang.update"/>"
                                       class="btn btn-outline-primary updatePlaneBtn"/>
                            </form>
                            <form action="<%=Endpoints.PLANE + Endpoints.DELETE%>" method="post">
                                <input type="hidden" name="id" value="${plane.id}"/>
                                <input type="submit" value="<spring:message code="lang.delete"/>"
                                       class="btn btn-outline-danger deletePlaneBtn"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
    <br/>

</div>

</body>
</html>

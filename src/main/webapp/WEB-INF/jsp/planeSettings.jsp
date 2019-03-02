<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.updateFlight"/></title>
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
            <h4><spring:message code="lang.updatePlane"/></h4>


            <form action="<%=Endpoints.PLANE%>/${plane.id}" method="post">
                <input type="hidden" name="planeId" value="${plane.id}"/>
                <spring:message code="lang.model"/><br/><input type="text" required name="model" class="form-control input" value="${plane.model}"/>
                <spring:message code="lang.numBusiness"/> <br/> <input type="number" required name="businessPlacesNumber" class="form-control input" value="${plane.businessPlacesNumber}"/>
                <spring:message code="lang.numEconom"/> <br/><input type="number" required class="form-control input" name="economPlacesNumber" value="${plane.economPlacesNumber}"/>
                <input type="submit" value="<spring:message code="lang.updatePlane"/>"
                       class="btn btn-outline-success updateBtn"/>
            </form>
        </div>

    </div>
</div>
</body>
</html>

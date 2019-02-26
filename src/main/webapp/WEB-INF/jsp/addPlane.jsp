<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title><spring:message code="lang.updateFlight"/></title>
    <spring:url value="/resources/css/main.css" var="main_css"/>
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
            <h4><spring:message code="lang.addNewPlane"/></h4>


            <form action="<%=Endpoints.PLANE%>" method="post">
                <spring:message code="lang.model"/><br/><input type="text" required name="model" class="form-control input" value="${plane.model}"/>
                <spring:message code="lang.businessPlacesNumber"/>. <br/> <input type="number" required name="businessPlacesNumber" class="form-control input" value="${plane.businessPlacesNumber}"/>
                <spring:message code="lang.economyPlacesNumber"/> <br/><input type="number" required class="form-control input" name="economPlacesNumber" value="${plane.economPlacesNumber}"/>
                <input type="submit" value="<spring:message code="lang.addNewPlane"/>"
                       class="btn btn-outline-success addPlaneBtn"/>
            </form>
        </div>

    </div>
</div>
</body>
</html>

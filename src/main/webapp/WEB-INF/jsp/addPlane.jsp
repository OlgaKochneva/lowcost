<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <h4><spring:message code="lang.addNewPlane"/></h4>
            <form:form action="<%=Endpoints.PLANE + Endpoints.ADD%>" method="post" modelAttribute="planeForm">
                <spring:bind path="model">
                    <spring:message code="lang.model"/>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" required="true" path="model" class="form-control"
                                    bodycontent ="" autofocus="true"></form:input>
                        <form:errors path="model"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="businessPlacesNumber">
                    <spring:message code="lang.numBusiness"/>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" required="true"  path="businessPlacesNumber" class="form-control"
                                    autofocus="true"></form:input>
                        <form:errors path="businessPlacesNumber"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="economPlacesNumber">
                    <spring:message code="lang.numEconom"/>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="number" required="true"  path="economPlacesNumber" class="form-control"
                                    autofocus="true"></form:input>
                        <form:errors path="economPlacesNumber"></form:errors>
                    </div>
                </spring:bind>
                <input type="submit" class="btn btn-outline-success addPlaneBtn" value="OK"/>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>
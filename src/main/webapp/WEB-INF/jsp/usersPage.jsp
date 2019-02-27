<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.users"/></title>
    <spring:url value="/resources/static/css/main.css" var="main_css"/>
    <link href="/resources/static/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
    </head>
<body>


<div class="container">
    <div class="row">
        <div class="col-md-3 usersTitle">
            <spring:message code="lang.users"/>
        </div>
    </div>
    <div class="row">

        <div class="col-md-10">

        </div>
        <div class="col-md-2 numOfUsers">
            <form></form>
            <form action="<%=Endpoints.USER + Endpoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="3"/>
                <input type="hidden" name="fromPage" value="<%=Endpoints.USER + Endpoints.ALL%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="3"/>
            </form>
            <form action="<%=Endpoints.USER + Endpoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="5"/>
                <input type="hidden" name="fromPage" value="<%=Endpoints.USER + Endpoints.ALL%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="5"/>
            </form>
            <form action="<%=Endpoints.USER + Endpoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="10"/>
                <input type="hidden" name="fromPage" value="<%=Endpoints.USER + Endpoints.ALL%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="10"/>
            </form>
            <form action="<%=Endpoints.USER + Endpoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="20"/>
                <input type="hidden" name="fromPage" value="<%=Endpoints.USER + Endpoints.ALL%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="20"/>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.firstName"/></th>
                    <th scope="col"><spring:message code="lang.lastName"/></th>
                    <th scope="col"><spring:message code="lang.email"/></th>
                    <th scope="col"><spring:message code="lang.document"/></th>
                    <th scope="col"><spring:message code="lang.birthday"/></th>

                    <th></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>

                        <td><c:out value="${user.firstName}"/></td>
                        <td><c:out value="${user.lastName}"/></td>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.documentInfo}"/></td>
                        <td><c:out value="${user.birthday}"/></td>


                        <td>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">

                                <form action="<%=Endpoints.USER + Endpoints.DELETE%>" method="post">
                                    <input type="hidden" name="id" value="${user.id}"/>
                                    <input type="submit" value="<spring:message code="lang.deleteUser"/>"
                                           class="btn btn-outline-danger deletePlaneBtn"/>
                                </form>

                            </sec:authorize>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <form action="<%=Endpoints.USER + Endpoints.ALL%>/${pageId-1}">
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.previous"/>">
            </form>
            <c:forEach var="page" begin="1" end="${pagesNum}">
                <form action="<%=Endpoints.USER + Endpoints.ALL%>/${page}">

                    <input type="submit" class="btn btn-link paginationBtn" value="${page}">

                </form>
            </c:forEach>
            <form action="<%=Endpoints.USER + Endpoints.ALL%>/${pageId+1}">
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.next"/>">
            </form>

        </div>
    </div>
</div>
</body>
</html>

<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@ page import="static com.epam.lowcost.util.Endpoints.BLOCK_USER" %>
<%@ page import="static com.epam.lowcost.util.Endpoints.UNBLOCK_USER" %>
<%@ page import="static com.epam.lowcost.util.Endpoints.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="users" type="org.springframework.data.domain.Page"--%>
<%--@elvariable id="sessionUser" type="com.epam.lowcost.model.User"--%>

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
        <a class=" navbarLink" href="<%=Endpoints.TICKETS + Endpoints.SELF%>"><spring:message code="lang.personalCabinet"/></a>|
        <a class=" navbarLink " href="/"><spring:message code="lang.buyMoreTickets"/></a>|
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.USERS%>" class="navbarLink activeNav "> <spring:message code="lang.users"/></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.PLANE%>" class="navbarLink "> <spring:message code="lang.planes" /></a>|</sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.FLIGHTS + Endpoints.ALL%>" class="navbarLink"> <spring:message code="lang.flights"/></a>|</sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')"> <a href="<%=Endpoints.AIRPORT%>" class="navbarLink"> <spring:message code="lang.airports"/></a>|</sec:authorize>

    </div>

</header>


<div class="container">
    <%--<div class="row">--%>
        <%--<div class="col-md-12 UserPageP">--%>
            <%--<spring:message code="lang.users"/>--%>

        <%--</div>--%>
    <%--</div>--%>
    <div class="row">

        <div class="col-md-12 blockUserPage">
            <form action="<%=SEARCH%>" method="post">
                <div class="block4">
                    <spring:message code="lang.searchBy"/>
                </div>
                <div class="block1">
                    <select class="form-control " name="searchTerm">
                        <option value="all"><spring:message code="lang.showAll"/> </option>
                        <option value="username">email</option>
                        <option value="lastName"><spring:message code="lang.lastName"/> </option>
                        <option value="documentInfo"><spring:message code="lang.document"/> </option>
                    </select>
                </div>
                <div class="block2">
                    <input type="text" class="form-control  " name="searchString"/><br/>
                </div>
                <div class="block3">
                    <input type="submit" class="btn btn-outline-primary" value="<spring:message code="lang.search"/> "/>
                </div>

            </form>
        </div>






    </div>
    <div class="row">
        <div class="col-md-10">
        </div>
        <div class="col-md-2 numOfUsers">
            <div>
                <spring:message code="lang.showUsersBy"/> <a
                    href="?size=1&searchTerm=${searchTerm}&searchString=${searchString}">1</a> | <a
                    href="?size=5&searchTerm=${searchTerm}&searchString=${searchString}"> 5</a>
            </div>
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
                <c:forEach items="${users.getContent()}" var="user">
                    <tr>

                        <td><c:out value="${user.firstName}"/></td>
                        <td><c:out value="${user.lastName}"/></td>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.documentInfo}"/></td>
                        <td><c:out value="${user.birthday}"/></td>


                        <td>


                            <c:if test="${sessionUser.id != user.id}">
                                <a href="<%=Endpoints.USER%>/${user.id}" class="btn btn-outline-primary updateUser">
                                    <spring:message
                                            code="lang.update"/></a>

                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <c:if test="${user.active}">
                                        <form action="<%=BLOCK_USER%>" method="post">
                                            <input type="hidden" name="id" value="${user.id}"/>
                                            <input type="submit" value="<spring:message code="lang.blockUser"/>"
                                                   class="btn btn-outline-danger blockUser"/>
                                        </form>

                                    </c:if>
                                    <c:if test="${!user.active}">
                                        <form action="<%=UNBLOCK_USER%>" method="post">
                                            <input type="hidden" name="id" value="${user.id}"/>
                                            <input type="submit" value="<spring:message code="lang.unblockUser"/>"
                                                   class="btn btn-outline-success blockUser"/>
                                        </form>
                                    </c:if>
                                </sec:authorize>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

            <div>
                <c:if test="${users.hasPrevious()}"> <a
                        href="?page=${users.number-1}&size=${users.size}"><spring:message
                        code="lang.previous"/></a></c:if>
                <c:forEach var="page" begin="1" end="${users.totalPages}">
                    <a href="?page=${page-1}&size=${users.size}">${page}</a>
                </c:forEach>
                <c:if test="${users.hasNext()}"> <a href="?page=${users.number+1}&size=${users.size}"><spring:message
                        code="lang.next"/></a></c:if>
            </div>


        </div>
    </div>
</div>
</body>
</html>

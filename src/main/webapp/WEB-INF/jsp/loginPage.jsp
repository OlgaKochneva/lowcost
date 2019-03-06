<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <base href="/">
    <title><spring:message code="lang.login" text="Login"/></title>
    <spring:url value="/resources/static/css/login.css" var="login_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="/resources/static/css/login.css" rel="stylesheet">

</head>
<body>

<div class="wrapper">
    <p align="right" class="language"><a href="?lang=en"><img src="../../resources/static/img/united_kingdom_round_icon_64.png" /></a>
        <a href="?lang=ru"><img src="../../resources/static/img/russia_round_icon_64.png"/> </a></p>

    <div class="main_block">

        <p class="greeting"><spring:message code="lang.loginIntroduction"/></p>
        <form method="POST" action="${contextPath}/login" class="form-signin">
            <p class="message">${message}</p>
            <div>
                <p style="color: #dc3545"> <span>${error}</span></p>
                <input name="username" type="text" class="form-control input" placeholder="Username"
                       autofocus="true"/>
                <br/>
                <input name="password" type="password" class="form-control input" placeholder="Password"/>
                <br/>
                <input type="submit" value="<spring:message code="lang.logIn"/>" class="btn btn-outline-primary btnAuth"/>

            </div>
        </form>
        <a href="${contextPath}/registration"><spring:message code="lang.signIn"/></a>

    </div>

</div>
</body>
</html>

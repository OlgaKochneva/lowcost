<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="/resources/static/css/404.css" var="login_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="/resources/static/css/404.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>Error page</title>
</head>
<body>
<details>
    <summary>Additional info about error</summary>
    <p>Cause is: ${errorCause}</p>
    <p>Trace is: ${trace}</p>
</details>

<div class="wrapper">
    <h1>Hmm.</h1>
    <p>It seems that you're lost in a perpetual black hole. Let us help guide you out and get you back home.</p>
    <div class="buttons"><a href="/">home</a></div>
</div>



<div class="space">
    <div class="blackhole"></div>
    <div class="ship"></div>
</div>

</body>
</html>
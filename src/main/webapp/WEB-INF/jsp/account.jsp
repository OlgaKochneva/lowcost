<%@ page import="com.epam.lowcost.util.Endpoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.personalCabinet"/></title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/static/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 GroupSearch">
            <%--<a href="<%=Endpoints.FLIGHTS + Endpoints.FLIGHT%>"><spring:message--%>
                    <%--code="lang.buyMoreTickets"/></a>--%>
        </div>
    </div>
    <div class="row OneRow">


        <c:forEach items="${currentUserTickets}" var="ticket">
            <div class="col-md-6 OneTicket">
                <c:if test="${ticket.paid}">
                <div class="backgroundTicket">

                    <spring:message code="lang.departureAirport"/>: <c:out
                        value="${ticket.flight.departureAirport.cityEng}"/><br/>
                    <spring:message code="lang.arrivalAirport"/>: <c:out
                        value="${ticket.flight.arrivalAirport.cityEng}"/><br/>
                    <spring:message code="lang.departureAt"/>: <c:out value="${ticket.flight.departureDate.toString().replaceAll( 'T', ' ')}"/><br/>
                    <spring:message code="lang.arriveAt"/>: <c:out value="${ticket.flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/><br/>
                    <spring:message code="lang.price"/>: <c:out value="${ticket.price}"/> <spring:message code="lang.currency"/> <br/>
                    <div class="buttonGroupCard">


                    <%--<input type="button"--%>
                           <%--onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport.cityEng} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport.cityEng} At ${ticket.flight.arrivalDate}')"--%>
                           <%--value="<spring:message code="lang.details"/>"--%>
                           <%--class="btn btn-outline-primary buttonFloatLeft"/>--%>

                    <form action="<%=Endpoints.TICKETS + Endpoints.CANCEL%>/" method="post">
                        <input type="hidden" name="id" value="${ticket.id}"/>
                        <input type="submit" value="<spring:message code="lang.cancel" />" class="btn btn-outline-danger buttonFloatLeft" onclick=" ActionButton(event,${ticket.id},'<spring:message code="lang.confirmCancel" />')" id="${ticket.id}" data-click="false"/>

                    </form>
                        <br/> <br/>
                    </div>
                </div>
                </c:if>
                <c:if test="${!ticket.paid}">
                    <div class="backgroundTicketUnpaid">

                        <spring:message code="lang.departureAirport"/>: <c:out value="${ticket.flight.departureAirport.cityEng}"/><br/>
                        <spring:message code="lang.arrivalAirport"/>: <c:out value="${ticket.flight.arrivalAirport.cityEng}"/><br/>
                        <spring:message code="lang.departureAt"/>: <c:out value="${ticket.flight.departureDate.toString().replaceAll( 'T', ' ')}"/><br/>
                        <spring:message code="lang.arriveAt"/>: <c:out value="${ticket.flight.arrivalDate.toString().replaceAll( 'T', ' ')}"/><br/>
                        <spring:message code="lang.price"/>: <c:out value="${ticket.price}"/> y.e  <br/>
                        <div class="buttonGroupCard">


                            <%--<input type="button"--%>
                                   <%--onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport.cityEng} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport.cityEng} At ${ticket.flight.arrivalDate}')"--%>
                                   <%--value="<spring:message code="lang.details"/>"--%>
                                   <%--class="btn btn-outline-primary buttonFloatLeft"/>--%>

                            <form action="<%=Endpoints.TICKETS + Endpoints.CANCEL%>" method="post" >
                                <input type="hidden" name="id" value="${ticket.id}"/>
                                <input type="submit" value="<spring:message code="lang.cancel" />" class="btn btn-outline-danger buttonFloatLeft buttonAction " onclick=" ActionButton(event,${ticket.id},'<spring:message code="lang.confirmCancel" />')" id="${ticket.id}" data-click="false"/>

                            </form>
                            <c:if test="${!ticket.paid}">
                                <form action="<%=Endpoints.TICKETS+Endpoints.PAY%>" method="post">
                                    <input type="hidden", name="id", value="${ticket.id}">
                                    <input type="submit" value="<spring:message code="lang.pay"/>" class="btn btn-outline-success btPay "onclick=" ActionButton2(event,${ticket.id},'<spring:message code="lang.confirmPayment" /> ${ticket.price} <spring:message code="lang.currency"/> ?')" id="l${ticket.id}" data-click="false"/>
                                </form>
                            </c:if>


                        </div>
                    </div>
                </c:if>
                <form action="<%=Endpoints.TICKETS + Endpoints.PDF%>" method="get">
                    <input type="hidden" name="ticketId" value="${ticket.id}">
                    <input type ="hidden" name="userEmail" value="${sessionUser.username}">
                    <input type="submit" value="Get Pdf to email" class="btn-outline-secondary"/>
                </form>
                <form action="<%=Endpoints.TICKETS + Endpoints.DOWNLOAD%>" method="get">
                    <input type="hidden" name="ticketId" value="${ticket.id}">
                    <input type="submit" value="Download Ticket" class="btn-outline-secondary"/>
                </form>

            </div>
        </c:forEach>
    </div>
</div>

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal"><spring:message code="lang.back"/> </button>
                <button type="button" class="btn btn-primary " id="butCloseModal" onclick="unPrevent()" data-this=''><spring:message code="lang.yes"/> </button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script>
    function unPrevent() {
        var id = $('#butCloseModal').attr('data-this');
        console.log(id);
        $('#'+id).attr('data-click','true');
        $('#'+id).click();
        $('#confirmModal').modal('hide');

    }

    function ActionButton(event,id,str) {
        var bol = $('#' + id).attr('data-click');
        if (bol == 'false') {
            event.preventDefault();
            $('.modal-body').text(str);
            $('#butCloseModal').attr('data-this', id);
            $('#confirmModal').modal('show');
        }
    }
        function ActionButton2(event,id,str) {
            var bol = $('#l' + id).attr('data-click');
            if (bol == 'false') {
                event.preventDefault();
                $('.modal-body').text(str);
                $('#butCloseModal').attr('data-this', 'l' + id);
                $('#confirmModal').modal('show');
            }
        }

</script>
</body>
</html>
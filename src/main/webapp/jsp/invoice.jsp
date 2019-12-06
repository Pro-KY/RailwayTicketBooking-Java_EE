<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<fmt:message var ="date" key="invoice.date" bundle="${rb}"/>
<fmt:message var ="time" key="invoice.time" bundle="${rb}"/>
<fmt:message var ="departure" key="invoice.departure" bundle="${rb}"/>
<fmt:message var ="arrival" key="invoice.arrival" bundle="${rb}"/>
<c:set var="invoiceDto" value="${sessionScope.invoiceDto}" scope="session"/>

<html>
<head>
    <title>Invoice</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <div class="container float-left pt-4 ml-3 mt-5">
        <div class="row">
            <div class="col-md-9">
                <table class="table">
                    <tr class="table-row">
                        <td><fmt:message key="user.firstName" bundle="${rb}"/> <fmt:message key="user.lastName" bundle="${rb}"/></td>
                        <td>${invoiceDto.userFirstName} ${invoiceDto.userLastName}</td>
                    </tr>
                    <tr class="table-row">
                        <td><fmt:message key="table.header.route" bundle="${rb}"/></td>
                        <td><fmt:message key="station.${invoiceDto.departureStationId}" bundle="${rb}"/> - <fmt:message key="station.${invoiceDto.arrivalStationId}" bundle="${rb}"/></td>
                    </tr>
                    <tr class="table-row">
                        <td><fmt:message key="table.header.train" bundle="${rb}"/></td>
                        <td>${invoiceDto.trainId} ${invoiceDto.trainType}</td>
                    </tr>
                    <tr class="table-row">
                        <td>${departure} ${date}/${time}</td>
                        <td>${invoiceDto.routeDepartureDate} / ${invoiceDto.routeDepartureTime}</td>
                    </tr>
                    <tr class="table-row">
                        <td> ${arrival} ${date}/${time}</td>
                        <td>${invoiceDto.routeArrivalDate} / ${invoiceDto.routeArrivalTime}</td>
                    </tr>
                    <tr class="table-row">
                        <td><fmt:message key="invoice.seats.amount" bundle="${rb}"/></td>
                        <td>${invoiceDto.seatsAmount}</td>
                    </tr>
                    <tr class="table-row">
                        <td><fmt:message key="invoice.sum" bundle="${rb}"/></td>
                        <td>${invoiceDto.sum}</td>
                    </tr>
                </table>
            </div>
            <div class="col-md-3"></div>
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <!-- FOOTER -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>

<c:remove var="invoiceDto" scope="session" />

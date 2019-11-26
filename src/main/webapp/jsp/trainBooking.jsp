<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<fmt:message var="firstName" key="user.firstName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${sessionScope.rb}" scope="page"/>

<html>
<head>
    <title>Train booking</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <div class="container float-left ml-3 mt-5 pt-3">
        <form method="POST" action ="booking/" name="ticketBooking">
            <input type="hidden" name="command" value="${Commands.INVOICE}">
            <input type="hidden" name="${Parameters.TRAIN_ID}" value="${train.trainId}">

            <div class="row align-items-start justify-content-start mb-3">
                <div class="col-md-4 mt-3">
                    <%--        firstName/lastName (guest)--%>
                    <c:if test="${empty sessionScope.user}">
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label for="inputFirstName"><fmt:message key="user.firstName" bundle="${rb}"/></label>
                                <input type="text" required class="form-control" id="inputFirstName" placeholder="${firstName}" name="${Parameters.USER_FIRST_NAME}">
                            </div>

                            <div class="form-group col-md-6">
                                <label for="inputLastName"><fmt:message key="user.lastName" bundle="${rb}"/></label>
                                <input type="text" required class="form-control" id="inputLastName" placeholder="${lastName}" name="${Parameters.USER_LAST_NAME}">
                            </div>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label for="numberInput"><fmt:message key="seats" bundle="${rb}"/></label>
                        <input class="form-control" type="number" value="1" id="numberInput" min="1" max="5" name="${Parameters.SEATS_AMOUNT}">
                    </div>
                </div>
                <div class="col-md-8 mt-5">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="table.header.train" bundle="${rb}"/></th>
                            <th scope="col"><fmt:message key="table.header.route" bundle="${rb}"/></th>
                            <th scope="col"><fmt:message key="table.header.date" bundle="${rb}"/></th>
                            <th scope="col"><fmt:message key="table.header.time" bundle="${rb}"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="table-row">
                            <td>${train.trainId} ${train.trainType}</td>
                            <td><fmt:message key="station.${train.departureStationId}" bundle="${rb}"/> - <fmt:message key="station.${train.arrivalStationId}" bundle="${rb}"/></td>
                            <td>${train.routeDepartureDate} <br> ${train.routeArrivalDate}</td>
                            <td>${train.routeDepartureTime} <br> ${train.routeArrivalTime}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row align-items-start justify-content-center">
                <div class="col-md-6"></div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary col-12" data-dismiss="modal"> <fmt:message key="invoice.get" bundle="${rb}"/> </button>
                </div>
                <div class="col-md-3"></div>
            </div>
        </form>
    </div>

    <!-- FOOTER -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <!-- FOOTER -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>

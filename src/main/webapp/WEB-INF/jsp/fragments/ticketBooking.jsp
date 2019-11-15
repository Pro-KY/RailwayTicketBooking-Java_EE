<fmt:message var="firstName" key="user.firstName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${sessionScope.rb}" scope="page"/>

<form method="POST" action ="booking/" name="ticketBooking">
    <input type="hidden" name="command" value="${Commands.INVOICE}">
    <input type="hidden" name="${Parameters.TRAIN_ID}" value="${train.trainId}">

    <div class="row align-items-start justify-content-start mb-3">
        <div class="col-md-4 mt-3">
    <%--        firstName/lastName (guest)--%>
            <c:if test="${empty sessionScope.user}">
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label for="inputFirstName">FirstName</label>
                            <input type="text" class="form-control" id="inputFirstName" placeholder="${firstName}" name="${Parameters.USER_FIRST_NAME}">
                        </div>

                        <div class="form-group col-md-6">
                            <label for="inputLastName">LastName</label>
                            <input type="text" class="form-control" id="inputLastName" placeholder="${lastName}" name="${Parameters.USER_LAST_NAME}">
                        </div>
                    </div>

            </c:if>
            <div class="form-group">
                <label for="numberInput">Seats</label>
                <input class="form-control" type="number" value="1" id="numberInput" min="1" max="5" name="${Parameters.SEATS}">
            </div>
        </div>
        <div class="col-md-8">
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
                        <td>${train.routeName}</td>
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
    <%--        get payment invoice button--%>
            <button type="submit" class="btn btn-primary col-12" data-dismiss="modal"> Bill for ticket </button>
        </div>
        <div class="col-md-3"></div>
    </div>
</form>

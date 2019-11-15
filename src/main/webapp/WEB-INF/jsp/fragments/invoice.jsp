<fmt:message var ="date" key="invoice.date" bundle="${rb}"/>
<fmt:message var ="time" key="invoice.time" bundle="${rb}"/>
<fmt:message var ="departure" key="invoice.departure" bundle="${rb}"/>
<fmt:message var ="arrival" key="invoice.arrival" bundle="${rb}"/>
<c:set var="model" value="${sessionScope.model}" scope="page"/>

<div class="row">
    <div class="col-md-9">
        <table class="table">
            <tr class="table-row">
                <td><fmt:message key="user.firstName" bundle="${rb}"/> <fmt:message key="user.lastName" bundle="${rb}"/></td>
                <td>${model.userFirstName} ${model.userLastName}</td>
            </tr>
            <tr class="table-row">
                <td><fmt:message key="table.header.route" bundle="${rb}"/></td>
                <td>${model.routeName}</td>
            </tr>
            <tr class="table-row">
                <td><fmt:message key="table.header.train" bundle="${rb}"/></td>
                <td>${model.trainId} ${model.trainType}</td>
            </tr>
            <tr class="table-row">
                <td>${departure} ${date}/${time}</td>
                <td>${model.routeDepartureDate} ${model.routeDepartureTime}</td>
            </tr>
            <tr class="table-row">
                <td> ${arrival} ${date}/${time}</td>
                <td>${model.routeArrivalDate} ${model.routeArrivalTime}</td>
            </tr>
            <tr class="table-row">
                <td><fmt:message key="invoice.seats.amount" bundle="${rb}"/></td>
                <td>${model.seatsAmount}</td>
            </tr>
            <tr class="table-row">
                <td><fmt:message key="invoice.sum" bundle="${rb}"/></td>
                <td>${model.sum}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-3"></div>
</div>

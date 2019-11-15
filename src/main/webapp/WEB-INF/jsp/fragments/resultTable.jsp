<%--<div style="border: #0b2e13" class="row">--%>
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

        <c:forEach var="train" items="${pageDto.pageList}">
            <tr class="table-row clickable-row" data-href="/booking/?${Parameters.TRAIN_ID}=${train.trainId}&command=${Commands.GET_TICKET_BOOKING_FRAGMENT}">
                <td>${train.trainId} ${train.trainType}</td>
                <td>
                    <c:set var="stations" value="" />
                    <c:forEach var="st" items="${train.stations}">
                        <fmt:message var ="station" key="station.${st.stationId}" bundle="${rb}" scope="page"/>
                        <c:set var="stations" value="${stations += station} "/>
                    </c:forEach>
                    <button type="button" class="btn btn-link example-popover" data-toggle="popover" title="${route}" data-content="${stations}">${train.routeName}</button>
                </td>
                <td>${train.routeDepartureDate} <br> ${train.routeArrivalDate}</td>
                <td>${train.routeDepartureTime} <br> ${train.routeArrivalTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%--</div>--%>

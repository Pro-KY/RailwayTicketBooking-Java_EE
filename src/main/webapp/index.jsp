<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<c:set var="user" value="${sessionScope.user}" scope="session"/>
<c:set var="stations" value="${sessionScope.stations}" scope="session"/>

<c:set var="trainsPageDto" value="${sessionScope.trainsPageDto}" scope="session"/>
<c:set var="findTrainsCommand" value="${Commands.FIND_TRAINS}" scope="page"/>
<c:set var="findTrainParameters" value="${Attributes.GOING_TO_ID}=${param.goingToId}&${Attributes.DEPARTURE_DATE}=${param.departureDate}&${Attributes.DEPARTURE_TIME}=${param.departureTime}" scope="page"/>
<fmt:message var="route" key="table.header.route" bundle="${rb}"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page"/>

<html>
<head>
    <title>Main page</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
    <script type="text/javascript" src="${contextPath}/assets/js/moment.js"></script>
    <script type="text/javascript" src="${contextPath}/assets/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/tempusdominus-bootstrap-4.min.css"/>">
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <!--MAIN CONTENT-->
<%--    row mr-1 ml-1--%>
    <div class="container float-left ml-3 mt-5 pt-4">
        <div class="row justify">
            <form name = "findTrain" method="POST"  action="booking/">
                <input type="hidden" name="command" value="${findTrainsCommand}">

                <div class="row">
                    <%--GOING_TO--%>
                    <div class="col-3">
                        <div class="form-group">
                            <label for="usertype"><fmt:message key="going.to" bundle="${rb}"/></label>
                            <select class="form-control" id="usertype" name="${Parameters.GOING_TO_ID}">
                                <c:forEach var="station" items="${stations}">
                                    <option value="${station.id}" ${station.id == '1' ? 'selected' : ''}><fmt:message key="station.${station.id}" bundle="${rb}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--GOING_TO--%>
                    <%--DATE--%>
                    <div class="col-3">
                        <div class="form-group">
                            <label for="dateInput"><fmt:message key="departure.date" bundle="${rb}"/></label>
                            <div class="input-group date" id="datetimepicker4" data-target-input="nearest">
                                <input type="text" id="dateInput" class="form-control datetimepicker-input" value="${param.departureDate}" data-target="#datetimepicker4" name="${Parameters.DEPARTURE_DATE}"/>
                                <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--DATE--%>
                    <%--TIME--%>
                    <div class="col-3">
                        <div class="form-group">
                            <label for="timeInput"><fmt:message key="departure.time" bundle="${rb}"/></label>
                            <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                                <input type="text" id = "timeInput" value="${param.departureTime}" class="form-control datetimepicker-input" data-target="#datetimepicker3" name="${Parameters.DEPARTURE_TIME}"/>
                                <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--TIME--%>
                    <div class="col-3">
                        <br/>
                        <button type="submit" class="btn btn-primary mt-2" data-dismiss="modal"><fmt:message key="find.train.btn" bundle="${rb}"/></button>
                    </div>
                </div>
            </form>
        </div>

        <c:if test="${trainsPageDto != null and trainsPageDto.allPagesAmount > 0}">
            <%--PAGE_SIZE--%>
            <div id="elementsAmountSelect" class="row">
                <div class="col-md-10"></div>
                <div class="col-md-2">
                    <div class="dropdown show">
                        <a class="dropdown-toggle nav-link" href="#" id="pageSizeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="sent.reports.page.size" bundle="${rb}"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="pageSizeDropdown">
                            <a class="dropdown-item" href="<c:url value="booking/?${Parameters.PAGE_SIZE}=3&command=${findTrainCommand}&${findTrainParameters}"/>"> 3 </a>
                            <a class="dropdown-item" href="<c:url value="booking/?${Parameters.PAGE_SIZE}=5&command=${findTrainCommand}&${findTrainParameters}"/>"> 5 </a>
                            <a class="dropdown-item" href="<c:url value="booking/?${Parameters.PAGE_SIZE}=10&command=${findTrainCommand}&${findTrainParameters}"/>">10</a>
                        </div>
                    </div>
                </div>
            </div>
            <%--PAGE_SIZE--%>

            <%--TABLE--%>
            <div style="border: #0b2e13" class="row">
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

                    <c:forEach var="train" items="${trainsPageDto.pageList}">
                        <jsp:useBean id="train" type="com.proky.booking.dto.TrainDto"/>
                        <tr class="table-row clickable-row" data-href="${contextPath}/booking/?${Parameters.TRAIN_ID}=${train.trainId}&command=${Commands.TRAIN_BOOKING}">
                            <td>${train.trainId} ${train.trainType}</td>
                            <td>
                                <c:set var="stations" value="" />
                                <c:forEach var="st" items="${train.stations}">
                                    <fmt:message var ="station" key="station.${st.stationId}" bundle="${rb}" scope="page"/>
                                    <c:set var="stations" value="${stations += station} "/>
                                </c:forEach>
                                <button type="button" class="btn btn-link example-popover" data-trigger="hover" data-toggle="popover" title="${route}" data-content="${stations}">
                                    <fmt:message key="station.${train.departureStationId}" bundle="${rb}"/> - <fmt:message key="station.${train.arrivalStationId}" bundle="${rb}"/>
                                </button>
                            </td>
                            <td>${train.routeDepartureDate} <br> ${train.routeArrivalDate}</td>
                            <td>${train.routeDepartureTime} <br> ${train.routeArrivalTime}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--</div>--%>
            </div>
            <%--TABLE--%>
        </c:if>

        <%--PAGINATION--%>
        <c:if test="${trainsPageDto != null and trainsPageDto.allPagesAmount > 1}">
    <%--            <%@include file="/WEB-INF/jspf/pagination.jspf" %>--%>
            <div class="row mt-4">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <nav aria-label="...">
                        <ul class="pagination" style="list-style-type: none;">
                            <div class="container-fluid">
                                <div class="btn-group">
                                    <li class="${trainsPageDto.isLeftButtonDisabled ? 'page-item disabled' : 'page-item'}">
                                        <a class="page-link" href="${contextPath}?${Parameters.PAGE_SIZE}=${trainsPageDto.pageSize}&${Parameters.PREV_PAGE_CLICK}=true&command=${findTrainsCommand}&${findTrainParameters}&${Parameters.SELECTED_PAGE_INDEX}=${trainsPageDto.currentPageIndex}">
                                            <fmt:message key="pagination.previous" bundle="${rb}"/>
                                        </a>
                                    </li>

                                    <c:forEach begin="${trainsPageDto.startPageIndex}" end="${trainsPageDto.endPageIndex}" varStatus="counter">
                                        <li class="${(trainsPageDto.currentPageIndex) eq counter.index ? 'page-item active' : 'page-item'}">
                                            <a class="page-link" href="${contextPath}?${Parameters.PAGE_SIZE}=${trainsPageDto.pageSize}&${Parameters.SELECTED_PAGE_INDEX}=${counter.index}&command=${findTrainsCommand}&${findTrainParameters}">
                                                ${counter.index+1}
                                            </a>
                                        </li>
                                    </c:forEach>

                                    <li class="${trainsPageDto.isRightButtonDisabled ? 'page-item disabled' : 'page-item'}">
                                        <a class="page-link" href="${contextPath}?${Parameters.PAGE_SIZE}=${trainsPageDto.pageSize}&${Parameters.NEXT_PAGE_CLICK}=true&command=${findTrainsCommand}&${findTrainParameters}&${Parameters.SELECTED_PAGE_INDEX}=${trainsPageDto.currentPageIndex}">
                                            <fmt:message key="pagination.next" bundle="${rb}"/>
                                        </a>
                                    </li>
                                </div>
                            </div>
                        </ul>
                    </nav>
                </div>
            </div>
        </c:if>
        <%--PAGINATION--%>

        <%--TESTING PAGINATION--%>
<%--        <div class="float-left">--%>
<%--            <p>isLeftButtonDisabled : ${sessionScope.model.isLeftButtonDisabled}</p>--%>
<%--            <p>isRightButtonDisabled : ${sessionScope.model.isRightButtonDisabled}</p>--%>
<%--            <p>currentPageIndex ${sessionScope.model.currentPageIndex}</p>--%>
<%--            <p>allPagesAmount ${sessionScope.model.allPagesAmount}</p>--%>
<%--            <p>startPageIndex : ${sessionScope.model.startPageIndex}</p>--%>
<%--            <p>endPageIndex : ${sessionScope.model.endPageIndex}</p>--%>
<%--        </div>--%>
        <%--TESTING PAGINATION--%>
    </div>
    <!--MAIN CONTENT-->

<%--    <div class="container-fluid row">--%>
        <!-- FOOTER -->
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        <!-- FOOTER -->
<%--    </div>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>

<c:remove var="stations" scope="session" />
<c:remove var="trainsPageDto" scope="session" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageDto" value="${sessionScope.pageDto}" scope="session"/>
<c:set var="findTrainCommand" value="${Commands.FIND_TRAIN}" scope="page"/>
<c:set var="findTrainParameters" value="${Attributes.GOING_TO}=${param.goingTo}&${Attributes.DEPARTURE_DATE}=${param.departureDate}&${Attributes.DEPARTURE_TIME}=${param.departureTime}" scope="page"/>


<html>
<head>
    <title>main fragment</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/tempusdominus-bootstrap-4.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome/css/font-awesome.min.css"/>"/>
</head>
    <body>
    <%--        <div class="container">--%>
            <div class="row justify">
                <form name = "findTrain" method="POST"  action="booking/">
                    <input type="hidden" name="command" value="${findTrainCommand}">

                    <div class="row">
                        <%--GOING_TO--%>
                        <div class="col-3">
                            <div class="form-group">
                                <label for="usertype"><fmt:message key="going.to" bundle="${rb}"/></label>
                                <select class="form-control" id="usertype" name="${Parameters.GOING_TO}">
                                    <c:forEach var="station" items="${sessionScope.stations}">
                                        <option value="${station.id}" selected><fmt:message key="station.${station.id}" bundle="${rb}"/></option>
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
                                    <input type="text" id="dateInput" class="form-control datetimepicker-input" data-target="#datetimepicker4" name="${Parameters.DEPARTURE_DATE}"/>
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
                                    <input type="text" id = "timeInput" class="form-control datetimepicker-input" data-target="#datetimepicker3" name="${Parameters.DEPARTURE_TIME}"/>
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

            <%--TABLE--%>
<%--            <c:if test="${not empty pageDto and not empty pageDto.pageList}">--%>
<%--                <div class="row" id="table_wrapper">--%>
                    <div id="elementsAmountSelect" class="row">
                        <div class="col-md-10"></div>
                        <%--page size--%>
                        <div class="col-md-2">
                            <div class="dropdown show">
                                <a class="dropdown-toggle nav-link" href="#" id="pageSizeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <fmt:message key="sent.reports.page.size" bundle="${rb}"/>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="pageSizeDropdown">
                                    <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=3&command=${findTrainCommand}&${findTrainParameters}"/>"> 3 </a>
                                    <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=5&command=${findTrainCommand}&${findTrainParameters}"/>"> 5 </a>
                                    <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=10&command=${findTrainCommand}&${findTrainParameters}"/>">10</a>
                                </div>
                            </div>
                        </div>
                        <%--page size--%>
                    </div>

                    <div style="border: #0b2e13" class="row">
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
                                <tr class="table-row" data-href="${pageContext.request.contextPath}?${Parameters.TRAIN_ID}=${train.trainId}&command=${Commands.TICKET_PURCHASE_REQUEST}">
                                    <td>${train.trainId} ${train.trainType}</td>
                                    <td>${train.routeName}</td>
                                    <td>${train.routeDepartureDate} <br> ${train.routeArrivalDate}</td>
                                    <td>${train.routeDepartureTime} <br> ${train.routeArrivalTime}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
<%--                </div>--%>
<%--            </c:if>--%>
            <%--TABLE--%>

            <%--PAGINATION--%>
            <div class="row mt-4">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <c:if test="${sessionScope.pageDto.allPagesAmount > 1}">
                        <nav aria-label="...">
                            <ul class="pagination" style="list-style-type: none;">
                                <div class="container-fluid">
                                    <div class="btn-group">
                                        <c:if test="${sessionScope.paginationInfo.allPagesAmount > 1}">
                                            <li class="${sessionScope.paginationInfo.isLeftButtonDisabled ? 'page-item disabled' : 'page-item'}">
                                                <a class="page-link" href="${pageContext.request.contextPath}?${Parameters.PAGE_SIZE}=${sessionScope.paginationInfo.pageSize}&${Parameters.PREV_PAGE_CLICK}=true&command=${command}">Previous</a>
                                            </li>
                                        </c:if>

                                        <c:forEach begin="${sessionScope.paginationInfo.startPageIndex}" end="${sessionScope.paginationInfo.endPageIndex}" varStatus="counter">
                                            <li class="${(sessionScope.paginationInfo.currentPageIndex) eq counter.index ? 'page-item active' : 'page-item'}">
                                                <a class="page-link" href="${pageContext.request.contextPath}?${Parameters.PAGE_SIZE}=${sessionScope.paginationInfo.pageSize}&${Parameters.SELECTED_PAGE_INDEX}=${counter.index}&command=${command}"> ${counter.index+1} </a>
                                            </li>
                                        </c:forEach>

                                        <c:if test="${sessionScope.paginationInfo.allPagesAmount > 1}">
                                            <li class="${sessionScope.paginationInfo.isRightButtonDisabled ? 'page-item disabled' : 'page-item'}">
                                                <a class="page-link" href="${pageContext.request.contextPath}?${Parameters.PAGE_SIZE}=${sessionScope.paginationInfo.pageSize}&${Parameters.NEXT_PAGE_CLICK}=true&command=${command}">Next</a>
                                            </li>
                                        </c:if>
                                    </div>
                                </div>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            <%--PAGINATION--%>
<%--        </div>--%>
    </body>
</html>

<%--TESTING--%>
<div class="float-left">
    <p>isLeftButtonDisabled : ${sessionScope.pageDto.isLeftButtonDisabled}</p>
    <p>isRightButtonDisabled : ${sessionScope.pageDto.isRightButtonDisabled}</p>
    <p>currentPageIndex ${sessionScope.pageDto.currentPageIndex}</p>
    <p>allPagesAmount ${sessionScope.pageDto.allPagesAmount}</p>
    <p>startPageIndex : ${sessionScope.pageDto.startPageIndex}</p>
    <p>endPageIndex : ${sessionScope.pageDto.endPageIndex}</p>
</div>
<%--TESTING--%>

<%--${not empty sessionScope.validation}--%>
<%--<c:forEach var="entry" items="${sessionScope.validation.errorMessages}">--%>
<%--    <tr><td><c:out value="${entry.key}"/></td> <td><c:out value="${entry.value}"/> </td></tr>--%>
<%--</c:forEach>--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>main fragment</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/tempusdominus-bootstrap-4.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/fonts/fontawesome/css/font-awesome.min.css"/>"/>
</head>
    <body>
        <div class="container-fluid">
            ${param.errMsg}

            <form name = "findTrain" method="POST"  action="booking/">
                <input type="hidden" name="command" value="${Commands.FIND_TRAIN}">

                <div class="row">
                    <%--GOING_TO--%>
                    <div class="col-2">
                        <div class="form-group">

                            ${requestScope.findTrainDto.stringStringMap eq null}
                            <label for="usertype"><fmt:message key="going.to" bundle="${rb}"/></label>
                            <select class="form-control" id="usertype" name="${Parameters.GOING_TO}">
                                    <c:forEach var="entry" items="${requestScope.findTrainDto.stringStringMap}">
                                        <option value="${entry.key}" selected><fmt:message key="${entry.value}" bundle="${rb}"/></option>
                                    </c:forEach>

                                <%--                            <option value="1" selected><fmt:message key="signup.user.type.individual" bundle="${rb}"/></option>--%>
                                <%--                            loop through list of cities (id, key.message, )--%>
                            </select>
                        </div>
                    </div>
                    <%--GOING_TO--%>
                    <%--DATE--%>
                    <div class="col-2">
                        <div class="form-group">
                            <label for="dateInput"><fmt:message key="departure.date" bundle="${rb}"/></label>
                            <div class="input-group date" id="datetimepicker4" data-target-input="nearest">
                                <input type="text" id="dateInput" class="form-control datetimepicker-input" data-target="#datetimepicker4"/>
                                <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--DATE--%>
                    <%--TIME--%>
                    <div class="col-2">
                        <div class="form-group">
                            <label for="timeInput"><fmt:message key="departure.time" bundle="${rb}"/></label>
                            <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                                <input type="text" id = "timeInput" class="form-control datetimepicker-input" data-target="#datetimepicker3"/>
                                <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--TIME--%>
                    <div class="col-6">
                        <br/>
                        <button type="submit" class="btn btn-primary mt-2" data-dismiss="modal"><fmt:message key="find.train.btn" bundle="${rb}"/></button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>


<%--${not empty sessionScope.validation}--%>
<%--<c:forEach var="entry" items="${sessionScope.validation.errorMessages}">--%>
<%--    <tr><td><c:out value="${entry.key}"/></td> <td><c:out value="${entry.value}"/> </td></tr>--%>
<%--</c:forEach>--%>



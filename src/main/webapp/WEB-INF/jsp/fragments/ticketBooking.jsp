<fmt:message var="firstName" key="user.firstName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${sessionScope.rb}" scope="page"/>

<form method="POST" action ="booking/" id="ticketBooking">
    <div class="row align-items-start justify-content-start mb-3">
        <div class="col-md-3 mt-3">
    <%--        firstName/lastName (guest)--%>
            <c:if test="${empty sessionScope.user}">
                    <input type="hidden" name="command" value="${Commands.SIGN_IN}">

                    <label for="inputFirstName">FirstName</label>
                    <div class="form-group">
                        <input type="text" class="form-control" id="inputFirstName" placeholder="${firstName}" name="${Parameters.USER_FIRST_NAME}">
                    </div>

                    <div class="form-group">
                        <label for="inputLastName">LastName</label>
                        <input type="text" class="form-control" id="inputLastName" placeholder="${lastName}" name="${Parameters.USER_LAST_NAME}">
                    </div>
            </c:if>
        </div>
        <div class="col-md-9">
    <%--        result table--%>
            <%@include file="/WEB-INF/jsp/fragments/resultTable.jsp" %>
        </div>
    </div>
    <div class="row align-items-start justify-content-center">
        <div class="col-md-3">
    <%--        ticket amount counter--%>
            <div class="qty">
                <span class="minus bg-primary">-</span>
                <input type="number" class="count" name="qty" value="1">
                <span class="plus bg-primary">+</span>
            </div>
        </div>
        <div class="col-md-3"></div>
        <div class="col-md-3">
    <%--        get payment invoice button--%>
            <button type="submit" class="btn btn-primary col-12" data-dismiss="modal"> get Payment </button>
        </div>
        <div class="col-md-3"></div>
    </div>
</form>

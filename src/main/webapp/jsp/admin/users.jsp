<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Main page</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <!--MAIN CONTENT-->
    <%--    row mr-1 ml-1--%>
    <div class="container float-left ml-4 mt-5 pt-4">
        <%--PAGE_SIZE--%>
        <div id="elementsAmountSelect" class="row">
            <div class="col-md-10"></div>
            <div class="col-md-2">
                <div class="dropdown show">
                    <a class="dropdown-toggle nav-link" href="#" id="pageSizeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="sent.reports.page.size" bundle="${rb}"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="pageSizeDropdown">
                        <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=3&command=${Commands.ALL_USERS}"/>">  3  </a>
                        <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=5&command=${Commands.ALL_USERS}"/>">  5  </a>
                        <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=10&command=${Commands.ALL_USERS}"/>"> 10 </a>
                    </div>
                </div>
            </div>
        </div>
        <%--PAGE_SIZE--%>

        <%--TABLE--%>
        <div style="border: #0b2e13" class="row">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col"><fmt:message key="user.id" bundle="${rb}"/></th>
                        <th scope="col"><fmt:message key="user.firstName" bundle="${rb}"/></th>
                        <th scope="col"><fmt:message key="user.lastName" bundle="${rb}"/></th>
                        <th scope="col"><fmt:message key="user.email" bundle="${rb}"/></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="user" items="${model.pageList}">
                        <jsp:useBean id="user" type="com.proky.booking.dto.UserDto"/>
                        <tr class="table-row clickable-row" data-href="/booking/?${Parameters.USER_ID}=${user.id}&command=${Commands.MANAGE_USER}">
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <%--TABLE--%>

        <%--PAGINATION--%>
        <c:if test="${sessionScope.model.allPagesAmount > 1}">
        <div class="row mt-4">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <nav aria-label="...">
                    <ul class="pagination" style="list-style-type: none;">
                        <div class="container-fluid">
                            <div class="btn-group">
                                <li class="${sessionScope.model.isLeftButtonDisabled ? 'page-item disabled' : 'page-item'}">
                                    <a class="page-link" href="${contextPath}?${Parameters.PAGE_SIZE}=${sessionScope.model.pageSize}&${Parameters.PREV_PAGE_CLICK}=true&command=${Commands.ALL_USERS}">Previous</a>
                                </li>

                                <c:forEach begin="${sessionScope.model.startPageIndex}" end="${sessionScope.model.endPageIndex}" varStatus="counter">
                                    <li class="${(sessionScope.model.currentPageIndex) eq counter.index ? 'page-item active' : 'page-item'}">
                                        <a class="page-link" href="${contextPath}?${Parameters.PAGE_SIZE}=${sessionScope.model.pageSize}&${Parameters.SELECTED_PAGE_INDEX}=${counter.index}&command=${Commands.ALL_USERS}"> ${counter.index+1} </a>
                                    </li>
                                </c:forEach>

                                <li class="${sessionScope.model.isRightButtonDisabled ? 'page-item disabled' : 'page-item'}">
                                    <a class="page-link" href="${contextPath}?${Parameters.PAGE_SIZE}=${sessionScope.model.pageSize}&${Parameters.NEXT_PAGE_CLICK}=true&command=${Commands.ALL_USERS}">Next</a>
                                </li>
                            </div>
                        </div>
                    </ul>
                </nav>
            </div>
        </div>
        </c:if>
        <%--PAGINATION--%>
    </div>
    <!--MAIN CONTENT-->

    <!-- FOOTER -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <!-- FOOTER -->

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>

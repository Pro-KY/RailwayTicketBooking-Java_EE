<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

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
    <div class="container float-left ml-3">
        <c:if test="${sessionScope.pageDto.allPagesAmount > 1}">

            <%--PAGE_SIZE--%>
            <div id="elementsAmountSelect" class="row">
                <div class="col-md-10"></div>
                <div class="col-md-2">
                    <div class="dropdown show">
                        <a class="dropdown-toggle nav-link" href="#" id="pageSizeDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="sent.reports.page.size" bundle="${rb}"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="pageSizeDropdown">
                            <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=3&command=${Commands.FIND_ALL_USERS}"/>">  3  </a>
                            <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=5&command=${Commands.FIND_ALL_USERS}"/>">  5  </a>
                            <a class="dropdown-item" href="<c:url value="/booking/?${Parameters.PAGE_SIZE}=10&command=${Commands.FIND_ALL_USERS}"/>"> 10 </a>
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
                    <c:forEach var="user" items="${pageDto.pageList}">
                        <tr class="table-row clickable-row" data-href="/booking/?${Parameters.USER_ID}=${user.userId}&command=${Commands.CHANGE_USER}">
                            <td>${user.userId}<td>
                            <td>${user.firstName}<td>
                            <td>${user.lastName}<td>
                            <td>${user.email}<td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%--TABLE--%>

            <%--PAGINATION--%>
            <%@include file="/WEB-INF/jspf/pagination.jspf" %>
            <%--PAGINATION--%>
        </c:if>
    </div>
    <!--MAIN CONTENT-->

    <div class="container-fluid row">
        <!-- ALERT -->
        <c:if test="${(not empty alertError and alertError eq true) or
             (not empty alertSuccess and alertSuccess eq true)}">
            <%@ include file="/WEB-INF/jspf/alert.jspf"%>
        </c:if>
        <!-- ALERT -->

        <!-- FOOTER -->
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        <!-- FOOTER -->
    </div>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>

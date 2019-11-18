<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<c:set var="user" value="${sessionScope.user}" scope="session"/>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages" var = "rb" scope="session"/>

<html>
<head>
    <title>Main page</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/assets/css/tempusdominus-bootstrap-4.min.css"/>">
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <!--MAIN CONTENT-->
<%--    row mr-1 ml-1--%>
    <div class="container float-left ml-3">
        <%@include file="/WEB-INF/jspf/findTrains.jspf" %>

        <%--PAGINATION--%>
        <c:if test="${sessionScope.pageDto.allPagesAmount > 1}">
            <%@include file="/WEB-INF/jspf/pagination.jspf" %>
        </c:if>
        <%--PAGINATION--%>

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
    </div>
    <!--MAIN CONTENT-->

    <div class="container-fluid row">
        <!-- ALERT -->
        <c:if test="${(not empty alertError and alertError eq true) or (not empty alertSuccess and alertSuccess eq true)}">
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

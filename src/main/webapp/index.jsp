<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages" var = "rb" scope="session"/>

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
        <%@include file="/WEB-INF/jspf/findTrains.jspf" %>
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

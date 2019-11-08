<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="com.proky.booking.util.constans.Commands" %>
<%@ page import="com.proky.booking.util.constans.Parameters" %>
<%@ page import="com.proky.booking.util.constans.Attributes" %>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.messages" var = "rb" scope="session"/>

<html>
<head>
    <title>Main page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/flag-icon.min.css"/>">

    <script src="<c:url value="/jquery/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap/popper.js"/>"></script>
    <script src="<c:url value="/js/bootstrap/bootstrap.min.js"/>"></script>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
    <!-- HEADER -->

    <!--MAIN CONTENT-->
    <c:choose>
        <c:when test="${pageContext.request.getAttribute(Attributes.SIGN_UP_FRAGMENT) ne null}">
            <%@include file="/WEB-INF/jsp/fragments/signUp.jsp" %>
        </c:when>
        <c:when test="${pageContext.request.getAttribute(Attributes.GET_SIGN_IN_FRAGMENT) ne null}">
            <%@include file="/WEB-INF/jsp/fragments/signIn.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="/WEB-INF/jsp/fragments/main.jsp" %>
        </c:otherwise>
    </c:choose>
    ${not empty alertMessage}
    <!--MAIN CONTENT-->


    <div class="container-fluid row">
        <!-- ALERT -->
        <c:if test="${(not empty alertError and alertError eq true) or (not empty alertSuccess and alertSuccess eq true)}">
            <%@ include file="/WEB-INF/jsp/alert.jsp"%>
        </c:if>
        <!-- ALERT -->

        <!-- FOOTER -->
            <%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
        <!-- FOOTER -->
    </div>

</body>
</html>

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

    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/assets/css/main.css"/>">

    <script src="<c:url value="/assets/jquery/jquery.js"/>"></script>
    <script src="<c:url value="/assets/js/bootstrap/popper.js"/>"></script>
    <script src="<c:url value="/assets/js/bootstrap/bootstrap.min.js"/>"></script>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
    <!-- HEADER -->

    <!--MAIN CONTENT-->
<%--    row mr-1 ml-1--%>
    <div class="container float-left ml-3">
        <c:choose>
            <c:when test="${pageContext.request.getAttribute(Attributes.SIGN_UP_FRAGMENT) ne null}">
                <%@include file="/WEB-INF/jsp/fragments/signUp.jsp" %>
            </c:when>
            <c:when test="${pageContext.request.getAttribute(Attributes.GET_SIGN_IN_FRAGMENT) ne null}">
                <%@include file="/WEB-INF/jsp/fragments/signIn.jsp" %>
            </c:when>
            <c:when test="${pageContext.request.getAttribute(Attributes.GET_FRAGMENT_TICKET_BOOKING) ne null}">
                <%@include file="/WEB-INF/jsp/fragments/ticketBooking.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="/WEB-INF/jsp/fragments/main.jsp" %>
            </c:otherwise>
        </c:choose>
    </div>
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

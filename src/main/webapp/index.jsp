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
    <script src="<c:url value="/jquery/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap/popper.js"/>"></script>
    <script src="<c:url value="/js/bootstrap/bootstrap.min.js"/>"></script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>

    <!--MAIN CONTENT-->
    <c:choose>
        <c:when test="${pageContext.request.getAttribute(Attributes.SIGN_UP_FRAGMENT) ne null}">
            <%@include file="/WEB-INF/jsp/fragments/signUp.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="/WEB-INF/jsp/fragments/main.jsp" %>
        </c:otherwise>
    </c:choose>
    <!--MAIN CONTENT-->

    <%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
</body>
</html>

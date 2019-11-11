<%@ page contentType="text/html;charset=UTF-8"  isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<body>
heloo(
    Request from ${sessionScope.errorData.requestURI} is failed
    <br/>
    Servlet name: ${sessionScope.errorData.servletName}
    <br/>
    Status code: ${sessionScope.errorData.statusCode}
    <br/>
    <c:if test="${not empty sessionScope.errorData.exceptionName}">
        Exception: ${sessionScope.errorData.exceptionName}
    </c:if>
    <br/>
    <c:if test="${not empty sessionScope.errorData.exceptionMessage}">
        Message: ${sessionScope.errorData.exceptionMessage}
    </c:if>
    <br/>
    <a href="${pageContext.request.contextPath}/booking/">Home page</a>
</body>
</html>

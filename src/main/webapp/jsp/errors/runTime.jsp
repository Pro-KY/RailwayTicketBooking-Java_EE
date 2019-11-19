<%@ page contentType="text/html;charset=UTF-8"  isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<body>
    Request from ${param.requestURI} is failed
    <br/>
    Servlet name: ${param.servletName}
    <br/>
    Status code: ${param.statusCode}
    <br/>
    <c:if test="${not empty param.exceptionName and param.exceptionName ne 'null'}">
        Exception: ${param.exceptionName}
    </c:if>
    <br/>
    <c:if test="${not empty param.exceptionMessage and param.exceptionMessage ne 'null'}">
        Message: ${param.exceptionMessage}
    </c:if>
    <br/>
    <a href="${pageContext.request.contextPath}/booking/">Home page</a>
</body>
</html>
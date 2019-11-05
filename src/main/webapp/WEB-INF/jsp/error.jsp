<%@ page contentType="text/html;charset=UTF-8"  isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<body>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    <c:if test="${not empty pageContext.exception}">
        Exception: ${pageContext.exception}
    </c:if>
    <br/>
    <c:if test="${not empty pageContext.exception.message}">
        Message: ${pageContext.exception.message}
    </c:if>
</body>
</html>

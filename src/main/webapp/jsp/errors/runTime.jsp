<%@ page contentType="text/html;charset=UTF-8"  isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap/bootstrap.min.css"/>">
</head>
<body>
    <div class="container d-flex flex-column h-100">
        <div class="jumbotron mt-4 pb-4 text-center">
            <h1 class="display-4 text-danger">Oops.. An error occured! </h1>

            <p class="lead">Request from URI is failed: ${requestURI} </p>
            <p class="lead">Servlet name: ${servletName}</p>
            <p class="lead">Status code: ${statusCode}</p>
            <p class="lead">
                <c:if test="${not empty exceptionName and exceptionName ne 'null'}">
                    Exception: ${exceptionName}
                </c:if>
            </p>
            <p class="lead">
                <c:if test="${not empty exceptionMessage and exceptionMessage ne 'null'}">
                    Message: ${exceptionMessage}
                </c:if>
            </p>
            <hr class="my-4">
            <a class="btn btn-primary font-weight-bold" href="<c:url value="${pageContext.request.contextPath}/"/>" role="button">
                Home page
            </a>
        </div>
    </div>
</body>
</html>
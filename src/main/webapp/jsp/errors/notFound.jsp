<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Access denied</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col d-flex justify-content-center">
            <h1>Oops!</h1>
        </div>
    </div>
    <div class="row">
        <div class="col d-flex justify-content-center">
            <img class="img-fluid" src="${pageContext.request.contextPath}/assets/img/error_not_found.jpg" alt=""/>
        </div>
    </div>
</div>
</body>
</html>
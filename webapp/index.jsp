<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap/bootstrap.min.css"/>">
    <script src="<c:url value="/jquery/jquery.js"/>"></script>
    <script src="<c:url value="/js/bootstrap/popper.js"/>"></script>
    <script src="<c:url value="/js/bootstrap/bootstrap.min.js"/>"></script>
</head>

<body>
    <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>

    <!--MAIN CONTENT-->
    <div class="container-fluid">
        Main content
    </div>
    <!--MAIN CONTENT-->

    <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>

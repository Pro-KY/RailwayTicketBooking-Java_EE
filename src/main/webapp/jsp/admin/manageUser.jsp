<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<html>
<head>
    <title>Manage user</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <!--MAIN CONTENT-->
    <div class="container float-left ml-3">
        <form name="userCredentials" method="POST" action ="${pageContext.request.contextPath}/booking/">
            <input type="hidden" name="command" value="${Commands.UPDATE_USER}">
            <div class="row">
                <div class="col-md-9">
                    <table class="table">
                        <tr class="table-row">
                            <td><fmt:message key="user.id" bundle="${rb}"/> <fmt:message key="user.lastName" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${model.id}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.firstName" bundle="${rb}"/> <fmt:message key="user.lastName" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${model.firstName}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.lastName" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${model.lastName}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.email" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${model.email}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.password" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${model.password}"></td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-2"><button class="btn btn-primary" type="submit"> Update </button></div>
                <div class="col-md-2"><a class="btn btn-danger" href="${pageContext.request.contextPath}/booking/?${Parameters.USER_ID}=${model.id}&command=${Commands.DELETE_USER}" role="button">Delete</a></div>
                <div class="col-md-8"></div>
            </div>
        </form>
    </div>
    <!--MAIN CONTENT-->

    <div class="container-fluid row">
        <!-- ALERT -->
        <c:if test="${(not empty alertError and alertError eq true) or
             (not empty alertSuccess and alertSuccess eq true)}">
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

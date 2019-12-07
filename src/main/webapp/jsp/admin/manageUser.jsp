<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>
<c:set var="userDto" value="${sessionScope.userDto}" scope="session"/>

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
    <div class="container float-left ml-3 mt-5 pt-4">
        <form name="userCredentials" method="POST" action ="${pageContext.request.contextPath}/booking/">
            <input type="hidden" name="command" value="${Commands.UPDATE_USER}">
            <input type="hidden" name="${Parameters.ID}" value="${userDto.id}">

            <div class="row">
                <div class="col-md-9">
                    <table class="table">
                        <tr class="table-row">
                            <td><fmt:message key="user.id" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${userDto.id}" disabled ></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.firstName" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${userDto.firstName}" name="${Parameters.USER_FIRST_NAME}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.lastName" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${userDto.lastName}" name="${Parameters.USER_LAST_NAME}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.email" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${userDto.email}" name="${Parameters.EMAIL}"></td>
                        </tr>
                        <tr class="table-row">
                            <td><fmt:message key="user.password" bundle="${rb}"/></td>
                            <td><input type="text" class="form-control" value="${userDto.password}" name="${Parameters.PASSWORD}"></td>
                        </tr>
                    </table>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row">
                <div class="col-md-2"><button class="btn btn-primary" type="submit"> <fmt:message key="user.update" bundle="${rb}"/> </button></div>
                <div class="col-md-2"><a class="btn btn-danger" href="${pageContext.request.contextPath}/booking/?${Parameters.ID}=${userDto.id}&command=${Commands.DELETE_USER}" role="button"><fmt:message key="user.delete" bundle="${rb}"/></a></div>
                <div class="col-md-8"></div>
            </div>
        </form>
    </div>
    <!--MAIN CONTENT-->

    <div class="container-fluid row">
        <!-- FOOTER -->
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        <!-- FOOTER -->
    </div>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>

<c:remove var="userDto" scope="session" />

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<fmt:message var="firstName" key="user.firstName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="email" key="user.email" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="password" key="user.password" bundle="${sessionScope.rb}" scope="page"/>

<html>
<head>
    <title>Sign Up</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <div class="container pt-md-3">
        <div class="row justify-content-sm-center">
            <div class="col-sm-6 col-md-4">
                <div class="card border-info text-center">
                    <div class="card-body">
                        <form name="SignUpForm" method="POST" action ="booking/">
                            <input type="hidden" name="command" value="${Commands.SIGN_UP}">

                            <div class="row">
                                <input type="text" class="form-control m-1" placeholder="${firstName}" id="firstName" name="${Parameters.USER_FIRST_NAME}">
                            </div>
                            <div class="row">
                            </div>
                            <input type="text" class="form-control m-1" placeholder="${lastName}" name="${Parameters.USER_LAST_NAME}">
                            <div class="row">
                                <input type="text" class="form-control m-1" placeholder="${email}" name="${Parameters.EMAIL}">
                            </div>
                            <div class="row">
                                <input type="password" class="form-control m-1" placeholder="${password}" name="${Parameters.PASSWORD}">
                            </div>

                            <button type="submit" class="btn btn-primary"><fmt:message key="sing.up" bundle="${rb}"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <!-- FOOTER -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>




<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<fmt:message var="firstName" key="user.firstName" bundle="${rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${rb}" scope="page"/>
<fmt:message var="email" key="user.email" bundle="${rb}" scope="page"/>
<fmt:message var="password" key="user.password" bundle="${rb}" scope="page"/>

<html>
<head>
    <title>Sign Up</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <div class="container pt-md-3 mt-5">
        <div class="row justify-content-center">
            <div class="col-sm-6 col-md-4">
                <div class="card border-info">
                    <div class="card-body">
                        <form name="SignUpForm" method="POST" action ="${pageContext.request.contextPath}/booking/">
                            <input type="hidden" name="command" value="${Commands.SIGN_UP}">

                            <div class="form-group">
                                <label class="ml-3" for="firstName">${firstName}</label>
                                <input type="text" required class="form-control m-1" placeholder="${firstName}" id="firstName" name="${Parameters.USER_FIRST_NAME}">
                            </div>
                            <div class=" form-group">
                                <label class="ml-3" for="lastName">${lastName}</label>
                                <input type="text" required class="form-control m-1" placeholder="${lastName}" id="lastName" name="${Parameters.USER_LAST_NAME}">
                            </div>
                            <div class=" form-group">
                                <label class="ml-3" for="email">${email}</label>
                                <input type="text" required class="form-control m-1" placeholder="${email}" id="email" name="${Parameters.EMAIL}">
                            </div>
                            <div class=" form-group">
                                <label class="ml-3" for="password">${password}</label>
                                <input type="password" required class="form-control m-1" placeholder="${password}" id="password" name="${Parameters.PASSWORD}">
                            </div>

                            <div class="row justify-content-center">
                                <button type="submit" class="btn btn-primary"><fmt:message key="sing.up" bundle="${rb}"/></button>
                            </div>
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




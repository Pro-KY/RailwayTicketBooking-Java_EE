<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/utilImports.jspf" %>

<fmt:message var="firstName" key="user.firstName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="email" key="user.email" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="password" key="user.password" bundle="${sessionScope.rb}" scope="page"/>

<html>
<head>
    <title>Sign In</title>
    <%@ include file="/WEB-INF/jspf/headImports.jspf" %>
</head>

<body>
    <!-- HEADER -->
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- HEADER -->

    <div class="container pt-md-3">
        <div class="row justify-content-sm-center">
            <div class="col-sm-6 col-md-4">
                <div class="card border-info">
                    <div class="card-body">
                        <form name="signIn" method="POST" action ="${pageContext.request.contextPath}/booking/">
                            <input type="hidden" name="command" value="${Commands.SIGN_IN}">

                            <div class="form-group">
                                <label for="inputEmail">Email</label>
                                <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="${Parameters.EMAIL}">
                            </div>

                            <div class="form-group">
                                <label for="inputPassword">Password</label>
                                <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="${Parameters.PASSWORD}">
                            </div>

                            <button type="submit" class="btn btn-primary col-12" data-dismiss="modal">Enter</button>
                        </form>

                        <div><fmt:message key="msg.signup" bundle="${rb}"/> <a href="${pageContext.request.contextPath}/jsp/signUp.jsp"><fmt:message key="signup" bundle="${rb}"/></a></div>
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




<form name="SignUpForm" method="POST" action ="${pageContext.request.contextPath}/booking/">
    <input type="hidden" name="command" value="${Commands.SIGN_UP}">

    <div id = "fistLastNamesWrapper" class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="fistName"><fmt:message key="signup.user.firstName" bundle="${rb}"/></label>
                <input type="text" class="form-control" id="fistName" name="${Parameters.USER_FIRST_NAME}">
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="lastName"><fmt:message key="signup.user.lastName" bundle="${rb}"/></label>
                <input type="text" class="form-control" id="lastName" name="${Parameters.USER_LAST_NAME}">
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="email"><fmt:message key="signin.email" bundle="${rb}"/></label>
                <input type="text" class="form-control" id="email" name="${Parameters.EMAIL}">
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="password"><fmt:message key="signin.password" bundle="${rb}"/></label>
                <input type="password" class="form-control" id="password" name="${Parameters.PASSWORD}">
            </div>
        </div>
    </div>

    <button type="submit" class="btn btn-primary"><fmt:message key="sendreport.form.button.submit" bundle="${rb}"/></button>
</form>
<%@ page contentType="text/html;charset=UTF-8"%>

<fmt:message var="firstName" key="user.firstName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="lastName" key="user.lastName" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="email" key="email" bundle="${sessionScope.rb}" scope="page"/>
<fmt:message var="password" key="password" bundle="${sessionScope.rb}" scope="page"/>

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
                            <input type="text" class="form-control m-1" placeholder="${lastName}" name="${Parameters.USER_LAST_NAME}">
                        </div>
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



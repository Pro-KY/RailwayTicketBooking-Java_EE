<c:set var="success" value="${not empty alertSuccess and alertSuccess eq true}"/>

<%--<div class="container-fluid mb-3">--%>
    <div class="container-fluid mb-3 row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="alert ${success ? "alert-success" : "alert-danger"} alert-dismissible fade show" type="alert">
                <c:choose>
                    <c:when test="${not empty alertMessage}">
                        ${alertMessage}
                    </c:when>
                    <c:otherwise>
                        ${success ? alertSuccessMsg : alertErrorMsg}
                    </c:otherwise>
                </c:choose>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
<%--</div>--%>




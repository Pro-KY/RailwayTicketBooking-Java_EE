<div class="container-fluid">
    Main content
    ${param.errMsg}


    ${not empty sessionScope.validation}
    <c:forEach var="entry" items="${sessionScope.validation.errorMessages}">
        <tr><td><c:out value="${entry.key}"/></td> <td><c:out value="${entry.value}"/> </td></tr>
    </c:forEach>
</div>

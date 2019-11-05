<div class="container-fluid p-2 fixed-bottom" id="footer">
<%--    if pagination --%>
    <div class="row">
        <div class="col-4 d-flex justify-content-start">Railway Ticket Booking, 2019</div>
        <div class="col-4"></div>
        <div class="col-4 d-flex justify-content-end" >
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-item nav-link dropdown-toggle" href="#" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="footer.language" bundle="${rb}"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="booking/?command=${Commands.CHANGE_LANGUAGE}&language=ua"><span class="flag-icon flag-icon-ua"> </span> Ukrainian</a>
                        <a class="dropdown-item" href="booking/?command=${Commands.CHANGE_LANGUAGE}&language=en"><span class="flag-icon flag-icon-us"> </span> English</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

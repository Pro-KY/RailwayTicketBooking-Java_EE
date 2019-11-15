<div class="container-fluid p-2 fixed-bottom" id="footer">
<%--    if pagination --%>
    <div class="row">
        <div class="col-4 d-flex justify-content-start">Railway Ticket Booking, 2019</div>
        <div class="col-4"></div>
        <div class="col-4 d-flex justify-content-end" >
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        <i class="fa fa-language" aria-hidden="true"></i>
                        ${language eq 'en' ? 'EN' : 'RU'}
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="booking/?command=${Commands.CHANGE_LANGUAGE}&language=en"> EN </a>
                        <a class="dropdown-item" href="booking/?command=${Commands.CHANGE_LANGUAGE}&language=ru"> RU </a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

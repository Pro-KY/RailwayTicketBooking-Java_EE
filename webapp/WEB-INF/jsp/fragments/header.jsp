
<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand" href="#">LOGO</a>
        </div>


<%--        <a class="navbar-brand container-fluid" href="#">Logo</a>--%>
        <span class="navbar-text mr-4">Hello, guest! </span>
        <button type="button"  class="btn btn-link" data-toggle="modal" data-target="#myModal">Authorize</button>
    </nav>
</div>

<!-- The Modal -->
<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Authorize</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label for="inputEmail">Email</label>
                        <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                    </div>

                    <div class="form-group">
                        <label for="inputPassword">Password</label>
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                    </div>
                </form>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer justify-content-center align-items-center">
                <div class="row">
                    <button type="button" class="btn btn-primary col-12" data-dismiss="modal">Enter</button>
                    <div class="col-12 pt-2 pl-5 mr-md-n4">
                        <a href="#">Sign up</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

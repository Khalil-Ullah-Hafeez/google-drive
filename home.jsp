<%@ page session = "false" %>
<jsp:include page = "/layout/header.jsp" />

<% 
    HttpSession ns = request.getSession( false );
    if ( ns == null ) {
        response.sendRedirect( "index.jsp" );
    }
%>

<div class="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="logo">
                    <img src="assets/images/logo.png" alt="logo">
                </div>
            </div>
            <div class="col-lg-3">
                <input type="text" name="search" placeholder="Search">
            </div>
            <div class="col-lg-6">
                <a href="Logout">Logout</a>
            </div>
        </div>
    </div>
</div>

<div class="main-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <form class="file-upload-form">
                    <input type="file" name="file" />
                    <input type="submit" value="upload file" />
                </form>
            </div>
            <div class="col-lg-6">
                <div class="files-content">

                </div>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/download.js"></script>
<script src="assets/js/main.js"></script>

<jsp:include page = "/layout/footer.jsp" />
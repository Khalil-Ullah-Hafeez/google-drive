<%@ page session = "false" %>
<%@ page import = "drive.Beans.UserBean" %>
<jsp:include page = "/layout/header.jsp" />

<% 
    HttpSession ns = request.getSession( false );
    if ( ns == null ) {
        response.sendRedirect( "login.jsp" );
        return;
    }
    
    UserBean user = (UserBean) ns.getAttribute( "user" );
%>

<jsp:include page = "/layout/navbar.jsp" />

<div class="main-wrapper">
    <% if ( ns != null && !user.isEmail_verified() ) { %>
    <div class="email-verfication">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">
                          Email Verfication
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">Your email address is not verified</h5>
                            <p class="card-text">You click the button below and we will send you an email for verification.</p>
                            <a href="#" class="btn btn-outline-secondary anchor-email-verify">
                                Send Verification Email
                                <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                            </a>
                        </div>
                      </div>
                </div>
            </div>
        </div>
    </div>
    <% } %>
    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <form class="file-upload-form">
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Upload File</label>
                        <input class="form-control" type="file" id="formFile" name="file" multiple required>
                        <input type="hidden" name="action" value="FileUpload">
                    </div>
                    <button type="submit" class="btn btn-outline-dark">Upload</button>
                </form>
            </div>
            <div class="col-lg-8">
                <div class="files-content">

                </div>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/main.js"></script>

<jsp:include page = "/layout/footer.jsp" />
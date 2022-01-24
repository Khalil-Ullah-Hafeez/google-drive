<%@ page session = "false" %>

<% 
    HttpSession ns = request.getSession( false );
    if ( ns != null ) {
        response.sendRedirect( "index.jsp" );
    }
%>


<jsp:include page = "/layout/header.jsp" />

<div class="wrapper">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 offset-lg-3">
                <div class="heading">
                    <h1>Sign in</h1>
                </div>
                <form class="login-form form" novalidate>
                    <div class='main-error login-main-error'></div>

                    <div class="form-floating mb-3">
                        <input
                        type="text"
                        class="form-control"
                        id="floatingInput"
                        name="email"
                        placeholder="Email address"
                        >
                        <label for="floatingInput">Email address</label>
                        <div class='error-div login-email-error'></div>
                    </div>

                    

                    <div class="form-floating mb-3">
                        <input
                        type="password"
                        class="form-control"
                        id="floatingPassword"
                        name="password"
                        placeholder="Password"
                        >
                        <label for="floatingPassword">Password</label>
                        <div class='error-div login-password-error'></div>
                    </div>
                    
                    <input type="hidden" name="action" value="Login">

                    <button type="submit" class="col-lg-12 btn btn-outline-primary mb-3">Sign In</button>
                </form>
                <div class="other-link">
                    <p>OR</p>
                    <a href="signup.jsp" class="btn btn-outline-dark">Signup</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/main.js"></script>
<script src='assets/js/login.js'></script>

<jsp:include page = "/layout/footer.jsp" />
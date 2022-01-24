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
                    <h1>Sign up</h1>
                </div>
                <form class="signup-form form" novalidate>
                    <div class='main-error signup-main-error'></div>

                    <div class="form-floating mb-3">
                        <input
                        type="text"
                        class="form-control"
                        id="floatingInput"
                        name="first_name"
                        placeholder="First Name"
                        >
                        <label for="floatingInput">First Name</label>
                        <div class='error-div signup-first_name-error'></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                        type="text"
                        class="form-control"
                        id="last_name"
                        name="last_name"
                        placeholder="Last Name"
                        >
                        <label for="last_name">Last Name</label>
                        <div class='error-div signup-last_name-error'></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                        type="email"
                        class="form-control"
                        id="email"
                        name="email"
                        placeholder="Email Address"
                        >
                        <label for="email">Email Address</label>
                        <div class='error-div signup-email-error'></div>
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
                        <div class='error-div signup-password-error'></div>
                    </div>

                    <div class="form-floating mb-3">
                        <input
                        type="password"
                        class="form-control"
                        id="confirm_password"
                        name="confirm_password"
                        placeholder="Confirm Password"
                        >
                        <label for="confirm_password">Confirm Password</label>
                        <div class='error-div signup-confirm_password-error'></div>
                    </div>

                    <div class="radio-group">
                        <div class="form-check">
                            <input 
                            class="form-check-input" 
                            type="radio" 
                            name="gender" 
                            id="gender_male">
                            <label class="form-check-label" for="gender_male">
                                Male
                            </label>
                        </div>

                        <div class="form-check">
                            <input 
                            class="form-check-input" 
                            type="radio" 
                            name="gender" 
                            id="gender_female">
                            <label class="form-check-label" for="gender_female">
                                Female
                            </label>
                        </div>

                    </div>
                    <div class='error-div signup-gender-error mb-3'></div>

                    <input type="hidden" name="action" value="Signup">
                    
                    <button type="submit" class="col-lg-12 btn btn-outline-primary mb-3">Sign Up</button>
                </form>
                <div class="other-link">
                    <p>OR</p>
                    <a href="login.jsp" class="btn btn-outline-dark">Login</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/main.js"></script>
<script src='assets/js/signup.js'></script>

<jsp:include page = "/layout/footer.jsp" />
<%@ page session = "false" %>
<%@ page import = "drive.Beans.UserBean" %>
<% 
    HttpSession ns = request.getSession( false );
    if ( ns == null ) {
        response.sendRedirect( "login.jsp" );
        return;
    }
    UserBean user = (UserBean) ns.getAttribute( "user" );
%>
<jsp:include page = "/layout/header.jsp" />

<jsp:include page = "/layout/navbar.jsp" />

<div class="main-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                <div class="d-flex align-items-start">
                    <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                      <button class="nav-link active" id="v-pills-home-tab" data-bs-toggle="pill" data-bs-target="#v-pills-home" type="button" role="tab" aria-controls="v-pills-home" aria-selected="true">Change Information</button>
                      <button class="nav-link" id="v-pills-profile-tab" data-bs-toggle="pill" data-bs-target="#v-pills-profile" type="button" role="tab" aria-controls="v-pills-profile" aria-selected="false">Delete Account</button>
                      <button class="nav-link" id="v-pills-messages-tab" data-bs-toggle="pill" data-bs-target="#v-pills-messages" type="button" role="tab" aria-controls="v-pills-messages" aria-selected="false">Messages</button>
                      <button class="nav-link" id="v-pills-settings-tab" data-bs-toggle="pill" data-bs-target="#v-pills-settings" type="button" role="tab" aria-controls="v-pills-settings" aria-selected="false">Settings</button>
                    </div>
                    <div class="tab-content" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
                            <div class="content">
                                <h3>Enter the new Email below</h3>

                                <form class="signup-form update-form form" novalidate>
                                    <div class='main-error signup-main-error'></div>
            
                                    <div class="form-floating mb-3">
                                        <input
                                        type="text"
                                        class="form-control"
                                        id="floatingInput"
                                        name="first_name"
                                        placeholder="First Name"
                                        value="<%= user.getFirst_name() %>"
                                        required
                                        disabled
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
                                        value="<%= user.getLast_name() %>"
                                        required
                                        disabled
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
                                        value="<%= user.getEmail() %>"
                                        required
                                        disabled
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
                                        required
                                        disabled
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
                                        required
                                        disabled
                                        >
                                        <label for="confirm_password">Confirm Password</label>
                                        <div class='error-div signup-confirm_password-error'></div>
                                    </div>
            
                                    <div class="radio-group mb-3">
                                        <div class="form-check">
                                            <input 
                                            class="form-check-input" 
                                            type="radio" 
                                            name="gender" 
                                            id="gender_male"
                                            value="male"
                                            <%= ( user.getGender() ).equals( "male" ) ? "checked" : "" %>
                                            disabled>
                                            <label class="form-check-label" for="gender_male">
                                                Male
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input 
                                            class="form-check-input" 
                                            type="radio" 
                                            name="gender" 
                                            id="gender_female"
                                            value="female"
                                            <%= ( user.getGender() ).equals( "female" ) ? "checked" : "" %>
                                            disabled>
                                            <label class="form-check-label" for="gender_female">
                                                Female
                                            </label>
                                        </div>
                                    </div>
            
                                    <input type="hidden" name="action" value="UpdateData">
                                
                                    <div class="row">
                                        <button type="button" class="col-lg-6 btn btn-outline-secondary unlock-button">Unlock</button>
                                        <button type="submit" class="col-lg-6 btn btn-outline-primary">Update</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
                            <div class="content">
                                <h3>Click the button below to terminate account.</h3>
                                <button 
                                type="button" 
                                class="btn btn-primary" 
                                data-bs-toggle="modal" 
                                data-bs-target="#exampleModal">
                                    Delete account
                                </button>
                            </div>
                        </div>
                      <div class="tab-pane fade" id="v-pills-messages" role="tabpanel" aria-labelledby="v-pills-messages-tab">...</div>
                      <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">...</div>
                    </div>
                  </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete your account?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                <button type="button" class="delete-button btn btn-primary">Yes</button>
            </div>
        </div>
    </div>
</div>

<script src="assets/js/signup.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/main.js"></script>

<jsp:include page = "/layout/footer.jsp" />
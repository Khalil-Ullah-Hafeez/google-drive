<%@ page session = "false" %>

<jsp:include page = "/layout/header.jsp" />

<%= request.getSession( false ) %>

<div class="main-container-wrapper">
	<div class="main-container" id="container">
		<div class="form-container sign-up-container">
			<form class="signup-form form">
				<h1>Create Account</h1>
				<div class='signup-main-error'></div>
	
				<input type="text" name="first_name" placeholder="Name" />
				<div class='signup-first_name-error'></div>
	
				<input type="text" name="last_name" placeholder="Name" />
				<div class='signup-last_name-error'></div>
	
				<input type="email" name="email" placeholder="Email" />
				<div class='signup-email-error'></div>
	
				<input type="password" name="password" placeholder="Password" />
				<div class='signup-password-error'></div>
	
				<input type="password" name="confirm_password" placeholder="confirm password" />
				<div class='signup-confirm_password-error'></div>
	
				<input id="gender_male" type="radio" name="gender" value="male" />
				<label for="gender_male">Male</label>
				<input id="gender_female" type="radio" name="gender" value="female" />
				<label for="gender_female">Female</label>
	
				<button>Sign Up</button>
			</form>
		</div>
		<div class="form-container sign-in-container">
			<form class="login-form form">
				<h1>Sign in</h1>
				<div class='login-main-error'></div>
				<input type="email" name="email" placeholder="Email" />
				<div class='login-email-error'></div>
				<input type="password" name="password" placeholder="Password" />
				<div class='login-password-error'></div>
				<button type="submit">Sign In</button>
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Welcome Back!</h1>
					<p>To keep connected with us please login with your personal info</p>
					<button class="ghost" id="signIn">Sign In</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Hello, Friend!</h1>
					<p>Enter your personal details and start journey with us</p>
					<button class="ghost" id="signUp">Sign Up</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script src='assets/js/login.js'></script>

<jsp:include page = "/layout/footer.jsp" />


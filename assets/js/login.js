
// resets the error's contents
const reset_login_errors = () => {
    document.querySelector(`.login-email-error`).innerHTML = '';
    document.querySelector(`.login-password-error`).innerHTML = '';
}

// displays login errors on the page
const show_login_errors = (data_json) => {
    data_json.forEach((error) => {
        document.querySelector(`.login-${error.name}-error`).innerHTML = error.message;
    });
}

// performs client-side validation
const validate_login_form = (form) => {
    let errors = [];

    let email = form.querySelector('input[name="email"]');
    let password = form.querySelector('input[name="password"]');

    // check if email is empty
    if ( email.value === '' ) {
        errors.push({
            name: 'email',
            message: 'email can\'t be empty'
        });
    }
    // check if email is valid
    else if ( !validate_email_address(email.value) ) {
        errors.push({
            name: 'email',
            message: 'email is not valid'
        });
    }

    // check if password is empty
    if ( password.value === '' ) {
        errors.push({
            name: 'password',
            message: 'password can\'t be empty'
        });
    }
    // check if password has less than 8 length
    else if ( password.value.length < 8 ) {
        errors.push({
            name: 'password',
            message: 'password cannot have less than 8 characters'
        });
    }

    return errors;
}

// handle the submission of the form
document.querySelector('.login-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    // reset the login error div contents
    reset_login_errors();

    // perform client side validation
    let errors = validate_login_form(e.target);
    if ( errors.length != 0 ) {
        show_login_errors(errors);
        return;
    }

    // create FormData
    const data = new FormData(e.target);

    // send request to server
    let response = await fetch('Controller', {
        method: 'POST',
        body: data,
        'Content-Type': 'multipart/form-data'
    });

    // show errors if credentials are wrong
    if ( response.status == 401 ) {
        let data_text = await response.text();
        // shows errors with the corresponding input fields
        let element = document.querySelector(`.login-main-error`);
        element.innerHTML = data_text;
        return;
    }

    // show errors if the input was invalid
    if ( response.status == 400 ) {
        let data_json = await response.json();
        // shows errors with the corresponding input fields
        show_login_errors(data_json);
        return;
    }

    // redirect to the url in the response
    window.location.href = response.url;

});
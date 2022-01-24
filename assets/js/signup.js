// resets the error's contents
const reset_signup_errors = () => {
    document.querySelectorAll('.error-div').forEach((error) => {
        error.innerHTML = '';
    });
}

// displays signup errors on the page
const show_signup_errors = (data_json) => {
    data_json.forEach((error) => {
        document.querySelector(`.signup-${error.name}-error`).innerHTML += ' ' + error.message;
    });
}

// handle the submission of the form
// performs client-side validation
const validate_login_form = (form) => {
    let errors = [];

    let first_name = form.querySelector('input[name="first_name"]');
    let last_name = form.querySelector('input[name="last_name"]');
    let email = form.querySelector('input[name="email"]');
    let password = form.querySelector('input[name="password"]');
    let confirm_password = form.querySelector('input[name="confirm_password"]');
    let gender = form.querySelector('input[type="radio"]:checked');

    if ( first_name.value == '' ) {
        errors.push({
            name: 'first_name',
            message: 'First name can\'t be empty'
        });
    }

    if ( last_name.value == '' ) {
        errors.push({
            name: 'last_name',
            message: 'Last name can\'t be empty'
        });
    }

    if ( email.value == '' ) {
        errors.push({
            name: 'email',
            message: 'Email can\'t be empty'
        });
    } else if ( !validate_email_address(email.value) ) {
        errors.push({
            name: 'email',
            message: 'email is not valid'
        });
    }

    if ( password.value == '' ) {
        errors.push({
            name: 'password',
            message: 'Password can\'t be empty'
        });
    } else if ( password.value.length < 8 ) {
        errors.push({
            name: 'password',
            message: 'password cannot have less than 8 characters'
        });
    }

    if ( confirm_password.value == '' ) {
        errors.push({
            name: 'confirm_password',
            message: 'Confirm password can\'t be empty'
        });
    } else if ( confirm_password.value.length < 8 ) {
        errors.push({
            name: 'confirm_password',
            message: 'password cannot have less than 8 characters'
        });
    }

    if ( 
        password.value != '' && 
        password.value.length >= 8 &&
        confirm_password.value != '' && 
        confirm_password.value.length >= 8 &&
        password.value !== confirm_password.value ) {
        errors.push({
            name: 'confirm_password',
            message: 'Confirm password does not match the password'
        });
    }

    if ( gender == null || gender == undefined ) {
        errors.push({
            name: 'gender',
            message: 'Gender has to be selected'
        });
    }

    return errors;
};

document.querySelector('.signup-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = new FormData(e.target);

    // reset the login error div contents
    reset_signup_errors();

    // perform client side validation
    let errors = validate_login_form(e.target);
    if ( errors.length != 0 ) {
        show_signup_errors(errors);
        return;
    }

    let response = await fetch('Controller', {
        method: 'POST',
        body: data,
        'Content-Type': 'multipart/form-data'
    });

    // show errors if credentials are wrong
    if ( response.status == 401 ) {
        let data_text = await response.text();
        // shows errors with the corresponding input fields
        let element = document.querySelector(`.signup-main-error`);
        element.innerHTML = data_text;
        return;
    }

    // show errors if the input was invalid
    if ( response.status == 400 ) {
        let data_json = await response.json();
        // shows errors with the corresponding input fields
        show_signup_errors(data_json);
        return;
    }

    // redirect to the url in the response
    window.location.href = await response.text();

});

window.addEventListener('load', (e) => {
    let button = document.querySelector('.unlock-button');
    if ( button == null || button == undefined ) return;
    button.addEventListener('click', (e) => {
        let elements = document.querySelectorAll('.update-form input');
    
        if ( elements.length < 1 ) return;
    
        let value = e.target.innerHTML;
        console.log( value === 'Unlock' );
        if ( value === 'Unlock' ) {
            e.target.innerHTML = 'Lock';
        } else {
            e.target.innerHTML = 'Unlock';
        }
    
        elements.forEach((element) => {
            if ( element.type == 'hidden' ) return;
            element.toggleAttribute( 'disabled' );
        })
    })
})
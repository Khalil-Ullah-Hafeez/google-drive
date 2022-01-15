
// handle the submission of the form
document.querySelector('.login-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = new FormData(e.target);

    let response = await fetch('Login', {
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
        data_json.forEach((error) => {
            // console.log( document.querySelector(`.${error.name}-error`) );
            document.querySelector(`.login-${error.name}-error`).innerHTML = error.message;
        });
        return;
    }

    // redirect to the url in the response
    window.location.href = response.url;

});

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});
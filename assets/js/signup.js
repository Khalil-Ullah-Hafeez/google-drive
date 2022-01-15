
// handle the submission of the form
document.querySelector('.signup-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const data = new FormData(e.target);

    console.log( data.values() );

    let response = await fetch('Signup', {
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
        data_json.forEach((error) => {
            // console.log( document.querySelector(`.${error.name}-error`) );
            document.querySelector(`.signup-${error.name}-error`).innerHTML = error.message;
        });
        return;
    }

    // redirect to the url in the response
    // window.location.href = response.url;
    console.log( response );

});
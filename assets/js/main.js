// returns true if email address is validated
const validate_email_address = (emailString) => {
    let atSymbol = emailString.indexOf("@");
    if( atSymbol < 1 ) return false;
    
    let dot = emailString.lastIndexOf(".");
    if( dot <= atSymbol + 2 ) return false;
    
    if ( dot === emailString.length - 1 ) return false;
    
    return true;
}

const show_toast = (message, isError = false) => {
    let style = null;

    if ( isError ) {
        style = {
            background: "red"
        };
    };

    Toastify({
        duration: 3000,
        newWindow: true,
        close: false,
        gravity: "bottom",
        position: "left",
        stopOnFocus: true,
        text: message,
        style: style
    }).showToast();
}

const create_file_element = (file) => {
    let container =  document.createElement('div');
    container.className = 'single-file';

    let wrapper =  document.createElement('div');
    wrapper.className = 'single-file-wrapper';

    let icon = document.createElement('i');
    icon.className = 'las la-file-alt';

    let h4 = document.createElement('h4');
    h4.innerHTML = file.name;

    let download = document.createElement('div');
    download.dataset.file = file.name;
    download.className = 'file-link download btn btn-success';
    download.innerHTML = 'Download';

    let deletefile = document.createElement('div');
    deletefile.dataset.file = file.name;
    deletefile.className = 'file-link delete btn btn-danger';
    deletefile.innerHTML = 'Delete';

    let anchor_wrapper = document.createElement('div');
    anchor_wrapper.className = 'anchor-wrapper';
    
    anchor_wrapper.append( download );
    anchor_wrapper.append( deletefile );

    
    wrapper.append( icon );
    wrapper.append( h4 );

    container.append( wrapper );
    container.append( anchor_wrapper );
    
    return container;
};

const get_all_files = async () => {
    let container = document.querySelector('.files-content');

    if ( container == null || container == undefined ) return;

    let response = await fetch('ViewFile', {
        method: 'GET',
        credentials: 'same-origin'
    });

    container.innerHTML = '';

    // handle if there is no session
    if ( response.status === 401 ) {
        container.innerHTML = "There is an issue with your session";
        return;
    }

    // handle if there are no files
    if ( response.status === 404 ) {
        container.innerHTML = "There are no files";
        return;
    }

    // output the files
    let data = await response.json();
    
    data.forEach(element => {
        container.append( create_file_element( element ) );
    });

}

window.addEventListener( 'load', get_all_files);

const file_upload = async () => {
    let form = document.querySelector('.file-upload-form');
    if ( form == null || form == undefined ) return;
    form.addEventListener( 'submit', async (e) => {
        e.preventDefault();
        const data = new FormData(e.target);
    
        try {
    
            let response = await fetch('Controller', {
                method: 'POST',
                credentials: 'same-origin',
                body: data,
                'Content-Type': 'multipart/form-data'
            });
    
            let text = await response.text();
    
            if ( response.status != 200 ) {
                show_toast( text, true );
                return;
            }
            
            show_toast( text );
    
            await get_all_files();
    
        } catch (err) {
            show_toast( 'Upload was not successfull', true );
        }
    
    } );
}

const download = (blob, file_name) => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.style.display = 'none';
    a.href = url;
    a.download = file_name;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
}

const download_file = async (event) => {
    let file_name = event.target.dataset.file;
    try {
        let response = await fetch(`Controller?filename=${file_name}&action=DownloadFile`);
        let res_blob = await response.blob();
        download( res_blob, file_name );

        // notify that the download has been started
        show_toast( 'Download started' );

    } catch (err) {
        show_toast( 'Download could not be started', true );
    }
    
}

const delete_file = async (event) => {
    let file_name = event.target.dataset.file;
    try {
        let response = await fetch(`Controller?filename=${file_name}&action=DeleteFile`);

        if ( response.status !== 200 ) {
            let text = await response.text();
            show_toast( text, true );
            return;
        }

        await get_all_files();

        // notify that the file has been deleted
        show_toast( 'File deleted successfully.' );


    } catch (err) {
        show_toast( 'File could not be deleted successfully', true );
    }
}

const email_verify = async (e) => {
    e.preventDefault();

    let spinner = e.target.querySelector( '.spinner-border' );

    try {
        spinner.style.display = 'inline-block';

        let response = await fetch('Controller?action=Verification');
    
        if ( response.status !== 200 ) {
            console.log( response );
            let text = await response.text();
            show_toast( text, true );
            return;
        }

        spinner.style.display = 'none';

        // notify that the email has been sent
        show_toast( 'Email sent successfully.' );

    } catch (err) {
        show_toast( 'Email could not be sent', true );

        spinner.style.display = 'none';
    }

}

const delete_acount = async (e) => {
    try {
        let response = await fetch('Controller?action=DeleteAccount');
        if ( response.status != 200 ) {
            let text = await response.text();
            show_toast( text , true );
            return;
        }

        show_toast( 'User account deleted successfully' );
        show_toast( 'Redirecting to login page in 3 seconds' );

        setTimeout(() => {
            window.location.href = 'login.jsp';
        }, 3000);
        
    } catch (errr) {
        show_toast( 'User account could not be deleted', true );
    }
}

document.addEventListener( 'click', async (e) => {
    if (e.target.classList.contains('download'))
        await download_file(e);
    if (e.target.classList.contains('delete'))
        await delete_file(e);
    if (e.target.classList.contains('anchor-email-verify'))
        await email_verify(e);
    if (e.target.classList.contains('delete-button'))
        await delete_acount(e);
} );


window.addEventListener( 'load', () => {
    file_upload();
} )
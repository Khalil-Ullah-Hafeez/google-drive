
const create_file_element = (file) => {
    let container =  document.createElement('div');
    container.className = 'single-file';

    let wrapper =  document.createElement('div');
    wrapper.className = 'single-file-wrapper';

    let icon = document.createElement('i');
    icon.className = 'fas fa-file';

    let h4 = document.createElement('h4');
    h4.innerHTML = file.name;

    let download = document.createElement('div');
    download.dataset.file = file.name;
    download.className = 'file-link download';
    download.innerHTML = 'Download';

    let deletefile = document.createElement('div');
    deletefile.dataset.file = file.name;
    deletefile.className = 'file-link delete';
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

window.addEventListener( 'load', async () => {
    let response = await fetch('ViewFile', {
        method: 'GET',
        credentials: 'same-origin'
    });

    let container = document.querySelector('.files-content');

    // handle if there is no session
    if ( response.status === 401 ) {
        return;
    }

    // handle if there are no files
    if ( response.state === 404) {
        container.innerHTML = "There are no files";
    }

    // output the files
    let data = await response.json()
    
    data.forEach(element => {
        container.append( create_file_element( element ) );
    });

});

document.querySelector('.file-upload-form').addEventListener( 'submit', async (e) => {
    e.preventDefault();
    const data = new FormData(e.target);

    let response = await fetch('FileUpload', {
        method: 'POST',
        credentials: 'same-origin',
        body: data,
        'Content-Type': 'multipart/form-data'
    })

    let res_data = response.text();

    console.log( res_data );

} );


const download_file = async (event) => {
    let file_name = event.target.dataset.file;
    try {
        let response = await fetch(`DownloadFile?filename=${file_name}`);
        let res_blob = await response.blob();
        download( res_blob, file_name );
    } catch (err) {
        toastr.info('Are you the 6 fingered man?')
    }
    
}

Toastify({
    text: "This is a toast",
    duration: 3000,
    newWindow: true,
    close: true,
    gravity: "bottom", // `top` or `bottom`
    position: "left", // `left`, `center` or `right`
    stopOnFocus: true, // Prevents dismissing of toast on hover
}).showToast();
  

document.addEventListener( 'click', async (e) => {
    if (e.target.classList.contains('download'))
        await download_file(e);
} )



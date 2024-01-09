// document.addEventListener('DOMContentLoaded', function () {
//     // Call getDocuments() once the DOM is fully loaded
//     getDocuments();
// });

function searchDocuments() {
      // Get the search input element
    const searchInput = document.getElementById('searchInput');

    // Get the value from the search input
    const searchTerm = searchInput.value.trim();

    try {
        var url = 'http://localhost:8088/api/custom/getdocumentsByContentString/?contentString='+searchTerm;

            fetch(url)
                .then(response => response.json())
                .then(data => displayDocuments(data))
                .catch(error => console.error('Error fetching documents:', error));

    } catch (error) {
        console.error('Error:', error);
        alert('Error searching for documents: ' + error.message);
    }
}

function uploadDocument() {
    // Get the file input element
    const fileInput = document.getElementById('fileInput');

    // Make sure a file was selected
    if (fileInput.files.length === 0) {
        alert('Please select a file to upload.');
        return;
    }

    // Get the selected files from the input element
    const file = fileInput.files[0];

    // Create the request body with FormData to handle the multipart/form-data body
    const formData = new FormData();
    formData.append('document', file, file.name);

    fetch('http://localhost:8088/api/documents/post_document/', {
                    method: 'POST',
                    body: formData,
                })
                .then(response => response.json())
                .then(getDocuments)
                .catch(error => console.error('Error fetching documents:', error));

    // Clear the file input
    fileInput.value = '';

    getDocuments();
}

function getDocuments() {
    fetch('http://localhost:8088/api/custom/getdocuments/')
                .then(response => response.json())
                .then(data => displayDocuments(data))
                .catch(error => console.error('Error fetching documents:', error));
}

function displayDocuments(data) {
    const documentTableBody = document.getElementById('documentTableBody');
    documentTableBody.innerHTML = ''; // Clear previous results

    for (const documentId in data) {
        const document = data[documentId];
        const row = documentTableBody.insertRow();
        row.insertCell().textContent = document.id;
        row.insertCell().textContent = document.title;
        row.insertCell().textContent = new Date(document.created).toLocaleDateString();
    }
}

getDocuments();


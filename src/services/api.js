// api.js

// Function to make a GET request to the specified microservice
async function getData(url) {
    const response = await fetch(url);
    if(!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
    }
    return await response.json();
}

// Function to make a POST request to the specified microservice
async function postData(url, data) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    });
    if(!response.ok) {
        throw new Error('Network response was not ok ' + response.statusText);
    }
    return await response.json();
}

// Exporting the API functions
export { getData, postData };
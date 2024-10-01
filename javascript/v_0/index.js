const XMLHttpRequest = require('xhr2');
const { JSDOM } = require('jsdom');

function fetchData(url, success, error) {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", url, true);
    
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                success(xhr.responseText);
            } else {
                error(xhr.status, xhr.statusText);
            }
        }
    };

    xhr.send();
}

function parseData(response, type) {
    if (type === "json") {
        return JSON.parse(response);
    } else if (type === "xml") {
        const dom = new JSDOM();
        const parser = new dom.window.DOMParser();
        return parser.parseFromString(response, "application/xml");
    } else {
        return response;
    }
}

function processData(data) {
    for (let i = 0; i < data.length; i++) {
        if (data[i] && data[i].title) {
            console.log("Processing item: " + data[i].title);
        }
    }
}

// Example usage
const MOCK_URL = "https://example.com/api";
const URL = "https://jsonplaceholder.typicode.com/posts";
fetchData(URL, function(response) {
	const parsedData = parseData(response, "json");
    processData(parsedData);
}, function(status, statusText) {
    console.error("Error fetching data: " + status + " " + statusText);
});

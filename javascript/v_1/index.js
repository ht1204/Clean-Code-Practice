const XMLHttpRequest = require('xhr2');
const { JSDOM } = require('jsdom');


const parseDataFormatStrategy = {
    json: (response) => JSON.parse(response),
    xml: (response) => {
        const dom = new JSDOM();
        const parser = new dom.window.DOMParser();
        return parser.parseFromString(response, "application/xml");
    }
};

async function fetchData(url) {
    if(!url || typeof url !== "string") {
        throw new Error("Invalid URL");
    }

    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", url, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    resolve(xhr.responseText);
                } else {
                    reject(`Error: ${xhr.status} ${xhr.statusText}`);
                }
            }
        };

        xhr.send();
    });
}

function parseData(response, type) {
    if (!response || typeof response !== 'string') {
        throw new Error("Invalid response");
    }
    
    if (!type || typeof type !== 'string') {
        throw new Error("Invalid type");
    }

    const parse = parseDataFormatStrategy[type.toLowerCase()];

    if (!parse) {
        throw new Error(`Unsupported type: ${type}`);
    }

    return parse(response);
}


function processData(data) {
    if (!Array.isArray(data)) {
        throw new Error("Invalid data format: array expected");
    }

    for (let i = 0; i < data.length; i++) {
        if (data[i] && data[i].title) {
            console.log("Processing item: " + data[i].title);
        }
    }
}


async function main() {
    const URL = "https://jsonplaceholder.typicode.com/posts";

    fetchData(URL)
    .then(response => {
        const parsedData = parseData(response, "json");
        processData(parsedData);
    })
    .catch(error => {
        console.error("Error:", error);
    });
}

main();


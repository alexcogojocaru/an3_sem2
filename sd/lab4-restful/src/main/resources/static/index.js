function getRequestFunction() {
    const xmlHttpRequest = new XMLHttpRequest();

    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("getResponse").innerText = this.responseText;
        }
    }

    xmlHttpRequest.open("GET", "http://localhost:8080/agenda", true);
    xmlHttpRequest.send();
}

function postRequestFunction() {
    const xmlHttpRequest = new XMLHttpRequest();

    xmlHttpRequest.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("post success");
        }
    }

    // xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

    xmlHttpRequest.open("POST", "http://localhost:8080/agenda", true);
    xmlHttpRequest.send(JSON.stringify({
        "id": 30,
        "lastName": "C++",
        "firstName": "Boost",
        "telephoneNumber": "120"
    }));
}

document.getElementById("getRequest").addEventListener("click", getRequestFunction);
document.getElementById("postRequest").addEventListener("click", postRequestFunction);
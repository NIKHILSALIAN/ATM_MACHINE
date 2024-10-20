const baseUrl = 'http://localhost:8081/api/atm';

function createAccount() {
    const accountNumber = document.getElementById('createAccountNumber').value;
    const pin = document.getElementById('createPin').value;

    fetch(`${baseUrl}/createAccount`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ accountNumber, pin }),
    })
    .then(response => response.json())
    .then(data => displayResponse(data))
    .catch(error => displayResponse(error));
}

function login() {
    const accountNumber = document.getElementById('loginAccountNumber').value;
    const pin = document.getElementById('loginPin').value;

    fetch(`${baseUrl}/login?accountNumber=${accountNumber}&pin=${pin}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => displayResponse(data))
    .catch(error => displayResponse(error));
}

function getBalance() {
    const accountNumber = document.getElementById('loginAccountNumber').value;
    const pin = document.getElementById('loginPin').value;

    fetch(`${baseUrl}/checkBalance?accountNumber=${accountNumber}&pin=${pin}`)
    .then(response => response.json())
    .then(data => displayResponse(data))
    .catch(error => displayResponse(error));
}

function deposit() {
    const accountNumber = document.getElementById('depositAccountNumber').value;
    const amount = document.getElementById('depositAmount').value;

    fetch(`${baseUrl}/deposit?accountNumber=${accountNumber}&amount=${amount}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => displayResponse(data))
    .catch(error => displayResponse(error));
}

function withdraw() {
    const accountNumber = document.getElementById('withdrawAccountNumber').value;
    const amount = document.getElementById('withdrawAmount').value;

    fetch(`${baseUrl}/withdraw?accountNumber=${accountNumber}&amount=${amount}`, {
        method: 'POST'
    })
    .then(response => response.json())
    .then(data => displayResponse(data))
    .catch(error => displayResponse(error));
}

function displayResponse(data) {
    const responseDiv = document.getElementById('response');
    responseDiv.innerHTML = JSON.stringify(data);
}

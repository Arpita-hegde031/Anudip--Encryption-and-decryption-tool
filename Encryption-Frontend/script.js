const BASE_URL = "http://localhost:8081"; // Backend URL

/* ===================== LOGIN & REGISTER ===================== */
async function login() {
    const email = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    if (!email || !password) return showMessage("Enter email & password ❌","error");

    try {
        const res = await fetch(`${BASE_URL}/api/auth/login`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email, password})
        });

        const data = await res.text();
        if (res.ok) {
            localStorage.setItem("user", email);
            showMessage(data + " ✅", "success");
            setTimeout(() => window.location.href = "home.html", 500);
        } else {
            showMessage(data + " ❌", "error");
        }
    } catch {
        showMessage("Login failed ❌","error");
    }
}

async function register() {
    const email = document.getElementById("regUsername").value;
    const password = document.getElementById("regPassword").value;
    if (!email || !password) return showMessage("Enter email & password ❌","error");

    try {
        const res = await fetch(`${BASE_URL}/api/auth/register`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email, password})
        });

        const data = await res.text();
        if (res.ok) {
            showMessage(data + " ✅", "success");
            setTimeout(() => window.location.href = "index.html", 1000);
        } else {
            showMessage(data + " ❌", "error");
        }
    } catch {
        showMessage("Registration failed ❌","error");
    }
}

/* ===================== LOGOUT ===================== */
function logout() {
    localStorage.removeItem("user");
    window.location.href = "index.html";
}

/* ===================== SECTION SWITCH ===================== */
function showSection(id, event) {
    document.querySelectorAll(".section").forEach(sec => sec.classList.remove("active"));
    document.getElementById(id).classList.add("active");
    document.querySelectorAll(".sidebar button").forEach(btn => btn.classList.remove("active"));
    if (event) event.currentTarget.classList.add("active");
}

/* ===================== TEXT ENCRYPTION/DECRYPTION ===================== */
async function encrypt() {
    const text = document.getElementById("inputText").value;
    const method = document.getElementById("method").value;
    if (!text) return showMessage("Enter text first ❌", "error");
    try {
        const res = await fetch(`${BASE_URL}/api/crypto/encrypt`, {
            method:"POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({message:text, method})
        });
        const data = await res.json();
        document.getElementById("outputText").value = data.result;
        showMessage("Encryption successful ✅","success");
    } catch {
        showMessage("Encryption failed ❌","error");
    }
}

async function decrypt() {
    const text = document.getElementById("inputText").value;
    const method = document.getElementById("method").value;
    if (!text) return showMessage("Enter text first ❌", "error");
    try {
        const res = await fetch(`${BASE_URL}/api/crypto/decrypt`, {
            method:"POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({message:text, method})
        });
        const data = await res.json();
        document.getElementById("outputText").value = data.result;
        showMessage("Decryption successful ✅","success");
    } catch {
        showMessage("Decryption failed ❌","error");
    }
}

/* ===================== EMAIL ENCRYPTION/DECRYPTION ===================== */
async function encryptEmail() {
    const text = document.getElementById("emailInput").value;
    const method = document.getElementById("emailMethod").value;
    if (!text) return showMessage("Enter email first ❌", "error");
    try {
        const res = await fetch(`${BASE_URL}/api/crypto/encrypt`, {
            method:"POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({message:text, method})
        });
        const data = await res.json();
        document.getElementById("emailOutput").value = data.result;
        showMessage("Email encryption successful ✅","success");
    } catch {
        showMessage("Encryption failed ❌","error");
    }
}

async function decryptEmail() {
    const text = document.getElementById("emailInput").value;
    const method = document.getElementById("emailMethod").value;
    if (!text) return showMessage("Enter email first ❌", "error");
    try {
        const res = await fetch(`${BASE_URL}/api/crypto/decrypt`, {
            method:"POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({message:text, method})
        });
        const data = await res.json();
        document.getElementById("emailOutput").value = data.result;
        showMessage("Email decryption successful ✅","success");
    } catch {
        showMessage("Decryption failed ❌","error");
    }
}

/* ===================== FILE ENCRYPTION/DECRYPTION ===================== */
let fileBlob = null;

async function encryptFile() {
    const file = document.getElementById("fileInput").files[0];
    const method = document.getElementById("fileMethod").value;
    if (!file) return showMessage("Select a file first ❌","error");

    const formData = new FormData();
    formData.append("file", file);
    formData.append("method", method);

    try {
        const res = await fetch(`${BASE_URL}/api/file/encrypt`, {method:"POST", body:formData});
        fileBlob = await res.blob();
        showMessage("File encrypted ✅","success");
    } catch {
        showMessage("File encryption failed ❌","error");
    }
}

async function decryptFile() {
    const file = document.getElementById("fileInput").files[0];
    const method = document.getElementById("fileMethod").value;
    if (!file) return showMessage("Select a file first ❌","error");

    const formData = new FormData();
    formData.append("file", file);
    formData.append("method", method);

    try {
        const res = await fetch(`${BASE_URL}/api/file/decrypt`, {method:"POST", body:formData});
        fileBlob = await res.blob();
        showMessage("File decrypted ✅","success");
    } catch {
        showMessage("File decryption failed ❌","error");
    }
}

function downloadFile() {
    if (!fileBlob) return showMessage("Encrypt/Decrypt first ❌","error");
    const url = window.URL.createObjectURL(fileBlob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "result_file";
    a.click();
}

/* ===================== MESSAGE BOX ===================== */
function showMessage(msg, type) {
    let messageBox = document.getElementById("messageBox");
    if (!messageBox) {
        messageBox = document.createElement("div");
        messageBox.id = "messageBox";
        document.body.prepend(messageBox);
    }
    messageBox.innerText = msg;
    messageBox.style.backgroundColor = type==="success"?"#28a745":"#dc3545";
    messageBox.style.color="#fff";
    messageBox.style.padding="12px";
    messageBox.style.borderRadius="10px";
    messageBox.style.fontWeight="bold";
    messageBox.style.position="fixed";
    messageBox.style.top="20px";
    messageBox.style.left="50%";
    messageBox.style.transform="translateX(-50%)";
    messageBox.style.zIndex="1000";
    setTimeout(()=>messageBox.remove(), 3000);
}

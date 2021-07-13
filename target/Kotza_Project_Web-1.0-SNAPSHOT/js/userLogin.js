function validateInput() {
    let username = document.forms["patientLoginForm"]["username"].value;
    let password = document.forms["patientLoginForm"]["password"].value;

    if (username === "" || password === "") {
        alert("You must fill the username and password!!");
        return false;
    }

    return true;
}

function validateLogout() {
    return confirm("Are sure you want to logout?");
}
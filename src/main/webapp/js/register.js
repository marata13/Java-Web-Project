
function validateRegister() {
    let form = document.forms["registerForm"];

    if (form["patient_password"].value !== form["confirm_password"].value) {
        alert("The password is not the same!.");
        return false;
    }

    if (form["patient_password"].value === "" ||
        form["confirm_password"].value === "" ||
        form["patient_name"].value === "" ||
        form["patient_surname"].value === "" ||
        form["patient_amka"].value === "" ||
        form["patient_username"].value === "") {

        alert("You must fill all the fields!");
        return false;
    }

    return true;
}
function trySubmit() {
    let name = $("#name")[0]['value']
    let surname = $("#surname")[0]['value']
    let description = $("#vocation")[0]['value']
    let email = $("#email")[0]['value']
    let password = $("#password")[0]['value']

    let errors = []

    if (!name) {
        errors.push("name must not be null")
    }

    if (!surname) {
        errors.push("surname must not be null")
    }

    if (!description) {
        errors.push("description must not be null")
    }

    if (!email) {
        errors.push("email must not be null")
    }

    if (!password) {
        errors.push("password must not be null")
    } else if (password.length < 3 || password.length > 10) {
        errors.push("password length must be greater than 3 and less than 10")
    }

    if (errors.length !== 0) {
        alert(errors.reduce((acc, val) => acc + "\n" + val))
    } else {
        $("#d").first().submit()
    }

}
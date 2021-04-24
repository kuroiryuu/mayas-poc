$(() => {
    let validator = $('#signupForm, #loginForm').jbvalidator({
        errorMessage: true,
        successClass: true,
        language: '/js/en.json'
    });

    $('#loginForm').on('submit', function() {
        if (this.checkValidity() === false) {
            return false;
        }

        let loginData = {};
        $("input", this).each(function() {
            loginData[this.id.replace('login_', '')] = $(this).val();
        });

        $.ajax({
            method: 'post',
            contentType: 'application/json',
            dataType: 'json',
            url: '/api/login',
            data: JSON.stringify(loginData)
        }).done(function (data) {
            localStorage.setItem('token', data.token);
            window.location.assign('/home.html');
        }).fail(function (jqXHR) {
            validator.errorTrigger($('#login_password'), jqXHR.responseJSON.detail)
            console.log(jqXHR.responseJSON);
        });
        return false;
    });

    $('#signup_togglePassword, #signup_togglePasswordConfirm, #login_togglePassword, #login_togglePasswordConfirm').on('click', evt => {
        $("i", evt.currentTarget).text((i, value) => {
            let passwordField = $(`#${(evt.currentTarget.id.replace('toggleP', 'p'))}`);
            if (value.endsWith('_off')) {
                passwordField.attr('type', 'password');
                return value.replace('_off', '');
            }
            passwordField.attr('type', 'text');
            return value + '_off';
        });
    });
})

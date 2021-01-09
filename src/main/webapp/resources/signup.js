$(() => {
    $('#signup').on('submit', (evt) => {
        evt.preventDefault();
        var formData = {};
        $('#signup input').serializeArray().forEach(element => formData[element.name] = element.value);
        console.log(formData);
    });
});

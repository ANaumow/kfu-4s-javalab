function sendForm(action, formId) {

    let formData = new FormData();
    // забрал файл из input


    $("form#" + formId + " :input").each(function () {
        let input = $(this);

        console.log($("form#" + formId + " :input"))

        if (input[0]["type"] === "text") {
            formData.append(input[0]["name"], input[0]["value"])
        } else if (input[0]["type"] === "file"){
            let files = input[0]['files'];
            // добавляю файл в formData

            console.log("files");

            console.log(files);

            [].forEach.call(files, function(file) {
                console.log("working!")
                formData.append(input[0]["name"], file);
            });

        }

        console.log(formData)

        // This is the jquery object of the input, do what you will
    });

    console.log("sending ..")


    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    console.log(token + " " + header)

    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader(header, token);
        }
    });

    $.ajax({
        type: "POST",
        url: action,
        data: formData,
        processData: false,
        contentType: false,

        success: function (response) {
            window.location.href = "/profile";
            //var body = response; //(uploadIframe.contentWindow ? uploadIframe.contentWindow : uploadIframe.contentDocument).document.body;
           // var json = response//eval("(" + response + ")");//JSON.parse(text);//(body.innerText) ? body.innerText : ((body.textContent) ? body.textContent : null);
            //console.log(json);

            //json = (typeof JSON.parse !== "undefined") ? JSON.parse(json) : eval("(" + json + ")");
        }
    });
}

/*inputList.forEach(function (element) {
    let files = $('#image-input-id')[0]['files'];
    // добавляю файл в formData
    [].forEach.call(files, function (file, i, files) {
        console.log(file);
        formData.append("editormd_image_file", file);
    });
});*/


// console.log(settings.csrfHeader + " " + settings.csrfToken);

/*formData.append(settings.csrfHeader, settings.csrfToken);
*/

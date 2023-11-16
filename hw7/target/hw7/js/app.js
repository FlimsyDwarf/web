window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

ajax = function (data, $error, fun) {
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: data,
        success: function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
                return
            } else {
                $error.text(null)
            }
            if (fun) {
                fun(response);
            }
            if (response["redirect"]) {
                location.href = response["redirect"];
            }
        }
    });
}

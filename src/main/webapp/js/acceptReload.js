window.onbeforeunload = function (event) {
    let message = "";
    window.confirm(message);
    event.returnValue = message;
    return message;
};
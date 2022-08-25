var counter = 3

function addAnswerForm(){
    if (counter === 3){
        var button = "<button onclick=\"deleteAnswerForm()\" class='p-1 text-white shadow rounded'><fmt:message key='create.question.button.delete'/></button><br>"
        document.getElementById('form').innerHTML += button
    }
    if (counter <= 5){
        var form = "        <h6><fmt:message key='create.question.answer'/></h6>\n" +
            "        <textarea name='textarea"+ counter +"' maxlength=\"100\"></textarea><br>\n" +
            "        <label><fmt:message key='create.question.correct'/></label>\n" +
            "        <select name='acorrect" + counter + "' size=\"1\">\n" +
            "            <option value='true' class='text-success'><fmt:message key='create.question.true'/></option>\n" +
            "            <option selected='false' value='false' class='text-danger'><fmt:message key='create.question.false'/></option>\n" +
            "        </select><br>\n"
        document.getElementById('form').innerHTML += form
        counter++
    }
}

function deleteAnswerForm(){
    counter = 3
    document.getElementById('form').innerHTML = ""
}
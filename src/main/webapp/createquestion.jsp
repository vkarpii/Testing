<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="create.question.title"/></title>
    <script src="js/AddAnswerForm.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <div class="m-4 p-4 bg-white rounded shadow">
        <form action="CreateQuestionServlet" method="post">
                <h2><fmt:message key="create.question.create.header"/></h2>
                <p class="text-danger"><fmt:message key="create.question.create.info"/></p>
                <a href="testsettings.jsp" class="p-1 m-1"><fmt:message key="create.question.button.cancel"/></a>
                <button type="submit" class="p-1 m-1 rounded shadow text-white"><fmt:message key="create.question.button.add.question"/></button><br>

                <h6><fmt:message key="create.question.header"/></h6><br>
                <textarea name="textarea" maxlength="300"></textarea><br>
                <hr>
                <div class="m-3">
                    <h6><fmt:message key="create.question.answer"/></h6>
                    <textarea maxlength="100" name="textarea1"></textarea><br>
                    <label><fmt:message key="create.question.correct"/></label>
                    <select name="acorrect1" size=\"1\">
                        <option value="true" class="text-success"><fmt:message key="create.question.true"/></option>
                        <option selected="false" value="false" class="text-danger"><fmt:message key="create.question.false"/></option>
                    </select><br>

                    <h6 name="answer2"><fmt:message key="create.question.answer"/></h6>
                    <textarea maxlength="100" name="textarea2"></textarea><br>
                    <label><fmt:message key="create.question.correct"/></label>
                    <select name="acorrect2" size=\"1\">
                        <option value="true" class="text-success"><fmt:message key="create.question.true"/></option>
                        <option selected="false" value="false" class="text-danger"><fmt:message key="create.question.false"/></option>
                    </select><br>
                    <div id="form"></div>
                </div>

        </form>
        <button onclick="addAnswerForm()" class="p-1 rounded text-white shadow"><fmt:message key="create.question.button.add"/></button>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

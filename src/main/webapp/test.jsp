<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title>${sessionScope.Test.getName()}</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <script type="text/javascript" src="js/timer.js"></script>
    <script type="text/javascript" src="js/acceptReload.js"></script>
</head>
<body>
<div class="m-4 p-4 bg-white rounded shadow">
    <div class="d-flex flex-grow-1 justify-content-center align-items-center bg-primary rounded text-white shadow">
        <div>
            <div class="row">
                <fmt:message key="test.time"/>:
            </div>
            <div id="timer" class="row"></div>
            <script>
                start(${sessionScope.Test.getMaxTime()},${sessionScope.time});
            </script>
        </div>
    </div>
    <h2>${sessionScope.Test.getName()}</h2>
    <form action="${pageContext.request.contextPath}/SaveResult" method="post">
        <label><fmt:message key="test.question"/>:</label><br>
        <c:forEach var="question" items="${sessionScope.Test.getQuestions()}" step="1" varStatus="status">
            <label>${status.index + 1}.${question.getText()}</label><br>
            <div class="mx-3">
                <c:forEach var="answer" items="${question.getAnswers()}" step="1" varStatus="ansStatus">
                    <c:if test="${question.isCheckbox()}">
                        <input type="checkbox" id="answer${answer.getId()}" name="question${question.getId()}${answer.getId()}" value="${answer.getText()}">
                        <label for="answer${answer.getId()}">${answer.getText()}</label><br>
                    </c:if>
                    <c:if test="${!question.isCheckbox()}">
                        <input type="radio" id="answer${answer.getId()}" name="question${question.getId()}" value="${answer.getText()}">
                        <label for="answer${answer.getId()}">${answer.getText()}</label><br>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
        <button id="form" type="submit" class="text-white shadow rounded"><fmt:message key="test.button"/></button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

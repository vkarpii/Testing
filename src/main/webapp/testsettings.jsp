<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="test.settings"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <div class="m-4 p-4 bg-white rounded shadow">
        <form action="PublishTestServlet" method="post">
            <div class="m-3">
                <a href="${pageContext.request.contextPath}/main" class="bg-danger rounded p-2 mb-4 text-white"><fmt:message key="test.settings.button.exit"/></a>
                <c:if test="${sessionScope.test.getQuestions().size() > 0}">
                    <button type="submit" class="bg-primary rounded p-2 text-white"><fmt:message key="test.settings.button.publish"/></button>
                </c:if>
            </div>
            <h6><fmt:message key="test.settings.name"/>: ${sessionScope.test.getName()} <fmt:message key="test.settings.complexity"/>: ${sessionScope.test.getComplexity()}</h6>
            <c:if test="${sessionScope.test.getQuestions().size() <= 0}">
                <br><p class="text-danger mb-4"><fmt:message key="test.settings.info"/></p>
            </c:if>
            <hr>
            <c:forEach var="item" items="${sessionScope.test.getQuestions()}" step="1" varStatus="status">
                <li>
                    №${status.index+1} <fmt:message key="test.settings.question"/> : ${item.getText()} <a href="DeleteQuestionServlet?id=${status.index}" class="bg-danger text-white rounded p-2"><fmt:message key="test.settings.button.delete"/></a> <br>
                    <div class="m-2 ms-5 border border-2 bg-gradient rounded" >
                        <c:forEach var="ans" items="${item.getAnswers()}" step="1" varStatus="stat">
                            <c:if test="${ans.getCorrect()}"><span class="text-success">✓ ${ans.getText()}</span></c:if>
                            <c:if test="${!ans.getCorrect()}"><span class="text-danger">× ${ans.getText()}</span></c:if>
                            <br>
                        </c:forEach>
                    </div>
                </li>
            </c:forEach>
            <div class="m-3">
                <a href="createquestion.jsp" class="bg-primary rounded p-2 text-white m-4"><fmt:message key="test.settings.button.add"/></a>
                <a href="createtest.jsp" class="p-2 m-4"><fmt:message key="test.settings.button.back"/></a>
            </div>

        </form>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

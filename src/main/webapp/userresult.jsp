<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="result.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <div class="m-4 p-4 bg-white rounded shadow">
        <c:if test="${requestScope.userResults.size()!=0}">
            <fmt:message key="info.success"/>:<br>
            <hr>
            <table class="table table-responsive">
                <tr>
                    <td><fmt:message key="info.test.name"/> </td>
                    <td><fmt:message key="result.title"/></td>
                </tr>
                <c:forEach var="item" items="${requestScope.userResults}" step="1" varStatus="status">
                    <tr>
                        <td>${item.getNameOfTest()}</td>
                        <td><formatter:numFormat number="${item.getResult()}"/> %</td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${requestScope.userResults.size() == 0}">
                <p class="text-danger"><fmt:message key="user.result.info"/></p>
            </c:if><br>
        </c:if>
        <a href="${pageContext.request.contextPath}/AdminPanelServlet?page=1"><fmt:message key="test.settings.button.back"/></a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

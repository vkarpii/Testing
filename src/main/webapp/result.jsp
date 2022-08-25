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
<body class="d-flex flex-grow-1 justify-content-center align-items-center">
<div class="login bg-white rounded shadow w-25 p-2">
    <div class="p-3 px-4">
        ${sessionScope.user.getName()}, <fmt:message key="result.info.first"/> ${sessionScope.testName} <fmt:message key="result.info.second"/>.<br>
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <h6><fmt:message key="result.info.third"/>:</h6>
                </div>
            </div>
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <h6><formatter:numFormat number="${sessionScope.CurrResult}"/> %</h6>
                </div>
            </div>
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/main?page=1" class="p-1 m-1"><fmt:message key="result.button.home"/></a>
                </div>
                <div class="col d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/InfoServlet" class="p-1 m-1"><fmt:message key="result.button.results"/></a>
                </div>
            </div>

    </div>
</div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

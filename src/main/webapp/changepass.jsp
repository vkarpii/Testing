<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="change.pass.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body class="d-flex flex-grow-1 justify-content-center align-items-center">
    <div class="login bg-white rounded shadow w-25">
        <form action="changePassword" method="post" class="p-2">
            <label><fmt:message key="change.pass.label.old.pass"/></label><br>
            <input type="password" maxlength="50" minlength="6" name="oldpass" class="mb-2 w-100" required>
            <label><fmt:message key="change.pass.label.new.pass"/></label></label><br>
            <input type="password" maxlength="50" minlength="6" name="newpass" class="mb-2 w-100" required>
            <label><fmt:message key="change.pass.label.repeat.new.pass"/></label>
            <input type="password" maxlength="50" minlength="6" name="repnewpass" class="mb-2 w-100" required><br>
            <div class="text-warning">${requestScope.error}</div>
            <button type="submit" class="shadow text-white m-2"><fmt:message key="change.pass.button.change"/></button>
            <a href="${pageContext.request.contextPath}/InfoServlet"><fmt:message key="change.pass.button.cancel"/></a>
        </form>
    </div>



    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

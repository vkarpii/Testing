<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="edit.user.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body class="d-flex flex-grow-1 justify-content-center align-items-center">
    <div class="login bg-white rounded shadow w-25">
        <form action="EditUser" method="post" class="p-3 px-4">
            <h2><fmt:message key="edit.user.header"/></h2>
            <label><fmt:message key="edit.user.label.name"/></label><br>
            <input type="text" maxlength="50" minlength="4" pattern="[a-zA-Zа-яієїґ\'А-ЯІЄЇҐ]+" placeholder="Enter Name" name="uname" required value="${sessionScope.user.getFirst_name()}" class="mb-2 w-100"/><br>
            <label><fmt:message key="edit.user.label.surname"/></label><br>
            <input type="text" maxlength="50" minlength="4" pattern="[a-zA-Zа-яієїґ\'А-ЯІЄЇҐ]+" placeholder="Enter Surname" name="usurname" required value="${sessionScope.user.getLast_name()}" class="mb-2 w-100"/><br>
            <label><fmt:message key="edit.user.label.login"/></label><br>
            <input type="text" maxlength="50" minlength="6" placeholder="Enter login" name="ulogin" required value="${sessionScope.user.getLogin()}" class="mb-2 w-100"/><br>
            <label><fmt:message key="edit.user.label.email"/></label><br>
            <input type="text" maxlength="50"
                   pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
                   placeholder="Enter Email" name="uemail" value="${sessionScope.user.getEmail()}" class="mb-2 w-100"/><br>
            <c:if test="${requestScope.error != null}">
                <div id="error" class="m-1 p-2 bg-info rounded shadow bg-danger">
                    <span class="text-white">${requestScope.error}</span>
                </div>
            </c:if>
            <div class="row p-1 m-1">
                <div class="col d-flex justify-content-center">
                    <button type="submit" class="p-1 text-white shadow"><fmt:message key="edit.user.button.save"/></button>
                </div>
                <div class="col d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/InfoServlet" class="p-1"><fmt:message key="edit.user.button.cancel.editing"/></a>
                </div>
            </div>
        </form>
    </div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

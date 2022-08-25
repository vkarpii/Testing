<%@ page contentType = "text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="registration.title"/></title>
    <script src="https://www.google.com/recaptcha/api.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
    <body class="d-flex flex-grow-1 justify-content-center align-items-center">
    <div class="login bg-white rounded shadow w-25">
        <form action="RegistrationServlet" method="post" class="p-3">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <h2><fmt:message key="registration.title"/></h2>
                </div>
            </div>
            <label><fmt:message key="registration.label.name"/> <small class="text-muted"><fmt:message key="registration.label.required"/></small></label><br>
            <input type="text" maxlength="50" minlength="4" pattern="[a-zA-Zа-яієїґ\'А-ЯІЄЇҐ]+" class="w-100 mb-1" placeholder="Enter Name" name="uname" required value="${requestScope.nameInput}"/><br>
            <label><fmt:message key="registration.label.surname"/> <small class="text-muted"><fmt:message key="registration.label.required"/></small></label><br>
            <input type="text" maxlength="50" minlength="4" pattern="[a-zA-Zа-яієїґ\'А-ЯІЄЇҐ]+" class="w-100 mb-1" placeholder="Enter Surname" name="usurname" required value="${requestScope.surnameInput}"/><br>
            <label><fmt:message key="registration.label.login"/> <small class="text-muted"><fmt:message key="registration.label.required"/></small></label><br>
            <input type="text" maxlength="50" minlength="6" class="w-100 mb-1" placeholder="Enter login" name="ulogin" required value="${requestScope.loginInput}"/><br>
            <label><fmt:message key="registration.label.email"/> <small class="text-muted"><fmt:message key="registration.label.required"/></small></label><br>
            <input type="text" maxlength="50"
                   pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
                   class="w-100 mb-1" placeholder="Enter Email" name="uemail" value="${requestScope.emailInput}"/><br>
            <label><fmt:message key="registration.label.password"/> <small class="text-muted"><fmt:message key="registration.label.required"/></small></label><br>
            <input type="password" maxlength="50" minlength="6" class="w-100 mb-1" placeholder="Enter Password" name="psw" required/><br>
            <label><fmt:message key="registration.label.repeat.password"/> <small class="text-muted"><fmt:message key="registration.label.required"/></small></label><br>
            <input type="password" maxlength="50" minlength="6" class="w-100 mb-1" placeholder="Repeat Password" name="pswcheck" required/><br>
            <div class="g-recaptcha mb-2 mt-2 d-flex flex-grow-1 justify-content-center align-items-center" data-sitekey="6Lf3M-sgAAAAAFeLLVQzCZk_N9o5D6Al1wRl5jDp"></div>
            <c:if test="${requestScope.login != null}">
                <div id="error" class="m-1 p-2 bg-info rounded shadow bg-danger">
                    <span class="text-white">${requestScope.login}</span>
                </div>
            </c:if>
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <button type="submit" class="text-white"><fmt:message key="registration.button.register"/></button>
                </div>
                <div class="col d-flex justify-content-center">
                    <a href="index.jsp" class="p-1"><fmt:message key="registration.button.cancel"/></a>
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
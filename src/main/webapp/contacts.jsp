<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="contacts.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <jsp:include page="header.jsp" /><br>
    <div class="m-4 p-4 bg-white rounded shadow">
        <div class="d-flex flex-grow-1 justify-content-center align-items-center">
            <div>
                <h5 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="contacts.email"/>✉</h5>
                <p>testing.contacts.e.a@gmail.com</p>
            </div>
        </div>
        <div class="d-flex flex-grow-1 justify-content-center align-items-center">
            <div>
                <h5 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="contacts.phone"/>✆</h5>
                <p>+380909090909</p>
            </div>

        </div>
        <div class="d-flex flex-grow-1 justify-content-center align-items-center">
            <div>
                <h5 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="contacts.address"/>➤</h5>
                <p><fmt:message key="contacts.address.body"/></p>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

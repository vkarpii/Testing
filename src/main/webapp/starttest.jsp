<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="start.test.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body class="d-flex flex-grow-1 justify-content-center align-items-center">
    <div class="m-4 p-4 bg-white rounded shadow">
        <form action="startTest" method="post">
            <h3 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="start.test.header"/></h3><br>
            <label><fmt:message key="start.test.name"/>: ${sessionScope.Test.getName()}</label><br>
            <label><fmt:message key="start.test.max.time"/>: ${sessionScope.Test.getMaxTime()} <fmt:message key="start.test.time"/></label><br>
            <label><fmt:message key="start.test.max.att"/>: ${sessionScope.Test.getMaxAttemps()}</label><br>
            <label><fmt:message key="start.test.info"/> ${sessionScope.CurrAttemps} <fmt:message key="start.test.info.att"/></label><br>
            <c:if test="${sessionScope.CurrAttemps < sessionScope.Test.getMaxAttemps()}">
                <label class="text-info"><fmt:message key="start.info"/></label><br>
                <button type="submit"  class="p-1 m-1 text-white rounded shadow"><fmt:message key="start.test.button.start"/></button>
            </c:if>
            <c:if test="${sessionScope.CurrAttemps >= sessionScope.Test.getMaxAttemps()}">
                <label class="text-danger"><fmt:message key="start.test.att.info"/></label><br>
            </c:if>
            <a href="${pageContext.request.contextPath}/main?page=1"><fmt:message key="start.test.button.cancel"/></a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

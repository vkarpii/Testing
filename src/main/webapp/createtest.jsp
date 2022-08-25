<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="create.test.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body class="d-flex flex-grow-1 justify-content-center align-items-center">
    <div class="login bg-white rounded shadow w-25">
        <form action="CreateTestServlet" method="post" class="p-3 px-4">
            <c:if test="${!sessionScope.change.equals('edit')}">
                <h2><fmt:message key="create.test.title"/></h2>
            </c:if>
            <c:if test="${sessionScope.change.equals('edit')}">
                <h2><fmt:message key="create.test.title.edit"/></h2>
            </c:if>
            <label><fmt:message key="create.test.label.name"/></label><br>
            <input type="text" maxlength="100" placeholder="Enter name" name="tname" value="${sessionScope.test.getName()}" class="mb-2 w-100" required /><br>
            <label><fmt:message key="create.test.label.max.time"/></label><br>
            <input type="number" min="1" max="1440" placeholder="Enter time" name="ttime" value="${sessionScope.test.getMaxTime()}" class="mb-2 w-100" required /><br>
            <label><fmt:message key="create.test.label.max.att"/></label><br>
            <input type="number" min="1" max="999" placeholder="Enter count" name="tcount" value="${sessionScope.test.getMaxAttemps()}" class="mb-2 w-100" required /><br>
            <label><fmt:message key="create.test.label.complexity"/></label>
            <select name="tcomplexity" class="mb-2">
                <option value="1"><fmt:message key="complexity.easy"/></option>
                <option value="2"><fmt:message key="complexity.medium"/></option>
                <option value="3"><fmt:message key="complexity.hard"/></option>
            </select><br>
            <label><fmt:message key="create.test.for.group"/></label>
            <select name="group" class="mb-2">
                <c:forEach var="item" items="${sessionScope.groups}">
                    <option value="${item.getId()}">${item.getName()}</option>
                </c:forEach>
            </select><br>
            <label><fmt:message key="create.test.for.subject"/></label>
            <select name="subject" class="mb-2">
                <c:forEach var="item" items="${sessionScope.subjects}">
                    <option value="${item.getId()}">${item.getName()}</option>
                </c:forEach>
            </select><br>

            <div class="row">
                <div class="col d-flex justify-content-center">
                    <button type="submit" class="text-white shadow p-1"><fmt:message key="create.test.button.next"/> </button>
                </div>
                <div class="col d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/main?page=1"><fmt:message key="create.test.button.cancel"/></a>
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

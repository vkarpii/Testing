<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="edit.group.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body class="d-flex flex-grow-1 justify-content-center align-items-center">
<div class="w-50">

    <div class="m-4 p-4 bg-white rounded shadow">
        <div class="m-1 p-1">
            <p><fmt:message key="edit.group.consist"/>:</p>
            <c:forEach items="${requestScope.editGroups}" var="item">
                <span class="bg-info text-white p-1 m-1">${item.getName()}</span>
            </c:forEach>
        </div>
        <div class="m-2">
            <a href="${pageContext.request.contextPath}/AdminPanelServlet?page=1" class="p-1 m-1"><fmt:message key="edit.group.button.cancel"/></a>
        </div>
    </div>

    <div class="m-4 p-4 bg-white rounded shadow">
        <div class="m-1 p-1">
            <form action="${pageContext.request.contextPath}/editGroup" method="post">
                <fmt:message key="edit.group.add.title"/><br>
                <label class="text-danger"><fmt:message key="edit.group.add.info"/></label><br>
                <c:forEach var="group" items="${sessionScope.groups}" step="1" varStatus="ansStatus">
                    <c:if test="${!group.getName().equals('For all')}">
                        <input type="radio" id="groups${group.getId()}" name="groupadd" value="${group.getId()}">
                        <label for="groups${group.getId()}">${group.getName()}</label><br>
                    </c:if>
                </c:forEach>
                <button type="submit"><fmt:message key="edit.group.button.add"/></button>
            </form>
        </div>
    </div>

    <div class="m-4 p-4 bg-white rounded shadow">
        <div class="m-1 p-1">
            <form action="${pageContext.request.contextPath}/editGroup" method="post">
                <fmt:message key="edit.group.delete"/><br>
                <label class="text-danger"><fmt:message key="edit.group.delete.info"/></label><br>
                <c:forEach var="group" items="${sessionScope.groups}" step="1" varStatus="ansStatus">
                    <c:if test="${!group.getName().equals('For all')}">
                        <input type="radio" id="groupsd${group.getId()}" name="groupdelete" value="${group.getId()}">
                        <label for="groupsd${group.getId()}">${group.getName()}</label><br>
                    </c:if>
                </c:forEach>
                <button type="submit"><fmt:message key="edit.group.button.delete"/> </button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
        integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
        integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

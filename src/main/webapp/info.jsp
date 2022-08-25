<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="info.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <c:import url="header.jsp"/>
    <div class="m-4 p-4 bg-white rounded shadow">
        <div class="m-1 p-1">
            <h3><fmt:message key="info.main.title"/></h3>
            <span><fmt:message key="info.login"/> : ${sessionScope.user.getLogin()}</span><br>
            <span><fmt:message key="info.name"/> : ${sessionScope.user.getFirst_name()}</span><br>
            <span><fmt:message key="info.surname"/> : ${sessionScope.user.getLast_name()}</span><br>
            <span><fmt:message key="info.role"/> :
                <c:choose>
                    <c:when test="${sessionScope.user.getRole() == applicationScope.Admin}"><fmt:message key="info.admin"/></c:when>
                    <c:otherwise><fmt:message key="info.student"/></c:otherwise>
                </c:choose>
            </span><br>
            <span><fmt:message key="info.email"/> : ${sessionScope.user.getEmail()}</span>
        </div>
        <div class="m-1 p-1">
            <a href="edituser.jsp" class="p-1"><fmt:message key="info.button.edit"/></a>
            <a href="${pageContext.request.contextPath}/changepass.jsp" class="bg-primary rounded shadow p-1 m-2 text-white"><fmt:message key="info.button.change.password"/></a>
            <c:if test="${sessionScope.user.getRole() == applicationScope.Admin}">
                <a href="${pageContext.request.contextPath}/AdminPanelServlet?page=1" class="p-1"><fmt:message key="info.button.admin.panel"/></a>
            </c:if>
        </div>
    </div>

    <c:if test="${sessionScope.results.size() != 0}">
        <div class="m-4 p-4 bg-white rounded shadow">
                <div class="m-2 p-2">
                    <h3><fmt:message key="info.success"/> :</h3>
                    <hr>
                    <table class="table table-responsive">
                        <tr>
                            <td><fmt:message key="info.test.name"/></td>
                            <td><fmt:message key="info.test.result"/></td>
                        </tr>
                        <c:forEach var="item" items="${sessionScope.results}" step="1" varStatus="status">
                            <tr>
                                <td>${item.getNameOfTest()}</td>
                                <td><formatter:numFormat number="${item.getResult()}"/> %</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

        </div>
    </c:if>

    <c:if test="${sessionScope.results.size() == 0}">
        <div class="bg-info rounded shadow p-4 m-4">
            <p class="text-white m-1 p-1"><fmt:message key="info.hint"/></p>
        </div>
    </c:if>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>

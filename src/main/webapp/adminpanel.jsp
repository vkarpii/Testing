<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="admin.panel.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <c:import url="header.jsp"/><br>
    <div class="m-4 p-4 bg-white rounded shadow">
        <h3 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="admin.panel.users"/> </h3>
        <div>
            <form action="AdminPanelServlet" method="get">
                <label><fmt:message key="admin.panel.number.of.elements"/></label><br>
                <input type="number" max="100" min="1" name="changePages" value="${sessionScope.countOnPage}" required>
                <button type="submit" class="shadow text-white"><fmt:message key="admin.panel.button.change"/></button>
            </form>
        </div>
        <table class="table table-responsive">
            <tr>
                <td><fmt:message key="admin.panel.number"/></td>
                <td><fmt:message key="admin.panel.user.name"/></td>
                <td><fmt:message key="admin.panel.login"/></td>
                <td><fmt:message key="admin.panel.email"/></td>
                <td><fmt:message key="admin.panel.role"/></td>
                <td></td>
            </tr>
            <c:forEach var="item" items="${requestScope.users}" step="1" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${item.getName()}</td>
                    <td>${item.getLogin()}</td>
                    <td>${item.getEmail()}</td>
                    <td>
                        <c:if test="${item.getRole().getId() == 1}"><fmt:message key="admin.panel.student"/></c:if>
                        <c:if test="${item.getRole().getId() == 2}"><fmt:message key="admin.panel.admin"/></c:if>
                    </td>
                    <td>
                        <c:if test="${item.getId() == 1 && item.getId() != sessionScope.user.getId()}">
                            <fmt:message key="admin.panel.creator"/>
                        </c:if>
                        <c:if test="${item.getId() == 1 && item.getId() == sessionScope.user.getId()}">
                            <fmt:message key="admin.panel.you"/>
                        </c:if>
                        <c:if test="${!(item.getId() == sessionScope.user.getId()) && item.getId() != 1 && item.getId() != sessionScope.user.getId()}">
                            <c:if test="${item.isBlocked()}">
                                <a href="${pageContext.request.contextPath}/block?id=${item.getId()}&event=unblock" class="bg-success rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.unblock"/></a>
                            </c:if>
                            <c:if test="${!item.isBlocked()}">
                                <a href="${pageContext.request.contextPath}/block?id=${item.getId()}&event=block" class="bg-danger rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.block"/></a>
                            </c:if>

                            <c:if test="${item.getRole() == applicationScope.Student}">
                                <a href="${pageContext.request.contextPath}/role?id=${item.getId()}&event=up" class="bg-success rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.up.to.admin"/></a>
                            </c:if>
                            <c:if test="${item.getRole() == applicationScope.Admin}">
                                <a href="${pageContext.request.contextPath}/role?id=${item.getId()}&event=down" class="bg-danger rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.down.to.student"/></a>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/editGroup?id=${item.getId()}" class="bg-primary rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.groups"/></a>
                            <a href="${pageContext.request.contextPath}/editUserByAdmin?id=${item.getId()}" class="bg-warning rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.edit"/></a>
                            <a href="${pageContext.request.contextPath}/deleteUser?id=${item.getId()}" class="bg-danger rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.delete"/></a>
                            <a href="${pageContext.request.contextPath}/seeResults?id=${item.getId()}" class="bg-info rounded shadow text-white p-1 m-1"><fmt:message key="admin.panel.results"/></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!--Pagination -->
        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${requestScope.users.size() > 0}">
            <div class="d-flex flex-grow-1 justify-content-center align-items-center">
                <c:if test="${requestScope.currentPage != 1}">
                    <td><a href="AdminPanelServlet?page=${requestScope.currentPage - 1}"><</a></td>
                </c:if>
                <c:if test="${requestScope.currentPage == 1}">
                    <td><</td>
                </c:if>
                    <%--For displaying Page numbers. The when condition does not display
                                a link for the current page--%>

                <table>
                    <tr>
                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="AdminPanelServlet?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>
                    <%--For displaying Next link --%>
                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                    <td><a href="AdminPanelServlet?page=${requestScope.currentPage + 1}">></a></td>
                </c:if>
                <c:if test="${!(requestScope.currentPage lt requestScope.noOfPages)}">
                    <td >></td>
                </c:if>
            </div>
        </c:if>
    </div>

    <div class="m-4 p-4 bg-white rounded shadow">
        <h3 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="admin.panel.groups"/></h3>
        <div class="row">
            <div class="col">
                <form action="AddGroupServlet" method="post">
                    <fmt:message key="admin.panel.add.group"/><br>
                    <label><fmt:message key="admin.panel.label.group"/></label><br>
                    <input type="text" placeholder="Enter group name" name="group" required /><br>
                    <button type="submit" class="text-white shadow p-1 m-1"><fmt:message key="admin.panel.button.add"/></button>
                    <button type="reset" class="text-white shadow p-1 m-1"><fmt:message key="admin.panel.button.clear"/></button>
                </form>
            </div>
            <div class="col">
                <form action="DeleteGroupServlet" method="post">
                    <fmt:message key="admin.panel.delete.group"/><br>
                    <label class="text-danger"><fmt:message key="admin.panel.delete.danger"/></label><br>
                    <c:forEach var="group" items="${sessionScope.groups}" step="1" varStatus="ansStatus">
                        <c:if test="${!group.getName().equals('For all')}">
                            <input type="radio" id="groups${group.getId()}" name="groupdelete" value="${group.getName()}">
                            <label for="groups${group.getId()}">${group.getName()}</label><br>
                        </c:if>
                    </c:forEach>
                    <button type="submit" class="text-white shadow m-1"><fmt:message key="admin.panel.delete"/></button>
                </form>
            </div>
        </div>
    </div>


    <div class="m-4 p-4 bg-white rounded shadow">
        <h3 class="d-flex flex-grow-1 justify-content-center align-items-center"><fmt:message key="admin.panel.subjects"/></h3>
        <div class="row">
            <div class="col">
                <form action="AddSubjectServlet" method="post">
                    <fmt:message key="admin.panel.add.subject"/><br>
                    <label>Enter subject name</label><br>
                    <input type="text" placeholder="Enter subject name" name="subject" required /><br>
                    <button type="submit" class="text-white shadow p-1 m-1"><fmt:message key="admin.panel.button.add"/></button>
                    <button type="reset" class="text-white shadow p-1 m-1"><fmt:message key="admin.panel.button.clear"/></button>
                </form>
            </div>
            <div class="col">
                <form action="DeleteSubjectServlet" method="post">
                    <fmt:message key="admin.panel.delete.subject"/><br>
                    <label class="text-danger"><fmt:message key="admin.panel.delete.subject.info"/></label><br>
                    <c:forEach var="subject" items="${sessionScope.subjects}" step="1" varStatus="ansStatus">
                        <c:if test="${!subject.getName().equals('Undefined')}">
                            <input type="radio" id="subjects${subject.getId()}" name="subjectdelete" value="${subject.getName()}">
                            <label for="subjects${subject.getId()}">${subject.getName()}</label><br>
                        </c:if>
                    </c:forEach>
                    <button type="submit" class="text-white shadow m-1"><fmt:message key="admin.panel.delete"/> </button>
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

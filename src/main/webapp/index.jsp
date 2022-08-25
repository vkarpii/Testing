<%@ page contentType = "text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="index.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
    <body>
        <c:import url="header.jsp"/>

        <c:if test="${sessionScope.registerAccount != null}">
            <div class="m-4 p-4 bg-success rounded shadow">
                <div class="row">
                    <div class="col d-flex justify-content-between">
                        <span class="text-white">${sessionScope.registerAccount}</span>
                    </div>
                    <div class="col d-flex justify-content-end">
                        <a href="${pageContext.request.contextPath}/removeInfoServlet" class="bg-white text-black-50 rounded p-1"> X </a>
                    </div>
                </div>
            </div>
        </c:if>


        <c:if test="${sessionScope.user != null}">
            <div class="m-4 p-4 bg-white rounded shadow">
            <c:out value="${sessionScope.name}"/>

                <div class="row">
                    <div class="col">
                        <form action="SortingServlet" method="get">
                            <div class="row">
                                <div class="col d-flex justify-content-center">
                                    <h5><fmt:message key="index.panel.sorting"/></h5>
                                </div>
                            </div>
                            <hr class="text-primary">
                                <div class="row">
                                    <div class="col"></div>
                                    <div class="col d-flex justify-content-start">
                                        <c:if test="${sessionScope.res.get(0) == null}">
                                            <input type="checkbox" value="test_name" name="test_name" id="Name">
                                            <label for="Name"><fmt:message key="index.panel.name"/></label>
                                        </c:if>
                                        <c:if test="${sessionScope.res.get(0) != null}">
                                            <input type="checkbox" value="test_name" name="test_name" id="Name" checked>
                                            <label for="Name"><fmt:message key="index.panel.name"/></label>
                                        </c:if>
                                    </div>
                                    <div class="col"></div>
                                </div>
                                <div class="row">
                                    <div class="col"></div>
                                    <div class="col d-flex justify-content-start">
                                        <c:if test="${sessionScope.res.get(1) == null}">
                                            <input type="checkbox" value="complexity" name="complexity" id="Difficulty" >
                                            <label for="Difficulty"><fmt:message key="index.panel.difficulty"/></label><br>
                                        </c:if>
                                        <c:if test="${sessionScope.res.get(1) != null}">
                                            <input type="checkbox" value="complexity" name="complexity" id="Difficulty" checked>
                                            <label for="Difficulty"><fmt:message key="index.panel.difficulty"/></label><br>
                                        </c:if>
                                    </div>
                                    <div class="col"></div>
                                </div>
                                <div class="row">
                                    <div class="col"></div>
                                    <div class="col d-flex justify-content-start">
                                        <c:if test="${sessionScope.res.get(2) == null}">
                                            <input type="checkbox" value="number_of_queries" name="number_of_queries" id="Number of queries">
                                            <label for="Number of queries"><fmt:message key="index.panel.number.of.queries"/></label><br>
                                        </c:if>
                                        <c:if test="${sessionScope.res.get(2) != null}">
                                            <input type="checkbox" value="number_of_queries" name="number_of_queries" id="Number of queries" checked>
                                            <label for="Number of queries"><fmt:message key="index.panel.number.of.queries"/></label><br>
                                        </c:if>
                                    </div>
                                    <div class="col"></div>
                                </div>
                                <div class="row">
                                    <div class="col"></div>
                                    <div class="col d-flex justify-content-start">
                                        <button type="submit" class="text-white shadow"><fmt:message key="index.panel.button"/></button>
                                    </div>
                                    <div class="col"></div>
                                </div>
                        </form>
                    </div>
                    <div class="col">
                        <form action="main?page=1" method="get">
                            <div class="row">
                                <div class="col d-flex justify-content-center">
                                    <h5><fmt:message key="index.panel.subject"/></h5>
                                </div>
                            </div>
                            <hr class="text-primary">
                            <div class="row">
                                <div class="col"></div>
                                <div class="col d-flex justify-content-start">
                                    <select name="filter" class="w-100">
                                        <c:forEach  items="${sessionScope.subjects}" var="item">
                                            <option value="${item.getId()}">${item.getName()}</option>
                                        </c:forEach>
                                    </select><br>
                                </div>
                                <div class="col"></div>
                            </div>
                            <div class="row">
                                <div class="col"></div>
                                <div class="col d-flex justify-content-start">
                                    <button type="submit" class="text-white rounded p-1 mt-2 m-1"><fmt:message key="index.panel.filter.button"/></button>
                                    <a href="${pageContext.request.contextPath}/main?page=1" class="p-1 mt-2 m-1"><fmt:message key="index.panel.skip.button"/></a>
                                </div>
                                <div class="col"></div>
                            </div>
                        </form>
                    </div>
                    <div class="col">
                        <form action="SearchServlet?page=1" method="get">
                            <div class="row">
                                <div class="col d-flex justify-content-center">
                                    <h5><fmt:message key="index.panel.search"/></h5>
                                </div>
                            </div>
                            <hr class="text-primary">
                            <div class="row">
                                <div class="col">
                                    <label><fmt:message key="index.panel.search"/></label>
                                </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col">
                                    <input type="text" maxlength="50" name="search" class="w-100" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col mb-2">
                                    <button type="submit" class="shadow text-white"><fmt:message key="index.panel.search.button"/></button>
                                </div>
                            </div>
                        </form>
                        <form action="main?page=1" method="get">
                            <div class="row">
                                <div class="col">
                                    <label><fmt:message key="index.panel.search.number.elements"/></label>
                                </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col">
                                    <input type="number" max="100" min="1" name="changePages" value="${sessionScope.countOnPage}" class="w-100" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <button type="submit" class="shadow text-white"><fmt:message key="index.panel.button.change"/></button>
                                </div>
                            </div>
                        </form><br>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.tests.size() <= 0}">
            <div class="m-4 p-4 bg-info rounded shadow">
                <p class="text-white"><fmt:message key="index.test.not.found"/></p>
                <c:if test="${sessionScope.user.getRole() == applicationScope.Admin && sessionScope.tests.size() <= 0}">
                    <a href="createtest.jsp" class="text-decoration-none p-1 m-1 text-black-50 bg-white rounded shadow"><fmt:message key="index.test.button.add"/></a>
                </c:if>
            </div>
        </c:if>

        <c:if test="${sessionScope.tests.size() > 0}">
            <div class="m-4 p-4 bg-white rounded shadow">
                <div class="row">
                    <div class="col d-flex justify-content-center">
                        <h3><fmt:message key="index.tests"/></h3>
                    </div>
                </div>
                    <table class="table table-responsive">
                        <tr>
                            <td><fmt:message key="index.test.number"/></td>
                            <td><fmt:message key="index.test.name"/></td>
                            <td><fmt:message key="index.test.attempts"/></td>
                            <td><fmt:message key="index.test.complexity"/></td>
                            <td><fmt:message key="index.test.subject"/></td>
                            <td><fmt:message key="index.test.number.of.queries"/></td>
                            <td></td>
                        </tr>
                        <c:forEach var="item" items="${sessionScope.tests}" step="1" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${item.getName()}</td>
                                <td>${item.getMaxAttemps()}</td>
                                <td>${item.getComplexity()}</td>
                                <td>${item.getSubject().getName()}</td>
                                <td>${item.getNumberOfQueries()}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/test?id=${item.getId()}" class="text-decoration-none p-1 m-1 text-white bg-success rounded shadow"><fmt:message key="index.test.button.start"/></a>
                                    <c:if test="${sessionScope.user.getRole() == applicationScope.Admin}">
                                        <a href="${pageContext.request.contextPath}/editTest?id=${item.getId()}" class="text-decoration-none p-1 m-1 text-white bg-warning rounded shadow"><fmt:message key="index.test.button.edit"/></a>
                                        <a href="${pageContext.request.contextPath}/delete?id=${item.getId()}" class="text-decoration-none p-1 m-1 text-white bg-danger rounded shadow"><fmt:message key="index.test.button.delete"/></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </c:if>
                <c:if test="${sessionScope.user.getRole() == applicationScope.Admin && sessionScope.tests.size() > 0}">
                    <a href="createtest.jsp" class="text-decoration-none p-1 m-1 text-white bg-primary rounded shadow"><fmt:message key="index.test.button.add"/></a>
                </c:if>
                <c:if test="${sessionScope.tests.size() > 0}">
                    <br>
                    <div class="d-flex flex-grow-1 justify-content-center align-items-center">
                        <!--Pagination -->
                            <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td><a href="main?page=${requestScope.currentPage - 1}"><</a></td>
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
                                            <td >${i}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href="main?page=${i}" >${i}</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tr>
                        </table>
                            <%--For displaying Next link --%>
                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td><a href="main?page=${requestScope.currentPage + 1}" >></a></td>
                        </c:if>
                        <c:if test="${!(requestScope.currentPage lt requestScope.noOfPages)}">
                            <td >></td>
                        </c:if>
                    </div>

            </div>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <div class="m-4 p-4 bg-info rounded shadow">
                <span class="text-white"><fmt:message key="index.login.info"/></span>
            </div>
        </c:if>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
                integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
                integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>

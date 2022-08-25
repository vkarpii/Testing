<%@ page contentType = "text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="formatter"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>
<!DOCTYPE>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="about.title"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="m-4 p-4 bg-white rounded shadow">
            <div class="row">
                <div class="col">
                    <h2 class="mx-5"><fmt:message key="about.header"/></h2><br>
                    <h4 class="mx-3">⮞<fmt:message key="about.header.tests"/>⮜</h4>
                    <p><fmt:message key="about.body.tests"/></p>
                    <h4 class="mx-3">⮞<fmt:message key="about.header.groups"/>⮜</h4>
                    <p><fmt:message key="about.body.groups.first"/><br>
                        <fmt:message key="about.body.groups.second"/><br>
                        <fmt:message key="about.body.groups.third"/>
                        <a href="contacts.jsp"><fmt:message key="about.body.groups.link"/></a></p>
                    <h4 class="mx-3">⮞<fmt:message key="about.header.subjects"/>⮜</h4>
                    <p><fmt:message key="about.body.subjects"/></p>
                </div>
                <div class="col ">
                    <img src="img/testing.jpg" alt="testing" class="rounded shadow">
                </div>
            </div>
            <img src="img/epam_logo.png" alt="epam" width="5%" height="auto">
            <p><fmt:message key="about.developed.by"/> Developed by Vitaly Karpii</p>


        </div>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
                integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
                integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>
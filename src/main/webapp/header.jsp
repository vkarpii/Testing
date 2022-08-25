<%@ page contentType = "text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tags/implicit" prefix="f"%>
<%@taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>

<format:setLocale value="${sessionScope.language}"/>
<format:setBundle basename="language"/>

<div class="header pt-1 pb-sm-5 px-3 mb-1 bg-white text-black shadow ">
    <div class="header-left float-lg-start d-flex align-items-center">
        <h4 class="fst-italic fw-bold text-decoration-none text-primary m-2">Testing</h4>
        <a href="${pageContext.request.contextPath}/main?page=1" id="menu-home " class="text-decoration-none p-1 m-1"><format:message key="header.home"/></a>
        <a href="about.jsp" id="menu-about" class="text-decoration-none p-1"><format:message key="header.about"/></a>
        <a href="contacts.jsp" id="menu-contacts" class="text-decoration-none p-1"><format:message key="header.contacts"/></a>
    </div>
        <div>
            <div class="float-lg-end d-flex align-items-center">
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <a href="InfoServlet" class="text-decoration-none p-1 m-1"><f:userName  name="${sessionScope.user.getFirst_name()}" surname="${sessionScope.user.getLast_name()}"/></a><%--<formatter:userName  name="${sessionScope.user.getFirst_name()}" surname="${sessionScope.user.getLast_name()}"/>--%>
                        <a href="InfoServlet" id="menu-info" class="text-decoration-none p-1 m-1"><format:message key="header.info"/></a><%--name="${sessionScope.user.getFirst_name()}" surname="${sessionScope.user.getLast_name()}"--%>
                        <a href="LogoutServlet" class="text-decoration-none p-1 m-1"><format:message key="header.logout"/></a>
                    </c:when>
                    <c:otherwise>
                        <a href="login.jsp" class="text-decoration-none p-1 m-1"><format:message key="header.login"/></a>
                        <a href="registration.jsp" class="text-decoration-none p-1 m-1"><format:message key="header.register"/></a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="float-lg-end d-flex align-items-center mt-2 mx-2">
                <form>
                    <select class="border border-primary" name="sessionLocale" onchange="this.form.submit()">
                        <c:choose>
                            <c:when test="${sessionScope.language.equals('ua')}">
                                <option value="ua" selected>УКР</option>
                            </c:when>
                            <c:otherwise>
                                <option value="ua">УКР</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${sessionScope.language.equals('en')}">
                                <option value="en" selected>EN</option>
                            </c:when>
                            <c:otherwise>
                                <option value="en">EN</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </form>
            </div>
        </div>
</div>






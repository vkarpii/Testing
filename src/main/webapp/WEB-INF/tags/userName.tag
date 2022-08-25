<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="name" required="true" type="java.lang.String" description="Name of user" %>
<%@attribute name="surname" required="true" type="java.lang.String" description="Surname of user" %>

${name} ${surname}
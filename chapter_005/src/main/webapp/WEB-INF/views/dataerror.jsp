<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Such user exists</title>
</head>
<body>
<form method="link" action="${pageContext.servletContext.contextPath}/logout">
    <input type="submit" value="Logout" style="position: absolute; right: 100px;"/>
</form>
<tr>Data error, try again</tr>
<form action="${pageContext.servletContext.contextPath}/list">
    <input type='submit' value='Try again'>
</form>
</body>
</html>

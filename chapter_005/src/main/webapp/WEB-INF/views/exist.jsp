<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Such user exists</title>
</head>
<body>
<tr>Such user exists</tr>
<form action="${pageContext.servletContext.contextPath}/create">
    <input type='submit' value='Try again'>
</form>
</body>
</html>

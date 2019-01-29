<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Such user exists</title>
</head>
<body>
<tr>Data error, try again</tr>
<form action="${pageContext.servletContext.contextPath}/list">
    <input type='submit' value='Try again'>
</form>
</body>
</html>

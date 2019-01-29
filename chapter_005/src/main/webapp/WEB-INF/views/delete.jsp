<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<% final Validate logic = ValidateService.getInstance(); %>
<tr>User was deleted</tr>
<form action="<%=request.getContextPath()%>/WEB-INF/views/list">
<input type='submit' value='Go back'>
</form>
<%=logic.deleteUser(request.getParameter("id"))%>
</body>
</html>

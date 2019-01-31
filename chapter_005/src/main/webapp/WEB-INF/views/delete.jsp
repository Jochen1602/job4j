<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<form method="link" action="${pageContext.servletContext.contextPath}/logout">
    <input type="submit" value="Logout" style="position: absolute; right: 100px;"/>
</form>
<% final Validate logic = ValidateService.getInstance(); %>
<tr>User was deleted</tr>
<form action="<%=request.getContextPath()%>/WEB-INF/views/list">
<input type='submit' value='Go back'>
</form>
<%=logic.deleteUser(request.getParameter("id"))%>
</body>
</html>

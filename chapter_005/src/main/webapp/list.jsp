<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page import="ru.job4j.servlets.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<table style="border: 1px slategrey" cellpadding="1" cellspacing="1" border="1">
    <% final Validate logic = ValidateService.getInstance(); %>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Created</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <% for (User user : logic.findAll()) { %>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td><form action="<%=request.getContextPath()%>/update.jsp" method="get"><input type="hidden" name="id" value="<%=user.getId()%>"><input type="submit" value="update"></form></td>
        <td><form action="<%=request.getContextPath()%>/delete" method="post"><input type='hidden' name='id' value="<%=user.getId()%>"><input type='submit' value='delete'></form></td>
    </tr>
    <% } %>
</table>
<br>
<br>
<form action="<%=request.getContextPath()%>/index.jsp">
    <input type='submit' value='Create new user'>
</form>
</body>
</html>

<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page import="ru.job4j.servlets.User" %><%--
  Created by IntelliJ IDEA.
  User: Jochen
  Date: 21.01.2019
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<table style="border: 1px slategrey" cellpadding="1" cellspacing="1" border="1">
    <%final Validate logic = ValidateService.getInstance(); %>
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Created</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <% for (User user : logic.findAll()) {%>
    <tr>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td><form action="<%=request.getContextPath()%>/update" method="get"><input type="hidden" name="id" value="<%=user.getId()%>"><input type="submit" value="update"></form></td>
        <td><form action="<%=request.getContextPath()%>/delete" method="post"><input type='submit' value='delete'> <input type='hidden' name='id' value="<%=user.getId()%>"></form></td>
    </tr>
    <% } %>
</table>
</body>
</html>

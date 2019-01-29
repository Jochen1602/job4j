<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All users</title>
</head>
<body>
<table style="border: 1px slategrey" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Name</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Created</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${user.createDate}"></c:out></td>
        <td><form action="${pageContext.servletContext.contextPath}/update" method="get"><input type="hidden" name="id" value="${user.id}"><input type="submit" value="update"></form></td>
        <td><form action="${pageContext.servletContext.contextPath}/delete" method="post"><input type="hidden" name="id" value="${user.id}"><input type="submit" value="delete"></form></td>
    </tr>
    </c:forEach>
</table>
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/create">
    <input type='submit' value='Create new user'>
</form>
</body>
</html>

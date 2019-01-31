<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
<form method="link" action="${pageContext.servletContext.contextPath}/logout">
    <input type="submit" value="Logout" style="position: absolute; right: 100px;"/>
</form>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    Name     : <input type="text" name="name"/><br/>
    Login    : <input type="text" name="login"/><br/>
    Password : <input type="text" name="password"/><br/>
    Email    : <input type="text" name="email"/><br/>
    Role     :
    <select name="role">
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select>
    <input type="submit">
</form>
<form action="${pageContext.servletContext.contextPath}/list">
    <input type="submit" value="Go to user's list">
</form>
</body>
</html>

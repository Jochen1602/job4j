<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    Name :  <input type="text" name="name"/><br/>
    Login : <input type="text" name="login"/><br/>
    Email : <input type="text" name="email"/><br/>
    <input type="submit">
</form>
<form action="${pageContext.servletContext.contextPath}/list">
    <input type="submit" value="Go to user's list">
</form>
</body>
</html>

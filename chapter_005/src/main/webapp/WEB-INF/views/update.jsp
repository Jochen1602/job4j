<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update user with id=<%=request.getParameter("id")%></title>
</head>
<body>
<form method="link" action="${pageContext.servletContext.contextPath}/logout">
    <input type="submit" value="Logout" style="position: absolute; right: 100px;"/>
</form>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
<input type='hidden' name='id' value="<%=request.getParameter("id")%>">
    Name  : <input type="text" name="name"  value="<%=request.getAttribute("name")%>"><br/>
    Login : <input type="text" name="login" value="<%=request.getAttribute("login")%>"><br/>
    Email : <input type="text" name="email" value="<%=request.getAttribute("email")%>"><br/>
    <input type="hidden" name="password" value="<%=request.getAttribute("password")%>"><br/>
    <br>
    Role    :
    <select name="role">
        <option value="user">user</option>
        <option value="admin">admin</option>
    </select>
    <br>
    <input type="submit">
</form>
</body>
</html>

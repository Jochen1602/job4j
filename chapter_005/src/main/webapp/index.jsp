<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    Name : <input type="text" name="name"/><br/>
    Login : <input type="text" name="login"/><br/>
    Email : <input type="text" name="email"/><br/>
    <input type="submit">
</form>
</body>
</html>

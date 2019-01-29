<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%final Validate logic = ValidateService.getInstance();%>
<head>
    <title>Update user with id=<%=logic.findById(request.getParameter("id")).getId()%></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/update" method="post">
<input type='hidden' name='id' value="<%=logic.findById(request.getParameter("id")).getId()%>">
    Name :  <input type="text" name="name"  value="<%=logic.findById(request.getParameter("id")).getName()%>"><br/>
    Login : <input type="text" name="login" value="<%=logic.findById(request.getParameter("id")).getLogin()%>"><br/>
    Email : <input type="text" name="email" value="<%=logic.findById(request.getParameter("id")).getEmail()%>"><br/>
    <input type="submit">
</form>
</body>
</html>

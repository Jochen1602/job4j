<%@ page import="ru.job4j.servlets.Validate" %>
<%@ page import="ru.job4j.servlets.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Such user exists</title>
</head>
<body>
<% final Validate logic = ValidateService.getInstance(); %>
<tr>Such user exists</tr>
<form action="<%=request.getContextPath()%>/create.jsp">
    <input type='submit' value='Try again'>
</form>
</body>
</html>

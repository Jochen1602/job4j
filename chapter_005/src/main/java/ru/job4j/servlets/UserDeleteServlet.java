package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Creating new user</title>"
                + "</head>"
                + "<body>"
                + "<tr>User was deleted</tr>"
                + "<form action='" + req.getContextPath()+ "/list'>"
                + "<input type='submit' value='Go back'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
        String id = req.getParameter("id");
        logic.deleteUser(id);
    }
}

package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Updating user</title>"
                + "</head>"
                + "<body>"
                + "<tr>Enter user's data:</tr>"
                + "<form action='" + req.getContextPath()+"/update' method='post'>"
                + "<tr>Id : <input type='hidden' name='id' value='" + req.getParameter("id") + "'></tr>"
                + "<tr>Name : <input type='text' name='name' value='" + logic.findById(req.getParameter("id")).getName() + "\'></tr>"
                + "<tr>Login : <input type='text' name='login' value='" + logic.findById(req.getParameter("id")).getLogin() + "'/></tr>"
                + "<tr>E-mail : <input type='text' name='email' value='" + logic.findById(req.getParameter("id")).getEmail()+ "'/></tr>"
                + "<input type='submit'>"
                + "</form>"
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        if (!logic.fullUpdateUser(id, user)) {
            writer.append("No such user.");
            writer.flush();
        }
    }
}

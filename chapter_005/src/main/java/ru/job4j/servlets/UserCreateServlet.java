package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Creating new user</title>"
                + "</head>"
                + "<body>"
                + "<tr>Enter user's data:</tr>"
                + "<form action='" + req.getContextPath()+ "/create' method='post'>"
                + "<tr>Name : <input type='text' name='name'/></tr>"
                + "<tr>Login : <input type='text' name='login'/></tr>"
                + "<tr>E-mail : <input type='text' name='email'/></tr>"
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
        User user = null;
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        if (email != null && login != null && name != null) {
            user = new User(name, login, email);
        } else {
            if (login != null && name != null) {
                user = new User(name, login);
            } else {
                if (name != null) {
                    user = new User(name);
                }
            }
        }
        if (!logic.addUser(user)) {
            writer.append("This user is already in set.");
            writer.flush();
        }
    }
}

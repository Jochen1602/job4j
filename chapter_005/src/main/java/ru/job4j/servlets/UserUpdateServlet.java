package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email);
        if (!(name.equals("") || login.equals("") || email.equals(""))) {
            if (logic.fullUpdateUser(id, user)) {
                resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
            } else {
                resp.sendRedirect(String.format("%s/dataerror.jsp", req.getContextPath()));
            }
        } else {
            resp.sendRedirect(String.format("%s/dataerror.jsp", req.getContextPath()));
        }
    }
}

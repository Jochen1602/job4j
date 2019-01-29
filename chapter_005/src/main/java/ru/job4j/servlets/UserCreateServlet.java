package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
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
        if (!(name.equals("") || login.equals("") || email.equals(""))) {
            if (logic.addUser(user)) {
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            } else {
                resp.sendRedirect(String.format("%s/exist", req.getContextPath()));
            }
        } else {
            resp.sendRedirect(String.format("%s/exist", req.getContextPath()));
        }
    }
}

package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class UserCreateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("role").equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
        } else {
            final Set<User> all = logic.findAll();
            req.setAttribute("users", all);
            req.getRequestDispatcher("/WEB-INF/views/listLight.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        User user = null;
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        if (email != null && login != null && name != null) {
            user = new User(name, login, password, role, email);
        }
        if (!(name.equals("") || login.equals("") || password.equals("") || email.equals(""))) {
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

package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("id"));
        req.setAttribute("name", logic.findById(req.getParameter("id")).getName());
        req.setAttribute("login", logic.findById(req.getParameter("id")).getLogin());
        req.setAttribute("password", logic.findById(req.getParameter("id")).getPassword());
        req.setAttribute("role", logic.findById(req.getParameter("id")).getRole());
        req.setAttribute("email", logic.findById(req.getParameter("id")).getEmail());
        if (req.getSession().getAttribute("role").equals("admin")) {
            if (req.getSession().getAttribute("login").equals(req.getAttribute("login"))) {
                req.getRequestDispatcher("/WEB-INF/views/selfUpdate.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/WEB-INF/views/updateLight.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        User user = new User(name, login, password, role, email);
        if (!(name.equals("") || login.equals("") || password.equals("") || email.equals(""))) {
            if (logic.fullUpdateUser(id, user)) {
                if (req.getSession().getAttribute("role").equals("admin")) {
                    if (!role.equals("admin") && req.getSession().getAttribute("login").equals(login)) {
                        resp.sendRedirect(String.format("%s/logout", req.getContextPath()));
                    }
                    else {
                        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
                    }
                } else {
                    resp.sendRedirect(String.format("%s/listLight", req.getContextPath()));
                }
            } else {
                resp.sendRedirect(String.format("%s/dataerror", req.getContextPath()));
            }
        } else {
            resp.sendRedirect(String.format("%s/dataerror", req.getContextPath()));
        }
    }
}

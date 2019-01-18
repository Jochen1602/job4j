package ru.job4j.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Presentation layout. Interact only with logic layout.
 */
public class UserServlet extends HttpServlet {
    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(logic.findAll().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String typeOfRequest = req.getParameter("action");
        if (typeOfRequest.equals("delete")) {
            String id = req.getParameter("id");
            if (!logic.deleteUser(id)) {
                writer.append("No such user.");
                writer.flush();
            }
        } else {
            if (typeOfRequest.equals("add")) {
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
            } else {
                if (typeOfRequest.equals("update")) {
                    String id = req.getParameter("id");
                    String name = req.getParameter("name");
                    if (!logic.updateUser(id, name)) {
                        writer.append("No such user.");
                        writer.flush();
                    }
                }
            }
        }
    }
}

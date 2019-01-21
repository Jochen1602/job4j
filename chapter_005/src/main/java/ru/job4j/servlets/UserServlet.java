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
        StringBuilder sb = new StringBuilder("<table>");
        for (User user : logic.findAll()) {
            sb.append("<tr><td>" + user.getName()
                    + "</td><td>" + user.getLogin()
                    + "</td><td>" + user.getEmail()
                    + "</td><td>" + user.getCreateDate()
                    + "</td><td>" + "<form action='" + req.getContextPath() + "/update' method='get'><input type='hidden' name='id' value='" + user.getId() + "'><input type='submit' value='update'></form>"
                    + "</td><td>" + "<form action='" + req.getContextPath() + "/delete' method='post'><input type='submit' value='delete'> <input type='hidden' name='id' value='" + user.getId() + "'></form>"
                    + "</td></tr>" );
        }
        sb.append("</table>");
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>List of users</title>"
                + "</head>"
                + "<body>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }
}

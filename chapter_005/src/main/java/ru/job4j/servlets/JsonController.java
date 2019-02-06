package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder("[");
        boolean dot = false;
        for (User user : MemoryStore.getInstance().findAll()) {
            String jsonInString = mapper.writeValueAsString(user);
            if (dot) {
                sb.append(", ");
            }
            sb.append(jsonInString);
            dot = true;
        }
        sb.append("]");
        out.append(sb.toString());
        System.out.println(sb.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(req.getParameter("name") + " " + req.getParameter("surname"), req.getParameter("login"), req.getParameter("password"), "user", req.getParameter("email"));
//        BufferedReader reader = req.getReader();
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = sb.toString();
//        System.out.println(jsonInString);
//        User user = mapper.readValue(jsonInString, User.class);
        MemoryStore.getInstance().addUser(user);
//        System.out.println(user.toString());
        this.doGet(req, resp);
    }
}

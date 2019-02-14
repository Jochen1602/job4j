package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Item;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List<Item> items = session.createQuery("from Item").list();
        resp.setContentType("text/json");
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder("[");
        boolean dot = false;
        for (Item item : items) {
            String jsonInString = mapper.writeValueAsString(item);
            if (dot) {
                sb.append(", ");
            }
            sb.append(jsonInString);
            dot = true;
        }
        session.getTransaction().commit();
        session.close();
        factory.close();
        sb.append("]");
        out.append(sb.toString());
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setDescription(req.getParameter("description"));
        session.save(item);
        session.getTransaction().commit();
        session.close();
        factory.close();
        this.doGet(req, resp);
    }
}

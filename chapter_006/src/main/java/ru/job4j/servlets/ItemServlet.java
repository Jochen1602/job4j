package ru.job4j.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.function.Function;

public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.tx(
                session -> {
                    List<Item> items = session.createQuery("from Item").list();
                    resp.setContentType("text/json");
                    PrintWriter out = null;
                    try {
                        out = new PrintWriter(resp.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    out.append(toJSON(items));
                    out.flush();
                    return null;
                }
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         this.tx(
                session -> {
                    Item item = new Item();
                    item.setDescription(req.getParameter("description"));
                    session.save(item);
                    return null;
                }
        );
        this.doGet(req, resp);
    }

    private <T> T tx(final Function<Session, T> command) {
        final SessionFactory factory = new Configuration().configure().buildSessionFactory();
        final Session session = factory.openSession();
        session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
            factory.close();
        }
    }

    private String toJSON(List<Item> items) {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder("[");
        boolean dot = false;
        for (Item item : items) {
            String jsonInString = null;
            try {
                jsonInString = mapper.writeValueAsString(item);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (dot) {
                sb.append(", ");
            }
            sb.append(jsonInString);
            dot = true;
        }
        sb.append("]");
        return sb.toString();
    }
}

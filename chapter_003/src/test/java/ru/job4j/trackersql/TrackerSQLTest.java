package ru.job4j.trackersql;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    @Test
    @Ignore
    public void checkConnection() {
        TrackerSQL sql = new TrackerSQL();
        assertThat(sql.init(), is(true));
    }

        public Connection init() {
            try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
                Properties config = new Properties();
                config.load(in);
                Class.forName(config.getProperty("driver-class-name"));
                return DriverManager.getConnection(
                        config.getProperty("url"),
                        config.getProperty("username"),
                        config.getProperty("password")


                );
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        @Test
        @Ignore
        public void createItem() throws Exception {
            try (TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()))) {
                tracker.add(new Item("name", "desc"));
                assertThat(tracker.findByName("name").size(), is(1));
            }
        }

}

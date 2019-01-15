package ru.job4j.sqlru;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DB {
    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(CronScan.class.getName());

    public void init(String properties) {
        LOG.info("Database was created with properties " + properties);
    }

    public void add(Set<Vacancy> items, String properties) {
        LOG.info("Inserting data in table.");
        try (InputStream in = DB.class.getClassLoader().getResourceAsStream(properties)) {
            connecting(in);
            for (Vacancy item : items) {
                String sql = "INSERT INTO vacancy (date, name, text, link) VALUES ('" + item.getDate() + "', '" + item.getName() + "', '" + item.getText() + "', '" + item.getLink() + "');";
                try (Statement statement = connection.createStatement()) {
                    statement.execute(sql);
                } catch (SQLException se) {
                    LOG.error("Message", se);
                }
            }
        } catch (Exception e) {
            LOG.error("Message", e);
        }
        LOG.info("Data was inserted in table.");
    }

    private void connecting(InputStream in) throws IOException, ClassNotFoundException, SQLException {
        Properties config = new Properties();
        config.load(in);
        Class.forName(config.getProperty("jdbc.driver"));
        this.connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        );
    }
}

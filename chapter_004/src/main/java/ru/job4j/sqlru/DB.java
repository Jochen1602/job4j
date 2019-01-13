package ru.job4j.sqlru;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

public class DB {
    private Connection connection;
    PreparedStatement statement = null;

    public void init(String properties) {
        try (InputStream in = DB.class.getClassLoader().getResourceAsStream(properties)) {
            connecting(in);
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS vacancy"
                    + "(id      SERIAL          PRIMARY KEY,"
                    + " date    VARCHAR(10)     NOT NULL, "
                    + " name    TEXT            NOT NULL, "
                    + " text    TEXT            NOT NULL, "
                    + " link    TEXT            NOT NULL)";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Table created");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(Set<Vacancy> items, String properties) {
        try (InputStream in = DB.class.getClassLoader().getResourceAsStream(properties)) {
            connecting(in);
            for (Vacancy item : items) {
                String sql = "INSERT INTO vacancy (date, name, text, link) VALUES ('" + item.getDate() + "', '" + item.getName() + "', '" + item.getText() + "', '" + item.getLink() + "');";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.executeUpdate();
                } catch (SQLException se) {
                    throw new SQLException();
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
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

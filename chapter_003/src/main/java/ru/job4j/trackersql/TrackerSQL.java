package ru.job4j.trackersql;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.util.List;

public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;
    Statement stmt;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        try {
            String sql;
            stmt = connection.createStatement();
            sql = "INSERT INTO items (id, category, state, user_id, info) VALUES (item.getId, item.getDesc, 0, item.getId, item.getDesc());";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try {
            String sql;
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM items;" );
            while (rs.next() ) {
                int id = rs.getInt("id");
                int category  = rs.getInt("category");
                int state  = rs.getInt("state");
                int userId  = rs.getInt("user_id");
                String info = rs.getString("info");
                result.add(new Item(String.valueOf(id), String.valueOf(category), String.valueOf(state), Long.valueOf(id), new String[]{info}));
                System.out.println(String.format("ID = %s   CATEGORY = %s   STATE = %s   USER ID = %s   INFO = %s", id, category, state, userId, info));
            }
            rs.close();
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        return result;
    }

    @Override
    public Item findById(String id) {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
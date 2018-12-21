package ru.job4j.trackersql;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import ru.job4j.tracker.*;

import java.util.List;

public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;
    PreparedStatement statement = null;

    /**
     * Задаём инпутстрим по параметрам из файлика в ресурсах.
     * Создаём 2 таблицы посредством SQL-запросов.
     * Закрываем коннект.
     * @return true если всё прошло успешно.
     */
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
            String sql;
            sql = "CREATE TABLE ITEMS "
                    + "(ID            VARCHAR(18) PRIMARY KEY     NOT NULL,"
                    + " NAME          VARCHAR(50)    NOT NULL, "
                    + " DESC          VARCHAR(50)    NOT NULL, "
                    + " CREATED       INT)";
            statement = connection.prepareStatement(sql);
            sql = "CREATE TABLE COMMENTS "
                    + "(ID            VARCHAR(30) SERIAL PRIMARY KEY,"
                    + " ITEM_ID       VARCHAR(30)    NOT NULL, "
                    + " COMMENT          VARCHAR(50)    NOT NULL)";
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.commit();
            System.out.println("Table created");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        String sql;
        String id = item.getId();
        String name = item.getName();
        String desc = item.getDesc();
        sql = "INSERT INTO ITEMS (ID, NAME, DESC) VALUES (" + id + ", " + name + ", " + desc + ");";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = true;
        String sql;
        String name = item.getName();
        String desc = item.getDesc();
        sql = "UPDATE ITEMS SET NAME = " + name + ", DESC = " + desc + " WHERE ID = " + id + ";";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = true;
        String sql;
        sql = "DELETE FROM ITEMS WHERE ID = " + id + ";";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        String sql;
        sql = "SELECT * FROM ITEMS;";
        List<String> comments = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery(sql)) {
            String id = rs.getString("ID");
            String name  = rs.getString("NAME");
            String desc  = rs.getString("DESC");
            int created  = rs.getInt("user_id");
            while (rs.next()) {
                try (ResultSet rsc = statement.executeQuery("SELECT * FROM COMMENTS WHERE ITEM_ID = "+ id + ";")) {
                    while (rsc.next()) {
                        comments.add(rsc.getString("COMMENT"));
                    }
                    String[] array = new String[comments.size()];
                    comments.toArray(array);
                    result.add(new Item(id, name, desc, created, array));
                    System.out.println(String.format("ID = %s   NAME = %s   DESC = %s   CREATED = %s   INFO = %s", id, name, desc, created, array));
                }
            }
            statement.executeUpdate();
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        String sql = "SELECT * FROM ITEMS WHERE NAME LIKE '%%" + key + "'%%;";
        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("ID");
                String name  = rs.getString("NAME");
                String desc  = rs.getString("DESC");
                int created  = rs.getInt("user_id");
                List<String> comments = new ArrayList<>();
                try (ResultSet rsc = statement.executeQuery("SELECT * FROM COMMENTS WHERE ITEM_ID = " + id + ";")) {
                    while (rsc.next()) {
                        comments.add(rsc.getString("COMMENT"));
                    }
                    String[] array = new String[comments.size()];
                    comments.toArray(array);
                    result.add(new Item(id, name, desc, created, array));
                    System.out.println(String.format("ID = %s   NAME = %s   DESC = %s   CREATED = %s   INFO = %s", id, name, desc, created, array));
                }
            }
            statement.executeUpdate();
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        String sql = "SELECT * FROM ITEMS WHERE ID = " + id + ";";
        try (ResultSet rs = statement.executeQuery(sql)) {
            String desc  = rs.getString("DESC");
            String name  = rs.getString("NAME");
            int created  = rs.getInt("user_id");
            List<String> comment = new ArrayList<>();
            try (ResultSet rsc = statement.executeQuery(sql)) {
                while (rsc.next()) {
                    comment.add(rsc.getString("COMMENT"));
                }
                String[] arr = new String[comment.size()];
                comment.toArray(arr);
                result = new Item(id, name, desc, created, arr);
                System.out.println(String.format("ID = %s   NAME = %s   DESC = %s   CREATED = %s   INFO = %s", id, name, desc, created, arr));
                statement.executeUpdate(sql);
                statement.close();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

    }
}

class Main {
    public static void main(String[] args) {
        TrackerSQL trackerSQL = new TrackerSQL();
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, trackerSQL).init();
    }
}

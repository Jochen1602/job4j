package ru.job4j.trackersql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.*;

import java.util.List;

public class TrackerSQL implements ITracker, AutoCloseable {
    private Connection connection;
    PreparedStatement statement = null;

    public TrackerSQL() {
    }

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * Задаём инпутстрим по параметрам из файлика в ресурсах.
     * Создаём 2 таблицы посредством SQL-запросов.
     * Закрываем коннект.
     * @return true если всё прошло успешно.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS ITEMS "
                    + "(ID            SERIAL PRIMARY KEY,"
                    + " NAME          VARCHAR(50)    NOT NULL, "
                    + " DESCRIPTION   VARCHAR(50)    NOT NULL, "
                    + " CREATED       INT)";
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            sql = "CREATE TABLE IF NOT EXISTS COMMENTS "
                    + "(ID            SERIAL PRIMARY KEY,"
                    + " ITEM_ID       INT NOT NULL REFERENCES ITEMS(ID), "
                    + " COMMENT       VARCHAR(50)    NOT NULL)";
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            System.out.println("Table created");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    public Item add(Item item) {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            int id = generateId();
            String name = item.getName();
            String description = item.getDesc();
            int created = (int) (item.getCreated());
            String sql = "INSERT INTO ITEMS (ID, NAME, DESCRIPTION, CREATED) VALUES (" + id + ", '" + name + "', '" + description + "', " + created + ");";
            System.out.println(sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
                System.out.println("Element was added");
            } catch (SQLException se) {
                throw new SQLException();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return item;
    }

    private void connection(InputStream in) throws IOException, ClassNotFoundException, SQLException {
        Properties config = new Properties();
        config.load(in);
        Class.forName(config.getProperty("driver-class-name"));
        this.connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("username"),
                config.getProperty("password")
        );
    }

    private int generateId() {
        java.util.Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddHHmmss");
        int num = (int) (10.0 * Math.random());
        return Integer.parseInt(simpleDateFormat.format(date) + String.valueOf(num));
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean result = true;
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            String name = item.getName();
            String desc = item.getDesc();
            String sql = "UPDATE ITEMS SET NAME = '" + name + "', DESCRIPTION = '" + desc + "' WHERE ID = '" + id + "';";
            System.out.println(sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
                System.out.println("Element was replaced");
            } catch (SQLException se) {
                throw new SQLException();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = true;
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            String sql = "DELETE FROM ITEMS WHERE ID = \'" + id + "\';";
            System.out.println(sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
                System.out.println("Element was deleted");
            } catch (SQLException se) {
                throw new SQLException();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            String sql = "SELECT * FROM ITEMS";
            System.out.println(sql);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        String name = rs.getString("NAME");
                        String desc = rs.getString("DESCRIPTION");
                        int created = rs.getInt("CREATED");
                        List<String> comment = new ArrayList<>();
                        try (PreparedStatement st = connection.prepareStatement("SELECT * FROM COMMENTS WHERE ITEM_ID = " + id + ";")) {
                            try (ResultSet rsc = st.executeQuery()) {
                                while (rsc.next()) {
                                    comment.add(rsc.getString("COMMENT"));
                                }
                                String[] arr = new String[comment.size()];
                                comment.toArray(arr);
                                String comm = "";
                                for (int i = 0; i < arr.length; i++) {
                                    comm += arr[i];
                                    comm += ", ";
                                }
                                result.add(new Item(String.valueOf(id), name, desc, created, arr));
                                System.out.println(String.format("ID = %s   NAME = %s   DESCRIPTION = %s   CREATED = %s   INFO = %s", id, name, desc, created, comm));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            String sql = "SELECT * FROM ITEMS WHERE NAME LIKE '%%" + key + "%%';";
            System.out.println(sql);
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String desc = rs.getString("DESCRIPTION");
                int created = rs.getInt("CREATED");
                List<String> comment = new ArrayList<>();
                PreparedStatement st = connection.prepareStatement("SELECT * FROM COMMENTS WHERE ITEM_ID = " + id + ";");
                try (ResultSet rsc = st.executeQuery()) {
                    while (rsc.next()) {
                        comment.add(rsc.getString("COMMENT"));
                    }
                    String[] arr = new String[comment.size()];
                    comment.toArray(arr);
                    String comm = "";
                    for (int i = 0; i < arr.length; i++) {
                        comm += arr[i];
                        comm += ", ";
                    }
                    result.add(new Item(String.valueOf(id), name, desc, created, arr));
                    System.out.println(String.format("ID = %s   NAME = %s   DESCRIPTION = %s   CREATED = %s   INFO = %s", id, name, desc, created, comm));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            connection(in);
            String sql = "SELECT * FROM ITEMS WHERE ID = " + id + ";";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                String desc = rs.getString("DESCRIPTION");
                String name = rs.getString("NAME");
                int created = rs.getInt("CREATED");
                List<String> comment = new ArrayList<>();
                PreparedStatement st = connection.prepareStatement("SELECT * FROM COMMENTS WHERE ITEM_ID = " + id + ";");
                try (ResultSet rsc = st.executeQuery()) {
                    while (rsc.next()) {
                        comment.add(rsc.getString("COMMENT"));
                    }
                    String[] arr = new String[comment.size()];
                    comment.toArray(arr);
                    String comm = "";
                    for (int i = 0; i < arr.length; i++) {
                        comm += arr[i];
                        comm += ", ";
                    }
                    result = new Item(id, name, desc, created, arr);
                    System.out.println(String.format("ID = %s   NAME = %s   DESCRIPTION = %s   CREATED = %s   INFO = %s", id, name, desc, created, comm));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
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

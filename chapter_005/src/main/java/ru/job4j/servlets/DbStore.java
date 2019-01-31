package ru.job4j.servlets;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class DbStore implements Store<User> {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        Properties appProps = new Properties();
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            appProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SOURCE.setDriverClassName(appProps.getProperty("jdbc.driver"));
        SOURCE.setUrl(appProps.getProperty("jdbc.url"));
        SOURCE.setUsername(appProps.getProperty("jdbc.username"));
        SOURCE.setPassword(appProps.getProperty("jdbc.password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeQuery("CREATE TABLE IF NOT EXISTS users("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(20) NOT NULL, "
                    + "login VARCHAR(20) NOT NULL, "
                    + "password VARCHAR(20) NOT NULL, "
                    + "role VARCHAR(10) NOT NULL, "
                    + "email VARCHAR(30) NOT NULL, "
                    + "createDate TIMESTAMP NOT NULL DEFAULT NOW()"
                    + ");");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //addUser(new User("root", "root", "root", "admin", "root@ya.ru"));
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public Set<Integer> checkName(String name) {
        Set<Integer> res = new CopyOnWriteArraySet<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE name = ?;")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res.add(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Set<Integer> checkLogin(String login) {
        Set<Integer> res = new CopyOnWriteArraySet<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?;")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res.add(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Set<Integer> checkEmail(String email) {
        Set<Integer> res = new CopyOnWriteArraySet<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?;")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                res.add(resultSet.getInt("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, login, password, role, email) VALUES(?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fullUpdateUser(int id, User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET name = ?, login = ?, password = ?, role = ?, email = ? WHERE id = ?;")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Set<User> findAll() {
        Set<User> res = new CopyOnWriteArraySet<>();
        try (Connection connection = SOURCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String email = resultSet.getString("email");
                String createDate = resultSet.getString("createDate");
                res.add(new User(id, name, login, password, role, email, createDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User findById(int id) {
        User user = new User(id, null, null, null, null, null, null);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
                user.setCreateDate(resultSet.getString("createDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isCredential(String login, String password) {
        boolean res = false;
        for (User u : this.findAll()) {
            if (u.getPassword().equals(password) && u.getLogin().equals(login)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public String roleByLogin(String login) {
        String res = null;
        for (User u : this.findAll()) {
            if (u.getLogin().equals(login)) {
                res = u.getRole();
                break;
            }
        }
        return res;
    }

    public int idByLogin(String login) {
        int res = 0;
        for (User u : this.findAll()) {
            if (u.getLogin().equals(login)) {
                res = u.getId();
                break;
            }
        }
        return res;
    }
}

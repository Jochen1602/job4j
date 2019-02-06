package ru.job4j.servlets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.job4j.servlets.MemoryStore.COUNT;

/**
 * Abstraction of users.
 */
public class User implements Comparable<User> {
    private int id;
    private String name;
    private String login;
    private String password;
    private String role;
    private String email;
    private String createDate;

    public User(int id, String name, String login, String password, String role, String email, String createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String password, String role, String email) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.createDate = dateFormat.format(date);
    }

    public User(int id, String name, String login, String role, String email, String createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.role = role;
        this.email = email;
        this.createDate = createDate;
    }

    public User(String name, String login, String role, String email) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
        this.login = login;
        this.role = role;
        this.email = email;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.createDate = dateFormat.format(date);
    }

    public User(String name) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.createDate = dateFormat.format(date);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "User "
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", role='" + role + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + "   ";
    }

    @Override
    public int compareTo(User o) {
        return this.getId()-o.getId();
    }
}

package ru.job4j.servlets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ru.job4j.servlets.MemoryStore.COUNT;

/**
 * Abstraction of users.
 */
public class User {

    private int id;
    private String name;
    private String login;
    private String email;
    private String createDate;

    public User(String name, String login, String email) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
        this.login = login;
        this.email = email;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.createDate = dateFormat.format(date);
    }

    public User(String name, String login) {
        this.id = COUNT.incrementAndGet();
        this.name = name;
        this.login = login;
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

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User "
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + "   ";
    }
}

package ru.job4j.servlets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Abstraction of users.
 */
public class User {
    private static final AtomicInteger COUNT = new AtomicInteger(0);
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

    public void setName(String name) {
        this.name = name;
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

package ru.job4j.servlets;

import java.util.Set;

public interface Validate {

    void deleteUser(String id);

    boolean fullUpdateUser(String id, User user);

    boolean addUser(User user);

    User findById(String id);

    Set<User> findAll();
}

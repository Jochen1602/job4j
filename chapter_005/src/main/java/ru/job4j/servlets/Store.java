package ru.job4j.servlets;

import java.util.Set;

public interface Store {

    boolean notContains(String name);

    void addUser(User user);

    void fullUpdateUser(int id, User user);

    boolean deleteUser(int id);

    Set<User> findAll();

    User findById(int id);
}

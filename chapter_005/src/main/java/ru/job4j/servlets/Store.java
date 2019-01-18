package ru.job4j.servlets;

import java.util.Set;

public interface Store {

    boolean contains(String name);

    void addUser(User user);

    void updateUser(int id, String name);

    boolean deleteUser(int id);

    Set<User> findAll();

    User findById(int id);
}

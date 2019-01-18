package ru.job4j.servlets;

import java.util.Set;

public interface Validate {

    boolean deleteUser(String id);

    boolean updateUser(String id, String name);

    boolean addUser(User user);

    boolean findById(String id);

    Set<User> findAll();
}

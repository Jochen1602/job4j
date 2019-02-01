package ru.job4j.servlets;

import java.util.Set;

public interface Store<T> {
    Set<Integer> checkData(String name, String login, String email);

    void addUser(T user);

    void fullUpdateUser(int id, T user);

    boolean deleteUser(int id);

    Set<T> findAll();

    T findById(int id);
}

package ru.job4j.servlets;

import java.util.Set;

public interface Store<T> {
    Set<Integer> checkName(String name);

    Set<Integer> checkLogin(String login);

    Set<Integer> checkEmail(String email);

    void addUser(T user);

    void fullUpdateUser(int id, T user);

    boolean deleteUser(int id);

    Set<T> findAll();

    T findById(int id);
}

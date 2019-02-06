package ru.job4j.servlets;

import java.util.*;

public class UserCreateStub implements Validate {
    public final Map<Integer, User> store = new HashMap<>();
    public int ids = 1;

    private UserCreateStub() {

    }

    private static class ValidateHolder {
        private static final Validate INSTANCE = new UserCreateStub();
    }

    public static Validate getInstance() {
        return ValidateHolder.INSTANCE;
    }

    @Override
    public void deleteUser(String id) {
        this.store.remove(Integer.parseInt(id));

    }

    @Override
    public boolean fullUpdateUser(String id, User user) {
        this.store.remove(Integer.parseInt(id));
        this.store.put(Integer.parseInt(id), user);
        return true;
    }

    @Override
    public boolean addUser(User user) {
        user.setId(this.ids++);
        this.store.put(user.getId(), user);
        return true;
    }

    @Override
    public User findById(String id) {
        return this.store.get(id);
    }

    @Override
    public Set<User> findAll() {
        return new TreeSet<>(this.store.values());
    }
}

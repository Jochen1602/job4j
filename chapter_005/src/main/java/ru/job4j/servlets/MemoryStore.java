package ru.job4j.servlets;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Persistent layout. No interaction to logic layout.
 */
public class MemoryStore implements Store {
    private static volatile MemoryStore soloMemoryStore = new MemoryStore();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final Set<String> names = new CopyOnWriteArraySet<>();

    public MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return soloMemoryStore;
    }

    public boolean contains(String name) {
        return this.names.contains(name);
    }

    public boolean deleteUser(int id) {
        return this.users.remove(id) != null;
    }

    public void updateUser(int id, String name) {
        User temp = findById(id);
        this.users.remove(id);
        temp.setName(name);
        this.users.put(id, temp);
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
        this.names.add(user.getName());
    }

    public Set<User> findAll() {
        return new LinkedHashSet<>(users.values());
    }

    public User findById(int id) {
        return users.get(id);
    }
}

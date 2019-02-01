package ru.job4j.servlets;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Persistent layout. No interaction to logic layout.
 */
public class MemoryStore implements Store<User> {
    private static volatile MemoryStore soloMemoryStore = new MemoryStore();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    static final AtomicInteger COUNT = new AtomicInteger(0);

    public MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return soloMemoryStore;
    }

    @Override
    public Set<Integer> checkData(String name, String login, String email) {
        CopyOnWriteArraySet<Integer> names = new CopyOnWriteArraySet();
        for (User u : findAll()) {
            if (u.getName().equals(name) || u.getLogin().equals(login) || u.getEmail().equals(email)) {
                names.add(u.getId());
            }
        }
        System.out.println(name + " names " + names.toString());
        return names;
    }

    public boolean deleteUser(int id) {
        return this.users.remove(id) != null;
    }

    @Override
    public void fullUpdateUser(int id, User user) {
        User temp = findById(id);
        this.users.remove(id);
        temp.setName(user.getName());
        temp.setLogin(user.getLogin());
        temp.setEmail(user.getEmail());
        this.users.put(id, temp);
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public Set<User> findAll() {
        return new LinkedHashSet<>(users.values());
    }

    public User findById(int id) {
        return users.get(id);
    }
}

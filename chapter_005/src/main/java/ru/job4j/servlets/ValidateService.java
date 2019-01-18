package ru.job4j.servlets;

import java.util.Set;

/**
 * Logic layout. No interaction to presentation layout. Interact only with persistent layout.
 */
public class ValidateService implements Validate {
    private final Store logic = MemoryStore.getInstance();
    private static volatile ValidateService soloValidateService = new ValidateService();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return soloValidateService;
    }

    public boolean deleteUser(String id) {
        boolean res = false;
        if (findById(id)) {
            if (logic.deleteUser(Integer.parseInt(id))) {
                res = true;
            }
        }
        return res;
    }

    public boolean updateUser(String id, String name) {
        boolean res = false;
        if (findById(id)) {
            res = true;
            logic.updateUser(Integer.parseInt(id), name);
        }
        return res;
    }

    public boolean addUser(User user) {
        boolean res = false;
        if (!logic.contains(user.getName())) {
            logic.addUser(user);
            res = true;
        }
        return res;
    }

    public boolean findById(String id) {
        boolean res = true;
        try {
            int num = Integer.parseInt(id);
            if (logic.findById(num) == null) {
                res = false;
            }
        } catch (NumberFormatException e) {
            res = false;
        }
        return res;
    }

    public Set<User> findAll() {
        return logic.findAll();
    }
}

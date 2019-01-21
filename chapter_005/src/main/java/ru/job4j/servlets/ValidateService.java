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

    public void deleteUser(String id) {
        logic.deleteUser(Integer.parseInt(id));
    }

    @Override
    public boolean fullUpdateUser(String id, User user) {
        boolean res = false;
        if (findById(id) != null) {
            res = true;
            logic.fullUpdateUser(Integer.parseInt(id), user);
        }
        return res;
    }

    public boolean addUser(User user) {
        boolean res = false;
        if (logic.notContains(user.getName())) {
            logic.addUser(user);
            res = true;
        }
        return res;
    }

    public User findById(String id) {
        User res = null;
        try {
            int num = Integer.parseInt(id);
            if (logic.findById(num) != null) {
                res =  logic.findById(num);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Set<User> findAll() {
        return logic.findAll();
    }
}

package ru.job4j.servlets;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
            CopyOnWriteArraySet<Integer> ids = new CopyOnWriteArraySet();
            ids.addAll(logic.checkName(user.getName()));
            ids.addAll(logic.checkLogin(user.getLogin()));
            ids.addAll(logic.checkEmail(user.getEmail()));
            System.out.println(ids.toString());
            if ((ids.size() == 1 && ids.contains(Integer.parseInt(id))) || ids.size() == 0) {
                res = true;
                logic.fullUpdateUser(Integer.parseInt(id), user);
            }
        }
        return res;
    }

    public boolean addUser(User user) {
        boolean res = false;
        CopyOnWriteArraySet<Integer> ids = new CopyOnWriteArraySet();
        ids.addAll(logic.checkName(user.getName()));
        ids.addAll(logic.checkEmail(user.getEmail()));
        ids.addAll(logic.checkLogin(user.getLogin()));
        System.out.println(ids.toString());
        if (ids.size() == 0) {
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
                res =  (User) logic.findById(num);
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

package ru.job4j.servlets;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Logic layout. No interaction to presentation layout. Interact only with persistent layout.
 */
public class ValidateService implements Validate {
    private final Store logic = DbStore.getInstance();
    private static volatile Validate soloValidateService = new ValidateService();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return (ValidateService) soloValidateService;
    }

    public void deleteUser(String id) {
        logic.deleteUser(Integer.parseInt(id));
    }

    @Override
    public boolean fullUpdateUser(String id, User user) {
        boolean res = false;
        if (findById(id) != null) {
            CopyOnWriteArraySet<Integer> ids = new CopyOnWriteArraySet();
            ids.addAll(logic.checkData(user.getName(), user.getLogin(), user.getEmail()));
            if ((ids.size() == 1 && ids.contains(Integer.parseInt(id))) || ids.size() == 0) {
                res = true;
                System.out.println(user.toString());
                logic.fullUpdateUser(Integer.parseInt(id), user);
            }
        }
        return res;
    }

    public boolean addUser(User user) {
        boolean res = false;
        CopyOnWriteArraySet<Integer> ids = new CopyOnWriteArraySet();
        ids.addAll(logic.checkData(user.getName(), user.getLogin(), user.getEmail()));
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

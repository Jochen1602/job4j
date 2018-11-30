package ru.job4j.multithreading;

import java.util.HashSet;
import java.util.Set;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 *class UserStorage Решение задачи 2. Класс хранилища пользователей UserStorage[#84189]
 *@author antontokarev
 *@since 29.11.2018
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Set<User> store = new HashSet<>();

    /**
     * Метод добавления нового пользователя.
     * @param user кого добавляем.
     * @return true если добавление прошло успешно, false иначе.
     */
    public boolean add(final User user) {
        synchronized (this) {
            return store.add(user);
        }
    }

    /**
     * Метод добавления суммы на счёт пользователя.
     * @param user кому добавляем.
     * @param amount сколько добавляем.
     * @return true если изменение прошло успешно, false иначе.
     */
    public boolean update(final User user, final int amount) {
        synchronized (this) {
            return store.add(new User(user.getId(), user.getAmount() + amount)) && store.remove(user);
        }
    }

    /**
     * Метод удаления пользователя.
     * @param user кого удаляем.
     * @return true если удаление прошло успешно, false иначе.
     */
    public boolean delete(final User user) {
        synchronized (this) {
            return store.remove(user);
        }
    }

    /**
     * Метод удаления пользователя по id.
     * @param id кого удаляем.
     * @return true если удаление прошло успешно, false иначе.
     */
    public boolean idDelete(final int id) {
        synchronized (this) {
            boolean result = false;
            for (User u : store) {
                if (u.getId() == id) {
                    delete(u);
                    result = true;
                    break;
                }
            }
            return result;
        }
    }

    public User findUserById(final int id) {
        synchronized (this) {
            User result = null;
            for (User u : store) {
                if (u.getId() == id) {
                    result = u;
                    break;
                }
            }
            return result;
        }
    }

    /**
     * Метод транзакции между двумя пользователями.
     * @param fromId id источника перевода.
     * @param toId id цели перевода.
     * @param amount сумма перевода.
     * @return true если добавление прошло успешно, false иначе.
     */
    public boolean transfer(final int fromId, final int toId, final int amount) {
            boolean result = false;
            User from = findUserById(fromId);
            User to = findUserById(toId);
            if (from != null && to != null) {
                if (from.getAmount() >= amount) {
                    result = update(findUserById(fromId), -amount) && update(findUserById(toId), amount);
                }
            }
            return result;
    }
}

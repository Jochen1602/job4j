package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**class UserConvert Решение задачи 2. Написать программу преобразования List в Map.
 *@author antontokarev
 *@since 02.11.2018
 */
public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
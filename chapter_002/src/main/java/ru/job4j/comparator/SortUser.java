package ru.job4j.comparator;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *class SortUser Решение задач 1. Организовать сортировку User и 2. Сортировка User с использованием Comparator
 *@author antontokarev
 *@since 04.11.2018
 */
public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    /**
     * Метод сортировки по длине имени.
     * @param list список юзеров.
     * @return отсортированный по длине имени список юзеров.
     */
    public List<User> sortNameLength(List<User> list) {
        list.sort(Comparator.comparing(User::getNameLenght));
        return list;
    }

    /**
     * Метод сортировки юзеров по обоим полям - снача по имени, потом по возрасту.
     * @param list список юзеров.
     * @return отсортированный список юзеров.
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));
        return list;
    }
}
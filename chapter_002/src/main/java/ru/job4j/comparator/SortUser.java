package ru.job4j.comparator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *class SortUser Решение задачи 1. Организовать сортировку User
 *@author antontokarev
 *@since 04.11.2018
 */
public class SortUser {
    public Set<User> sort (List<User> list) {
        return new TreeSet<>(list);
    }
}
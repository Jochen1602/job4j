package ru.job4j.comparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;

/**
 *class SortUserTest Тестирование задачи 1. Организовать сортировку User
 *@author antontokarev
 *@since 04.11.2018
 */
public class SortUserTest {
    @Test
    public void firstTestOfThree() {
        SortUser set = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Hans", "18"));
        list.add(new User("Max", "29"));
        list.add(new User("Fritz", "17"));
        Set<User> result = set.sort(list);
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Fritz", "17"));
        expected.add(new User("Hans", "18"));
        expected.add(new User("Max", "29"));
        assertTrue(result.equals(expected));
    }

    @Test
    public void secondTestOfFour() {
        SortUser set = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Hans", "18"));
        list.add(new User("Max", "29"));
        list.add(new User("Fritz", "17"));
        list.add(new User("Karl", "11"));
        Set<User> result = set.sort(list);
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Karl", "11"));
        expected.add(new User("Fritz", "17"));
        expected.add(new User("Hans", "18"));
        expected.add(new User("Max", "29"));
        assertTrue(result.equals(expected));
    }
}
package ru.job4j.comparator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 *class SortUserTest Тестирование задач 1. Организовать сортировку User и 2. Сортировка User с использованием Comparator
 *@author antontokarev
 *@since 04.11.2018
 */
public class SortUserTest {
    @Test
    public void firstTestOfThree() {
        SortUser set = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Hans", 18));
        list.add(new User("Max", 29));
        list.add(new User("Fritz", 17));
        Set<User> result = set.sort(list);
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Fritz", 17));
        expected.add(new User("Hans", 18));
        expected.add(new User("Max", 29));
        assertEquals(result, expected);
    }

    @Test
    public void secondTestOfFour() {
        SortUser set = new SortUser();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Hans", 18));
        list.add(new User("Max", 29));
        list.add(new User("Fritz", 17));
        list.add(new User("Karl", 11));
        Set<User> result = set.sort(list);
        Set<User> expected = new TreeSet<>();
        expected.add(new User("Karl", 11));
        expected.add(new User("Fritz", 17));
        expected.add(new User("Hans", 18));
        expected.add(new User("Max", 29));
        assertEquals(result, expected);
    }

    @Test
    public void nameLengthTest() {
        SortUser set = new SortUser();
        List<User> list = new ArrayList<>();
        User first = new User("Hans", 18);
        User second = new User("Max", 29);
        User third = new User("Fritz", 17);
        list.add(first);
        list.add(second);
        list.add(third);
        List<User> result = set.sortNameLength(list);
        List<User> expected = new ArrayList<>();
        expected.add(second);
        expected.add(first);
        expected.add(third);
        assertEquals(result, expected);
    }

    @Test
    public void nameLengthThenAgeTest() {
        SortUser set = new SortUser();
        List<User> list = new ArrayList<>();
        User first = new User("Hans", 20);
        User second = new User("Hans", 29);
        User third = new User("Fritz", 17);
        User fourth = new User("Hans", 18);
        User fifth = new User("Fritz", 29);
        list.add(first);
        list.add(second);
        list.add(third);
        list.add(fourth);
        list.add(fifth);
        List<User> result = set.sortByAllFields(list);
        List<User> expected = new ArrayList<>();
        expected.add(third);
        expected.add(fifth);
        expected.add(fourth);
        expected.add(first);
        expected.add(second);
        assertEquals(result, expected);
    }
}
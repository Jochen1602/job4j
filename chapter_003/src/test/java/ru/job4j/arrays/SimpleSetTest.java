package ru.job4j.arrays;


import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class SimpleSetTest Тестирование задачи 1. Реализовать коллекцию Set на массиве[#84118]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleSetTest {
    private SimpleSet<String> set = new SimpleSet<>();

    @Before
    public void beforeTest() {
        set.add("12");
        set.add("23");
        set.add("34");
        set.add("23");
        set.add("12");
        set.add("01");
        set.add("12");
    }

    @Test
    public void setStructureTest() {
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("12"));
        assertThat(it.next(), is("23"));
        assertThat(it.next(), is("34"));
        assertThat(it.next(), is("01"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationExceptionTest() {
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("12"));
        assertThat(it.next(), is("23"));
        assertThat(it.next(), is("34"));
        set.add("00");
        assertThat(it.next(), is("01"));
        assertThat(it.hasNext(), is(false));
    }
}
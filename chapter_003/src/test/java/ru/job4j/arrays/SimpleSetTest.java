package ru.job4j.arrays;


import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;

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
        assertThat(set.iterator().next().equals("12"), is(true));
        assertThat(set.iterator().next().equals("23"), is(true));
        assertThat(set.iterator().next().equals("34"), is(true));
        assertThat(set.iterator().next().equals("01"), is(true));
        assertThat(set.iterator().hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationExceptionTest() {
        assertThat(set.iterator().next().equals("12"), is(true));
        assertThat(set.iterator().next().equals("23"), is(true));
        assertThat(set.iterator().next().equals("34"), is(true));
        set.add("12");
        assertThat(set.iterator().next().equals("01"), is(true));
    }
}

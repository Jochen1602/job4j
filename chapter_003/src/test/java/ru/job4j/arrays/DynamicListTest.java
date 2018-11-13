package ru.job4j.arrays;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class DynamicListTest Тестирование задачи 5.3.1. Создать динамический список на базе массива.[#84103]
 *@author antontokarev
 *@since 13.11.2018
 */
public class DynamicListTest {
    private DynamicList<String> list = new DynamicList<>();

    @Before
    public void beforeTest() {
        list.add("12");
        list.add("23");
        list.add("34");
        list.add("45");
        list.add("56");
        list.add("67");
    }

    @Test
    public void whenAdd6ElementsAndDoSomething() {
        assertThat(list.set(10, "000"), is(false));
        assertThat(list.get(3), is("45"));
        list.set(0, "1212");
        assertThat(list.get(0), is("1212"));
        assertThat(list.delete(0), is(true));
        assertThat(list.delete(0), is(false));
        assertThat(list.get(0) == null, is(true));
        assertThat(list.getSize(), is(10));
        list.add("12");
        list.add("23");
        list.add("34");
        list.add("45");
        list.add("56");
        list.add("67");
        assertThat(list.getSize(), is(15));
        list.add("12");
        list.add("23");
        list.add("34");
        list.add("45");
        list.add("56");
        list.add("67");
        assertThat(list.getSize(), is(22));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void iteratorTest() {
        assertThat(list.iterator().next(), is("12"));
        assertThat(list.iterator().next(), is("23"));
        list.add("34");
        assertThat(list.iterator().next(), is("12"));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void hasNextIteratorTest() {
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        assertThat(list.iterator().hasNext(), is(true));
        list.iterator().next();
        assertThat(list.iterator().hasNext(), is(false));
    }
}
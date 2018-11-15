package ru.job4j.arrays;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class MyLinkedListTest Тестирование задачи 5.3.2. Создать контейнер на базе связанного списка [#84104]
 *@author antontokarev
 *@since 13.11.2018
 */
public class MyLinkedListTest {
    private MyLinkedList<String> list = new MyLinkedList<>();

    @Before
    public void beforeTest() {
        list.add("12");
        list.add("23");
        list.add("34");

    }
    @Test
    public void justBigTest() {
        assertThat(list.getSize(), is(3));
        assertThat(list.get(1).equals("23"), is(true));

    }

    @Test
    public void justAnotherBigTest() {
        assertThat(list.iterator().hasNext(), is(true));
        list.iterator().next();
        list.iterator().next();
        list.iterator().next();
        assertThat(list.iterator().hasNext(), is(false));
    }
    @Test(expected = ConcurrentModificationException.class)
    public void concurrentModificationException() {
        Iterator<String> it = list.iterator();
        assertThat(it.next(), is("12"));
        list.add("34");
        assertThat(it.next(), is("12"));
    }
    @Test(expected = NoSuchElementException.class)
    public void noSuchElementException() {
        Iterator<String> it = list.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }
}
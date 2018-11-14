package ru.job4j.arrays;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class SimpleStackTest Тестирование задачи 5.3.3. Используя контейнер на базе связанного списка создать контейнер Stack[#84102]
 *@author antontokarev
 *@since 14.11.2018
 */
public class SimpleStackTest {
    private SimpleStack<String> list = new SimpleStack<>();

    @Before
    public void beforeTest() {
        list.push("12");
        list.push("23");
        list.push("34");

    }
    @Test
    public void justSimpleTest() {
        assertThat(list.poll(), is("34"));
        assertThat(list.poll(), is("23"));
        assertThat(list.poll(), is("12"));
        list.push("45");
        assertThat(list.poll(), is("45"));
    }
}
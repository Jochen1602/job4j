package ru.job4j.arrays;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class SimpleArrayListTest Тестирование задачи 5.3.0 Создать метод delete для односвязного списка[#84101]
 *@author antontokarev
 *@since 13.11.2018
 */
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeElementsThenDeleteTwoOfThem() {
        assertThat((Integer) list.delete(), is(3));
        assertThat((Integer) list.delete(), is(2));
        assertThat(list.getSize(), is(1));
    }
}
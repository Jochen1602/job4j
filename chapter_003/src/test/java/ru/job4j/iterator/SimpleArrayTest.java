package ru.job4j.iterator;

import org.junit.Test;
import ru.job4j.arrays.SimpleArray;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 *class SimpleArrayTest Тестирование задачи 5.2.1. Реализовать SimpleArray<T>[#84114]
 *@author antontokarev
 *@since 12.11.2018
 */
public class SimpleArrayTest {
    @Test
    public void addThreeIntsAndChangeSecond() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("IDDQD");
        array.add("IDKFA");
        array.add("IDCLIP");
        array.set(1, "IMPULSE9");
        assertThat(array.get(1), is("IMPULSE9"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void addThreeIntsAndCanNotAddFourth() {
        SimpleArray<String> array = new SimpleArray<>(3);
        array.add("IDDQD");
        array.add("IDKFA");
        array.add("IDCLIP");
        array.add("IMPULSE9");
    }
    @Test(expected = NoSuchElementException.class)
    public void addThreeAndCanNotTakeFourthElement() {
        SimpleArray<Double> array = new SimpleArray<>(3);
        array.add(4.0);
        array.add(6.88);
        array.add(1.25);
        array.iterator().next();
        array.iterator().next();
        array.iterator().next();
        array.iterator().next();
    }
    @Test
    public void addTwoDeleteTwoAndArrayIsFullOfNull() {
        SimpleArray<Integer> array = new SimpleArray<>(3);
        array.add(4);
        array.add(5);
        array.delete(0);
        array.delete(0);
        assertThat(array.get(0) == null, is(true));
    }
    @Test
    public void addThreeAndHasNoNextForThird() {
        SimpleArray<Double> array = new SimpleArray<>(3);
        array.add(4.0);
        array.add(6.88);
        array.add(1.25);
        array.iterator().next();
        array.iterator().next();
        array.iterator().next();
        assertThat(array.iterator().hasNext(), is(false));
    }
}
package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 *class JaggedArrayIteratorTest Тестирование задачи 5.1.1. Итератор для двухмерного массива int[][]
 *@author antontokarev
 *@since 09.11.2018
 */
public class JaggedArrayIteratorTest {
    @Test
    public void findFifthElement() {
        JaggedArrayIterator jaggedArrayIterator = new JaggedArrayIterator(new int[][]{{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}});
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        int result = (Integer) jaggedArrayIterator.next();
        assertThat(result, is(5));
    }

    @Test
    public void findFirstAndTenthElement() {
        JaggedArrayIterator jaggedArrayIterator = new JaggedArrayIterator(new int[][]{{1}, {2, 3, 4, 5}, {6, 7}, {8, 9, 10, 11, 12, 13, 14}});
        int result = (Integer) jaggedArrayIterator.next();
        assertThat(result, is(1));
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        assertThat(jaggedArrayIterator.next(), is(10));
    }
    @Test(expected = NoSuchElementException.class)
    public void noSuchElement() {
        JaggedArrayIterator jaggedArrayIterator = new JaggedArrayIterator(new int[][]{{1}, {2, 3, 4, 5}, {6, 7}});
        int result = (Integer) jaggedArrayIterator.next();
        assertThat(result, is(1));
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
    }

    @Test
    public void hasNextOfFourthElementButNoNextForFifthElement() {
        JaggedArrayIterator jaggedArrayIterator = new JaggedArrayIterator(new int[][]{{1}, {2, 3, 4, 5}});
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        jaggedArrayIterator.next();
        boolean result = jaggedArrayIterator.hasNext();
        assertThat(result, is(true));
        jaggedArrayIterator.next();
        assertThat(jaggedArrayIterator.hasNext(), is(false));
    }
}
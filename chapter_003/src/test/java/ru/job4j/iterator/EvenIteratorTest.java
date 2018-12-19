package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 *class EvenIteratorTest Тестирование задачи 5.1.2. Создать итератор четных чисел
 *@author antontokarev
 *@since 10.11.2018
 */
public class EvenIteratorTest {
    @Test
    public void findFifthElement() {
        EvenIterator evenIterator = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        int result = (Integer) evenIterator.next();
        assertThat(result, is(10));
    }
    @Test(expected = NoSuchElementException.class)
    public void noSuchElement() {
        EvenIterator evenIterator = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        evenIterator.next();
        int result = (Integer) evenIterator.next();
    }
    @Test(expected = NoSuchElementException.class)
    public void noSuchElementAtAll() {
        EvenIterator evenIterator = new EvenIterator(new int[]{1, 3, 5, 7});
        evenIterator.next();
    }
    @Test
    public void noSuchElementAtAllHasNextNo() {
        EvenIterator evenIterator = new EvenIterator(new int[]{1, 3, 5, 7});
        assertThat(evenIterator.hasNext(), is(false));
    }
}
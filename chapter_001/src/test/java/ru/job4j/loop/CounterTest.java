package ru.job4j.loop;

/**
 *Class Max тестирование задачи 5.1. Подсчет суммы чётных чисел в диапазоне
 *@author antontokarev
 *@since 12.10.2018
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CounterTest {
    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}
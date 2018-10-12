package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *Class CounterTest тестирование задачи 5.2. Создать программу вычисляющую факториал.
 *@author antontokarev
 *@since 12.10.2018
 */

public class FactorialTest {
    @Test
    public void TheFactorialOf5Is120() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }
}
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
    public void theFactorialOf5Is120() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }
    @Test
    public void theFactorialOf0Is1() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result, is(1));
    }
}
package ru.job4j.calc;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *class CalculatorTest Тестирование задачи 1. Подсчет функции в диапазоне.
 *@author antontokarev
 *@since 07.11.2018
 */
public class CalculatorTest {
    @Test
    public void linearTest() {
        Calculator calculator = new Calculator();
        List<Double> result = calculator.diapasonFunction(1, 4, i -> i * 1.0);
        List<Double> expected = Arrays.asList(1.0, 2.0, 3.0);
        assertThat(expected, is(result));
    }
    @Test
    public void quadraticTest() {
        Calculator calculator = new Calculator();
        List<Double> result = calculator.diapasonFunction(1, 4, i -> Math.pow(i, 2));
        List<Double> expected = Arrays.asList(1.0, 4.0, 9.0);
        assertThat(expected, is(result));
    }
    @Test
    public void logarithmicTest() {
        Calculator calculator = new Calculator();
        List<Double> result = calculator.diapasonFunction(1, 4, Math::log);
        List<Double> expected = Arrays.asList(Math.log(1), Math.log(2), Math.log(3));
        assertThat(expected, is(result));
    }
}
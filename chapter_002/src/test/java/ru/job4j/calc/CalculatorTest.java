package ru.job4j.calc;

import org.junit.Test;
import java.util.ArrayList;
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
        List<Double> result = new Calculator().linear(1, 5);
        List<Double> expected = new ArrayList<>(Arrays.asList(1d, 2d, 3d, 4d, 5d));
        assertThat(result, is(expected));
    }
    @Test
    public void linearTest2() {
        List<Double> result = new Calculator().linear(7, 8);
        List<Double> expected = new ArrayList<>(Arrays.asList(7d, 8d));
        assertThat(result, is(expected));
    }
    @Test
    public void quadraticTest() {
        List<Double> result = new Calculator().quadratic(1, 5);
        List<Double> expected = new ArrayList<>(Arrays.asList(1d, 4d, 9d, 16d, 25d));
        assertThat(result, is(expected));
    }
    @Test
    public void quadraticTest2() {
        List<Double> result = new Calculator().quadratic(7, 8);
        List<Double> expected = new ArrayList<>(Arrays.asList(49d, 64d));
        assertThat(result, is(expected));
    }
    @Test
    public void logarithmicTest() {
        List<Double> result = new Calculator().logarithmic(1, 3);
        List<Double> expected = new ArrayList<>(Arrays.asList(Math.log(1.0), Math.log(2.0), Math.log(3.0)));
        assertThat(result, is(expected));
    }
    @Test
    public void logarithmicTest2() {
        List<Double> result = new Calculator().logarithmic(7, 8);
        List<Double> expected = new ArrayList<>(Arrays.asList(Math.log(7.0), Math.log(8.0)));
        assertThat(result, is(expected));
    }
}
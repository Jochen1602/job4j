package ru.job4j.calculate;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Anton Tokarev (jochen13@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CalculateTest {
    /**
     * Test of calculator (3.1. Элементарный калькулятор.).
     */
    @Test
    public void whenAddTwoPlusOneThenThree() {
        Calculate calc = new Calculate();
        calc.add(2D, 1D);
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenSubtractTwoByOneThenOne() {
        Calculate calc = new Calculate();
        calc.subtract(2D, 1D);
        double result = calc.getResult();
        double expected = 1D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMultiplyTwoByOneThenTwo() {
        Calculate calc = new Calculate();
        calc.multiply(2D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    @Test
    public void whenDivTwoByOneThenTwo() {
        Calculate calc = new Calculate();
        calc.div(2D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

}
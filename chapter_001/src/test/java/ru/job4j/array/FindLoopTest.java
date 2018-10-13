package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindLoopTest {

    @Test
    public void whenArrayHasLenght5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {3, 9, 5, 10, 7};
        int value = 10;
        int result = find.indexOf(input, value);
        int expect = 3;
        assertThat(result, is(expect));
    }
    @Test
    public void whenArrayHasLenght5ThenMinus1() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {3, 9, 5, 10, 7};
        int value = 1;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }
}
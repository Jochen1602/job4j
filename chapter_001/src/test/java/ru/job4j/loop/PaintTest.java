package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *Class CounterTest тестирование задачи 5.4. Построить пирамиду в псевдографике.
 *@author antontokarev
 *@since 12.10.2018
 */

public class PaintTest {
    @Test
    public void whenPiramidHasHeight3() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String result = paint.pyramid(3);
        assertThat(result, is(String.format("  ^  %s ^^^ %s^^^^^%s", ln, ln, ln)));
    }
    @Test
    public void whenPiramidHasHeight4() {
        Paint paint = new Paint();
        String ln = System.lineSeparator();
        String result = paint.pyramid(4);
        assertThat(result, is(String.format("   ^   %s  ^^^  %s ^^^^^ %s^^^^^^^%s", ln, ln, ln, ln)));
    }
}
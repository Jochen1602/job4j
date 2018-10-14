package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class CheckTest Тестирование задачи 6.3. Массив заполнен true или false.
 *@author antontokarev
 *@since 14.10.2018
 */

public class CheckTest {
    @Test
    public void whenDataMonoByFalseFalseFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {false, false, false};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenDataMonoByTrueTrueTrueTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true, true};
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByFalseFalseTrueFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {false, false, true, false};
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }
}
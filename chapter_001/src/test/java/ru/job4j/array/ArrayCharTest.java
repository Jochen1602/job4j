package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class CheckTest Тестирование задачи 6.4. Слово начинается с ...
 *@author antontokarev
 *@since 14.10.2018
 */

public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hel");
        assertThat(result, is(true));
    }

    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }
    @Test

    public void whenNotStartWithPrefixThenFalse2() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Ke");
        assertThat(result, is(false));
    }
}
package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *Class Max тестирование задачи 4.2. Максимум из двух чисел
 *@author antontokarev
 *@since 11.10.2018
 */

public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max max = new Max();
        int result = max.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenNumbersAreTwoThreeAndOneMaxIsThree() {
        Max max = new Max();
        int result = max.max(2, 3, 1);
        assertThat(result, is(3));
    }
}

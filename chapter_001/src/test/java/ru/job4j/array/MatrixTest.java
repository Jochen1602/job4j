package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class MatrixTest Тестирование задачи 6.6. Двухмерный массив. Таблица умножения.
 *@author antontokarev
 *@since 14.10.2018
 */

public class MatrixTest {
    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expect));
    }    @Test
    public void when4on4() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(4);
        int[][] expect = {
                {1, 2, 3, 4},
                {2, 4, 6, 8},
                {3, 6, 9, 12},
                {4, 8, 12, 16}
        };
        assertThat(table, is(expect));
    }
}
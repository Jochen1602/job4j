package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**class BubbleSortTest Тестирование задачи 6.5. Создать программу для сортировки массива методом перестановки.
 *@author antontokarev
 *@since 14.10.2018
 */
public class BubbleSortTest {
    @Test
    public void MassiveOf97215TurnsTo12579() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[] {9, 7, 2, 1, 5};
        int[] result = bubbleSort.sort(input);
        int[] expect = new int[] {1, 2, 5, 7, 9};
        assertThat(result, is(expect));
    }

    @Test
    public void MassiveOf865321TurnsTo123568() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[] {8, 6, 5, 3, 2, 1};
        int[] result = bubbleSort.sort(input);
        int[] expect = new int[] {1, 2, 3, 5, 6, 8};
        assertThat(result, is(expect));
    }
}
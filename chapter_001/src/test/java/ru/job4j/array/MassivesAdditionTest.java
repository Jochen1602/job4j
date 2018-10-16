package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class MassivesAdditionTest Тестирование задачи о слиянии 2 упорядоченных масивов в третий упорядоченный массив.
 *@author antontokarev
 *@since 16.10.2018
 */
public class MassivesAdditionTest {
    @Test
    public void when258And12779MustBe12257789() {
        MassivesAddition massivesAddition = new MassivesAddition();
        int[] first = new int[] {2, 5 ,8};
        int[] second = new int[] {1, 2, 7, 7 ,9};
        int[] result = massivesAddition.result(first, second);
        assertThat(result, is(new int[] {1, 2, 2, 5, 7, 7, 8 ,9}));
    }
    @Test
    public void when1256And113MustBe1112356() {
        MassivesAddition massivesAddition = new MassivesAddition();
        int[] first = new int[] {1, 2 ,5, 6};
        int[] second = new int[] {1, 1, 3};
        int[] result = massivesAddition.result(first, second);
        assertThat(result, is(new int[] {1, 1, 1, 2, 3, 5, 6}));
    }
    @Test
    public void when89And113MustBe11389() {
        MassivesAddition massivesAddition = new MassivesAddition();
        int[] first = new int[] {1, 1 ,3};
        int[] second = new int[] {8, 9};
        int[] result = massivesAddition.result(first, second);
        assertThat(result, is(new int[] {1, 1, 3, 8, 9}));
    }
}
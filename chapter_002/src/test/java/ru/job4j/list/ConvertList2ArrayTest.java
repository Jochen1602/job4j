package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**class ConvertList2ArrayTest Тестирование задачи 3. Конвертация ArrayList в двухмерный массив
 *@author antontokarev
 *@since 01.11.2018
 */
public class ConvertList2ArrayTest {
    @Test
    public void addingTwoArrays() {
        ConvertList2Array list = new ConvertList2Array();
        int[] first = new int[]{1, 2};
        int[] second = new int[]{3, 4, 5};
        List<int[]> arr = new ArrayList<>();
        arr.add(first);
        arr.add(second);
        List<Integer> result = list.convert(arr);
        List<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(2);
        expect.add(3);
        expect.add(4);
        expect.add(5);
        assertThat(result, is(expect));
    }
    @Test
    public void addingThreeArrays() {
        ConvertList2Array list = new ConvertList2Array();
        int[] first = new int[]{1, 2};
        int[] second = new int[]{3, 4, 5};
        int[] third = new int[]{0, 8, 9};
        List<int[]> arr = new ArrayList<>();
        arr.add(first);
        arr.add(second);
        arr.add(third);
        List<Integer> result = list.convert(arr);
        List<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(2);
        expect.add(3);
        expect.add(4);
        expect.add(5);
        expect.add(0);
        expect.add(8);
        expect.add(9);
        assertThat(result, is(expect));
    }
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void when7ElementsThen9Foreach() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArrayForeach(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen10() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
                5
        );
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {0, 0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void when8ElementsThen10Foreach() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArrayForeach(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
                5
        );
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6},
                {7, 8},
                {0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when8ElementsThen10Another() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void when8ElementsThen10AnotherForeach() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArrayForeach(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
                2
        );
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        assertThat(result, is(expect));
    }
}
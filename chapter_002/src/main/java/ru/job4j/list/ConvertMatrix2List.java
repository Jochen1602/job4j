package ru.job4j.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**class ConvertMatrix2List Решение задачи 3. Конвертация двумерного массива в ArrayList
 *@author antontokarev
 *@since 01.11.2018
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
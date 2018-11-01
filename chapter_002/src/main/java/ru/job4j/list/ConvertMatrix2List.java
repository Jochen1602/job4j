package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**class ConvertMatrix2List Решение задачи 3. Конвертация двумерного массива в ArrayList
 *@author antontokarev
 *@since 01.11.2018
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] anArray : array) {
            for (int row : anArray) {
                list.add(row);
            }
        }
        return list;
    }
}
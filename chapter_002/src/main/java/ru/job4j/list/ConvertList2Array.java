package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**class ConvertList2Array Решение задачи 3. Конвертация ArrayList в двухмерный массив
 *@author antontokarev
 *@since 01.11.2018
 */
public class ConvertList2Array {
    /**
     * Метод, равномерно разбивающий коллекцию в двумерный массив с использованием foreach
     * upd: как я понимаю, этот метод на Stream API не перевести
     * @param list коллекция
     * @param rows сколько строк необходимо
     * @return искомый двумерный массив, в конце нули если нацело не делится
     */
    public int[][] toArrayForeach(List<Integer> list, int rows) {
        int i = 0, j = 0;
        int cells = (list.size() + rows - 1) / rows;
        int[][] array = new int[rows][cells];
        for (int item : list) {
            if (i == rows) {
                i = 0;
            }
            if (j == cells) {
                j = 0;
                i++;
            }
            array[i][j++] = item;
        }
        return array;
    }

    /**
     * Метод добавляет все элементы всех массивов, поступающих на вход, в один список
     * @param list список массивов
     * @return список, содержащий все элементы массивов
     */
    public List<Integer> convert(List<int[]> list) {
        return list.stream().flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }
}
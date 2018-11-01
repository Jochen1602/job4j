package ru.job4j.list;

import java.util.List;

/**class ConvertList2Array Решение задачи 3. Конвертация ArrayList в двухмерный массив
 *@author antontokarev
 *@since 01.11.2018
 */
public class ConvertList2Array {
    /**
     * Метод, равномерно разбивающий коллекцию в двумерный массив
     * @param list коллекция
     * @param rows сколько строк необходимо
     * @return искомый двумерный массив, в конце нули если нацело не делится
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (list.size() + rows - 1) / rows;
        int[][] array = new int[rows][cells];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if (i * cells + j < list.size()) {
                    array[i][j] = list.get(i * cells + j);
                }
            }
        }
        return array;
    }

    /**
     * Метод, равномерно разбивающий коллекцию в двумерный массив с использованием foreach
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
}
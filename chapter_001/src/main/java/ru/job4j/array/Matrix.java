package ru.job4j.array;

/**class BubbleSort Решение задачи 6.6. Двухмерный массив. Таблица умножения.
 *@author antontokarev
 *@since 14.10.2018
 */

public class Matrix {
    /**Функция, создающая таблицу умножения определённого размера
     * @param size - размер таблицы умножения.
     * @return двумерный массив с таблицей умножения.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
package ru.job4j.array;

/**class BubbleSort Решение задачи 6.5. Создать программу для сортировки массива методом перестановки.
 *@author antontokarev
 *@since 14.10.2018
 */
public class BubbleSort {
    /**Функция сортировки пузырьком.
     * @param array - несортированный массив.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        int buf;
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    buf = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = buf;
                }
            }
        }
        return array;
    }
}
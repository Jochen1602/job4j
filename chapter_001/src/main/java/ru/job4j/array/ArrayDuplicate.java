package ru.job4j.array;

import java.util.Arrays;

/**class ArrayDuplicate Решение задачи 6.8. Удаление дубликатов в массиве.
 *@author antontokarev
 *@since 14.10.2018
 */
public class ArrayDuplicate {
    /**Метод убирает все дубликаты строк из массива.
     * @param array - массив строк на вход.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int k = array.length;
        String buf;
        for (int j = 1; j < k; j++) {
            for (int i = 0; i < j; i++) {
                if (array[i].equals(array[j])) {
                    buf = array[j];
                    array[j--] = array[k - 1];
                    array[k-- - 1] = buf;
                }
            }
        }
        return Arrays.copyOf(array, k);
    }
}
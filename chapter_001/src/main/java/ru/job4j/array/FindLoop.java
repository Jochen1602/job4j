package ru.job4j.array;

/**class FindLoop Решение задачи 6.1. Классический поиск перебором.
 *@author antontokarev
 *@since 13.10.2018
 */
public class FindLoop {
    /**Функция, вычисляющая номер элемента массива, равного параметру element.
     * @param data - массив.
     * @param element - число, которое мы ищем.
     * @return номер элемента массива равный element, или -1 если таковой не найден.
     */
    public int indexOf(int[] data, int element) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element) {
                result = i;
                break;
            }
        }
        return result;
    }
}
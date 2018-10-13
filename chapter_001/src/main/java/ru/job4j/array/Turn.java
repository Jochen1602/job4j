package ru.job4j.array;
/**class Turn Решение задачи 6.2. Перевернуть массив.
 *@author antontokarev
 *@since 13.10.2018
 */
public class Turn {
    /**Функция, которая принимает на вход массив и возвращает его перевёрнутым задом-наперёд.
     * @param array - массив, который надо обернуть.
     * @return перевёрнутый массив.
     */
    public int[] turn(int[] array) {
        int buf;
        for (int i = 0; i < array.length / 2; i++) {
            buf = array[array.length - 1 - i];
            array[array.length - 1 - i] = array[i];
            array[i] = buf;
        }
        return array;
    }
}
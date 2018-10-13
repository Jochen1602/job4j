package ru.job4j.array;

/**class Square Решение задачи 6.0. Заполнить массив степенями чисел.
 *@author antontokarev
 *@since 13.10.2018
 */
public class Square {
    /**Функция, заполняющая массив квадратами натуральных чисел.
     * @param bound - сколько первых натуральных чисел мы задействуем.
     * @return массив с квадратами натуральных чисел.
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 1; i <= bound; i++) {
            result[i - 1] = i * i;
        }
        return result;
    }
}
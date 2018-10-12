package ru.job4j.loop;

/**Class Factorial решение задачи 5.2. Создать программу вычисляющую факториал.
 *@author antontokarev
 *@since 12.10.2018
 */

public class Factorial {
    private int result = 1;

    /**Функция, вычисляющая факториал числа.
     * @param n - число, от которого вычисляется факториал.
     * @return искомый факториал.
     */
    public int calc(int n) {
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
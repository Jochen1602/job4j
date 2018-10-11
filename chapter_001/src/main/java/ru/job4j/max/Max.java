package ru.job4j.max;

/**
 *Class Max решение задачи 4.2. Максимум из двух чисел
 *@author antontokarev
 *@since 11.10.2018
 */

public class Max {
    /**
     * Метод определения максимума из 2 чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @return максимум из двух чисел.
     */
    public int max(int first, int second) {
        int result = first > second ? first : second;
        return result;
    }
}

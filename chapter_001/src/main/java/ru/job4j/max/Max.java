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
        return first > second ? first : second;
    }

    /**
     * Метод определения максимума из 3 чисел.
     * @param first - первое число.
     * @param second - второе число.
     * @param third - третье число.
     * @return максимум из трёх чисел.
     */
    public int max(int first, int second, int third) {
        int temp = max(first, second);
        temp = max(temp, third);
        return temp;
    }
}

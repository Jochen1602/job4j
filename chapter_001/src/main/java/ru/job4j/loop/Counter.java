package ru.job4j.loop;

/**
 *Class Counter решение задачи 5.1. Подсчет суммы чётных чисел в диапазоне.
 *@author antontokarev
 *@since 12.10.2018
 */
public class Counter {

    private int sum;
    /**
     * Метод, вычисляющий сумму чётных чисел в диапазоне.
     * @param start - начало диапазона.
     * @param finish - конец диапазона.
     * @return сумма чётных чисел в диапазоне.
     */
    public int add(int start, int finish) {
        for (int i = start; i <= finish; i++)
            sum += (i % 2 == 0) ? i : 0;
        return sum;
    }
}
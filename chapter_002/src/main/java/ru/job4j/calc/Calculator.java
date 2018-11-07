package ru.job4j.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *class Calculator Решение задачи 1. Подсчет функции в диапазоне.
 *@author antontokarev
 *@since 07.11.2018
 */
public class Calculator {
    /**
     * Наша универсальная вычислительная функция для диапазона.
     * @param start начало диапазона.
     * @param end конец диапазона.
     * @param func функция, которую необходимо применить.
     * @return список значений.
     */
    private List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }

    /**
     * Линейная функция для диапазона.
     * @param start начало диапазона.
     * @param end конец диапазона.
     * @return список значений.
     */
    List<Double> linear(int start, int end) {
        return diapason(start, end, (n) -> n);
    }

    /**
     * Квадратичная функция для диапазона.
     * @param start начало диапазона.
     * @param end конец диапазона.
     * @return список значений.
     */
    List<Double> quadratic(int start, int end) {
        return diapason(start, end, (n) -> Math.pow(n, 2));
    }

    /**
     * Логарифмическая функция для диапазона.
     * @param start начало диапазона.
     * @param end конец диапазона.
     * @return список значений.
     */
    List<Double> logarithmic(int start, int end) {
        return diapason(start, end, Math::log);
    }
}
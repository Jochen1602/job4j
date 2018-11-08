package ru.job4j.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *class Calculator Решение задачи 1. Подсчет функции в диапазоне.
 *@author antontokarev
 *@since 07.11.2018
 */
public class Calculator {
    public List<Double> diapasonFunction(int start, int end, Function<Integer, Double> op) {
        List<Double> result = new ArrayList<>();
        for (int index = start; index != end; index++) {
            result.add(op.apply(index));
        }
        return result;
    }
}
package ru.job4j.comparator;

import java.util.Comparator;

/**
 *class ListCompare Решение задачи 3. Компаратор для строк.
 *@author antontokarev
 *@since 04.11.2018
 */
public class ListCompare implements Comparator<String> {
    /**
     * Метод сравнения строк как массивов символов. Сначала сравниваем посимвольно
     * оба стринга пока либо один из них не кончится, либо найдём больший из них.
     * Если один из стрингов закончился и победитель не выявлен, большим будет тот,
     * где ещё остались символы.
     * @param left что сравниваем
     * @param right с чем сравниваем
     * @return результат > 0 если первый аргумент больше второго, < 0 если
     * первый меньше второго, и 0 если они равны
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        for (int i = 0; i < Math.min(left.length(), right.length()); i++) {
            if (left.charAt(i) - right.charAt(i) != 0) {
                result = left.charAt(i) - right.charAt(i);
                break;
            }
        }
        if (result == 0) {
            result = left.length() - right.length();
        }
        return result;
    }
}
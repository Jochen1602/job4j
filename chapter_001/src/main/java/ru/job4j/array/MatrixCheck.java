package ru.job4j.array;

/**class MatrixCheck Решение задачи 6.7. Квадратный массив заполнен true или false по диагоналям.
 *@author antontokarev
 *@since 14.10.2018
 */

public class MatrixCheck {
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++)
            if ((data[i][i] != data[0][0]) || (data[i][data.length - 1 - i] != data[0][data.length - 1]))
                result =  false;
        return result;
    }
}
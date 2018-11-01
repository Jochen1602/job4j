package ru.job4j.chess;

/**class FigureNotFoundException Решение задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */
public class FigureNotFoundException extends Exception {
    public FigureNotFoundException(String message) {
        super(message);
    }
}

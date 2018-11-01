package ru.job4j.chess;

/**class ImpossibleMoveException Решение задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */
public class ImpossibleMoveException extends Exception {
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
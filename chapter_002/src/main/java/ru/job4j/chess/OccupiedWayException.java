package ru.job4j.chess;

/**class OccupiedWayException Решение задачи Каркас шахматной доски
 *@author antontokarev
 *@since 01.11.2018
 */
public class OccupiedWayException extends Exception {
    public OccupiedWayException(String message) {
        super(message);
    }
}

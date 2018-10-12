package ru.job4j.loop;

/**Class Factorial решение задачи 5.3. Построить шахматную доску в псевдографике.
 *@author antontokarev
 *@since 12.10.2018
 */

public class Board {
    /**Функция, рисующая на экран подобие шахматной доски.
     * @param width - ширина доски.
     * @param height - высота доски.
     * @return строка
     */
    public String paint(int width, int height) {
        StringBuilder board = new StringBuilder();
        String ln = System.lineSeparator();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if ((i + j) % 2 == 0) {
                    board.append("X");
                } else {
                    board.append(" ");
                }
            }
            board.append(ln);
        }
        return board.toString();
    }
}

package ru.job4j.loop;

/**Class Paint решение задачи 5.4. Построить пирамиду в псевдографике.
 *@author antontokarev
 *@since 12.10.2018
 */

public class Paint {
    /**Функция, строящая псевдопирамиду в строку
     * @param h - высота пирамиды.
     * @return строка с пирамидой.
     */
    public String pyramid(int h) {
        StringBuilder board = new StringBuilder();
        String ln = System.lineSeparator();
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < 2 * h - 1; i++) {
                //не очевидно, но зато коротко и pure math
                //мы тут проверяем, что если точка достаточно близка к левому верхнему или правому верхнему углу
                //то там пробел, иначе галка
                if (((i + j) < h - 1) || ((2 * h - 1 - i + j) < h))
                    board.append(" ");
                else
                    board.append("^");
            }
            board.append(ln);
        }
        return board.toString();
    }
}
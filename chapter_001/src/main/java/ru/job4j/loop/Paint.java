package ru.job4j.loop;

/**Class Paint решение задачи 5.4. Построить пирамиду в псевдографике.
 *@author antontokarev
 *@since 12.10.2018
 */

public class Paint {
    /**Функция, строящая псевдопирамиду в строку
     * @param height - высота пирамиды.
     * @return строка с пирамидой.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        for (int j = 0; j != height; j++) {
            for (int i = 0; i != 2 * height - 1; i++) {
                if (j >= height - i - 1 && j + height - 1 >= i) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();

        for (int j = 0; j != height; j++) {
            for (int i = 0; i != height; i++) {
                if (j >= i) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();

        for (int j = 0; j != height; j++) {
            for (int i = 0; i != height; i++) {
                if (j >= height - i - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}

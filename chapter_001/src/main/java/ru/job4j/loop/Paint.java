package ru.job4j.loop;

import java.util.function.BiPredicate;

/**Class Paint решение задачи 5.4. Построить пирамиду в псевдографике.
 *@author antontokarev
 *@since 12.10.2018
 */
public class Paint {
    /**построение правой пирамиды.
     * @param height - высота пирамиды.
     * @return строка с пирамидой.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (j, i) -> j >= i
        );
    }

    /**построение левой пирамиды.
     * @param height - высота пирамиды.
     * @return строка с пирамидой.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (j, i) -> j >= height - i - 1
        );
    }

    /**построение классической пирамиды.
     * @param height - высота пирамиды.
     * @return строка с пирамидой.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (j, i) -> j >= height - i - 1 && j + height - 1 >= i
        );
    }

    /**Функция, строящая псевдопирамиду в строку.
     * @param height - высота пирамиды.
     * @param weight - шиирна пирамиды.
     * @param predict - условие ставить галку или пробел.
     * @return строка с пирамидой.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int j = 0; j != height; j++) {
            for (int i = 0; i != weight; i++) {
                if (predict.test(j, i)) {
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
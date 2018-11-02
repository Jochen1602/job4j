package ru.job4j.coffee;

import java.util.Arrays;

/**
 *class CoffeeMachine Класс кофемашины
 *@author antontokarev
 *@since 01.11.2018
 */
public class CoffeeMachine {
    private final int[] coins = new int[]{10, 5, 2, 1};
    /**
     * Метод, определяющий какую сдачу нужно выдать монетами по 10, 5, 2 и 1
     * @param value купюра
     * @param price стоимость
     * @return массив из монет в убывающем порядке
     */
    public int[] changes(int value, int price) {
        int[] temp = new int[(value - price) / 10 + 3];
        int counter = 0;
        value -= price;
        for (int coin : coins) {
            while (value - coin >= 0) {
                temp[counter++] = coin;
                value -= coin;
            }
        }
        return Arrays.copyOf(temp, counter);
    }
}
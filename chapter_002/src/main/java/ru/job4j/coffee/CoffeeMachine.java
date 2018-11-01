package ru.job4j.coffee;

import java.util.Arrays;

/**
 *class CoffeeMachine Класс кофемашины
 *@author antontokarev
 *@since 01.11.2018
 */
public class CoffeeMachine {
    /**
     * Метод, определяющий какую сдачу нужно выдать монетами по 10, 5, 2 и 1
     * @param value купюра
     * @param price стоимость
     * @return массив из монет в убывающем порядке
     */
    public int[] changes(int value, int price) {
        int monets = value - price;
        int[] temp = new int[monets / 10 + 3];
        int counter = 0;
        for (int i = 0; i < monets / 10; i++) {
            temp[counter++] = 10;
        }
        monets -= (monets / 10) * 10;
        for (int i = 0; i < monets / 5; i++) {
            temp[counter++] = 5;
        }
        monets -= (monets / 5) * 5;
        for (int i = 0; i < monets / 2; i++) {
            temp[counter++] = 2;
        }
        monets -= (monets / 2) * 2;
        if (monets == 1) {
            temp[counter++] = 1;
        }
        return Arrays.copyOf(temp, counter);
    }
}
package ru.job4j.converter;

/*
 * Конвертер валют.
 */
public class Converter {
    /**
     * Конвертируем рубли в Евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro (int value) {
        return value / 70;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return доллары.
     */
    public int rubleToDollar (int value) {
        return value / 60;
    }

}

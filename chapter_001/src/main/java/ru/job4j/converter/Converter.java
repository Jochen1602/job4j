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
    public int rubleToEuro(int value) {
        return value / 70;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return доллары.
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Конвертируем Евро в рубли.
     * @param value Евро.
     * @return рубли.
     */
    public int euroToRuble(int value) {
        return value * 70;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return рубли.
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }

}

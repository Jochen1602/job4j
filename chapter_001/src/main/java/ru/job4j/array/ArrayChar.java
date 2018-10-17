package ru.job4j.array;

/**class ArrayChar Решение задачи 6.4. Слово начинается с ...
 *@author antontokarev
 *@since 14.10.2018
 */
public class ArrayChar {
    private char[] data;
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }
    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        char[] value = prefix.toCharArray();
        boolean result = true;
        for (int i = 0; i < value.length; i++) {
            if (value[i] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
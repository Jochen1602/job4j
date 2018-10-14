package ru.job4j.array;

import java.util.Arrays;

/**class BubbleSort Решение задачи 6.8. Удаление дубликатов в массиве.
 *@author antontokarev
 *@since 14.10.2018
 */
public class ArrayDuplicate {
    /**Метод убирает все дубликаты строк из массива.
     * @param array - массив строк на вход.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int num = 0;
        String buf;
        for (int j = 1; j < array.length; j++) {
            for (int i = 0; i < j; i++) {
                if (array[i].equals(array[j])) {
                    //если нашли совпадение, то счётчик совпадений ++ и циклично двигаем остаток массива
                    num++;
                    buf = array[j];
                    for (int k = j; k < array.length - 1; k++)
                        array[k] = array[k + 1];
                    array[array.length - 1] = buf;
                    //обязательно именно тут сбрасываем счётчик прохода поиска дублей в начало
                    i = 0;
                }
                //если мы нашли столько дублей, что их сумма с тем, что без дублей
                //с длину массива, значит пора прерывать цикл
                if (num + j + 1 >= array.length)
                    break;
            }
        }
        return Arrays.copyOf(array, array.length - num);
    }
}